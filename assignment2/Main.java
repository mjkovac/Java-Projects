package assignment2;

import java.io.File;
import java.util.Vector;

//import assignment2.MobileDevice.RentSettings;

public class Main {

    public static void main(String[] args) {
    	
        /* TASK 1 - build labs from files - at least two labs */    	
        System.out.println("\n\n *" + " TASK 1 " + "*");
        
        File cur = new File("./src/assignment2/");
        String[] ls = cur.list();
        
        System.out.println(cur.getAbsolutePath());
        
        Vector<String> labName = new Vector<String>();
        for ( String t : ls )
        {
        	if ( t.contains("Lab.txt") )
        		labName.add(t);
        		//labName.addElement(t);        	
        }
        
        Labs labs = new Labs(labName.size());
        int index = 0;
        for ( String s : labName)
        {
        	String[] temp = s.split("Lab");
        	Lab l = labs.addDevicesToLab(temp[0], s);
        	labs.labs[index++] = l;        	
        }
        
        /* TASK 2 - ask for a device that is not in any lab inventory */
        System.out.println("\n\n *" + " TASK 2 " + "*");
        
        MobileDevice t2 = new MobileDevice("Android20", 20);        
        if ( labs.isThereDeviceInLabs(t2) == null)
        	System.out.println(Helper.printNonexistent(t2));
        else
        	System.out.println("Device " + t2.toString() + " has been found!");
        
        
         /* TASK 3 - ask for a device that is in a lab inventory
         *  issue a rent request and print the device
         *  issue the same rent request and print the device
         *  return the device
         *  issue the rent request with new dates and print the device
         */
        System.out.println("\n\n *" + " TASK 3 " + "*");
        
        MobileDevice t3 = labs.labs[0].devices.elementAt(0);
        String[] dates = {Helper.getCurrentDate(), Helper.getFutureDate(30) };
        
        if ( labs.isThereDeviceInLabs(t3) == null)
        	System.out.println(Helper.printNonexistent(t3));
        else
        {        	        	
        	if ( t3.lab.rentRequest(t3, dates[0], dates[1]))
        	{
        		for (int i = 0; i < 2; i++)
        		{
        			if (!t3.isRented(t3.lab))
					{
						t3.rentDevice(dates[0],	dates[1], t3.lab);
						System.out.println(Helper.printAvailable(t3, dates[0], t3.lab));
						System.out.println("wanted = " + t3);
					}
					else
					{
						System.out.println(Helper.printUnavailable(t3, dates[0]));
						t3.returnDevice(t3.lab);
						
						if (!t3.isRented(t3.lab))
						{
							t3.rentDevice(dates[0],	Helper.getFutureDate(90), t3.lab);
							System.out.println(Helper.printAvailable(t3, dates[0], t3.lab));
							System.out.println("wanted = " + t3);	
						}						
					}            	
        		}        		
        	}
        }
             
         /* TASK 4 - ask for the same device in all labs
          * if you can find a lab, rent the device from that lab
          */
        
        System.out.println("\n\n *" + " TASK 4 " + "*");
        
        MobileDevice t4 = new MobileDevice("Android", 25);
        Lab found = labs.rentDeviceAvailable(t4, Helper.getCurrentDate(), Helper.getFutureDate(13));
        t4.setLab(found);
        
        if ( found != null && found.rentRequest(t4, Helper.getCurrentDate(), Helper.getFutureDate(13)))
        {
        	for (int i = 0; i < 2; i++)
        	{
        		if (!t4.isRented(found))
        		{
        			t4.rentDevice(Helper.getCurrentDate(), Helper.getFutureDate(13), found);
        			System.out.println(Helper.printAvailable(t4, Helper.getCurrentDate(), found));
        			System.out.println("wanted = " + t4.toString());
        		}
        		else
        		{
        			System.out.println(Helper.printUnavailable(t4, dates[0]));
        			t4.returnDevice(found);
        			
        			if (!t4.isRented(found))
        			{
        				t4.rentDevice(Helper.getCurrentDate(), Helper.getFutureDate(90), found);
        				System.out.println(Helper.printAvailable(t4, Helper.getCurrentDate(), found));
        				System.out.println("wanted = " + t4);	
        			}						
        		}            	
        	}
        }
        else
        	System.out.println("Device " + t4 + " is currently rented out in all labs!");
        
        
        
//        for (Lab l : labs.labs)
//        {        	
//        	if ( l.isThereDevice(t4) )
//        	{
//        		t4.setLab(l);
//        		if ( l.rentRequest(t4, dates[0], dates[1]) )
//        		{
//        			for (int i = 0; i < 2; i++)
//            		{
//            			if (!t4.isRented(l))
//    					{
//    						t4.rentDevice(dates[0],	dates[1], l);
//    						System.out.println(Helper.printAvailable(t4, dates[0], l));
//    						System.out.println("wanted = " + t4.toString());
//    					}
//    					else
//    					{
//    						System.out.println(Helper.printUnavailable(t4, dates[0]));
//    						t4.returnDevice(l);
//    						
//    						if (!t4.isRented(l))
//    						{
//    							t4.rentDevice(dates[0],	Helper.getFutureDate(90), l);
//    							System.out.println(Helper.printAvailable(t4, dates[0], l));
//    							System.out.println("wanted = " + t4);	
//    						}						
//    					}            	
//            		}    
//        		}
//        	}
//        }
        
        /* TASK 5 - calculate maximum value tag for each lab */
        
        System.out.println("\n\n *" + " TASK 5 " + "*");
        
        for (Lab l : labs.labs)
        {
        	int max = 0;
        	max = l.findMaximumValueTag();
        	System.out.println("The max value tag for " + l.labName + " is: " + max + ".");
        }
                
        /* TASK 6 - inquire about a device: is it rented?, is it overdue?, 
         * could it be found in more than one lab?, when can it be rented?  */
        
        System.out.println("\n\n *" + " TASK 6 " + "*");      
        
        MobileDevice t6 = new MobileDevice("Android", 25);        
        for ( Lab l : labs.labs)
        {
        	if (l.isThereDevice(t6))
        	{       
        		t6.setLab(l);
            	if (l.labName.equalsIgnoreCase("newnham"))
            	{            		
            		if ( l.rentRequest(t6, Helper.getCurrentDate(), Helper.getFutureDate(14)))            		
            			t6.rentDevice(Helper.getCurrentDate(), Helper.getFutureDate(14), l);            	
            	}
            	else if (l.labName.equalsIgnoreCase("york"))
            	{
            		if ( l.rentRequest(t6, Helper.getCurrentDate(), Helper.getFutureDate(14)))            		
            			t6.rentDevice("01/01/1990", "01/02/1990", l);
            	}
            	
            	if ( !t6.isRented(l))                			            	
            		System.out.println(Helper.printAvailable(t6, Helper.getCurrentDate(), l) + "\n"); 
    			else if (t6.isDeviceOverdue())
    				System.out.println(Helper.printUnavailable(t6, Helper.getCurrentDate()) + 
    						"\nIt seems like this device is overdue! It will become available when the user returns it!");
    			else
    				System.out.println(Helper.printUnavailable(t6, Helper.getCurrentDate()) + 
    						"\nThis device can be expected to be rented out on: " + t6.availableDate(l) + "\n");
            	
            	t6.returnDevice(l);
        	}       
        }   
    }
}