package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Labs {
	public Lab[] labs;        // a collection of labs of type array
    public int numberOfLabs;  // number of labs in collection

    /**
     * @param numberOfLabs 
     * Constructor for allocating memory for required labs
     */
    public Labs(int numberOfLabs) 
    {
    	this.numberOfLabs = numberOfLabs;
    	labs = new Lab[numberOfLabs];
    }

    /**
     * @param labName
     * @param labFileName
     * @return individual lab built with devices added
     * This method utilizes the "buildLabFromFile"
     */
    public Lab addDevicesToLab(String labName, String labFileName) 
    {
    	Lab lab = buildLabFromFile(labName, labFileName);
    	System.out.println("Lab = " + labName + "\n[\n" + lab + "]");
        return lab;
    }

    /**
     * @param labName - the name in which the lab shall be given
     * @param fileName - the filename for the text file in which the lab will be built from
     * @return the built lab generated with the given lab-name and filename
     */
    public Lab buildLabFromFile(String labName, String fileName) 
    {
        Lab lab = new Lab(labName);
        MobileDevice md = null;
        String s;
        
        try (BufferedReader br = new BufferedReader(new FileReader("./src/assignment2/" + fileName))) 
        {
            while ((s = br.readLine()) != null) 
            {       
            	String temp[];
            	temp = s.split(",");
 
            	md = new MobileDevice(temp[0], Integer.parseInt(temp[1]));
            	
            	lab.addDevice(md);           
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }  
        return lab;
    }

    /**
     * @param md "MobileDevice" to be checked among all labs
     * @return The lab in which the mobile device resides in, "null" if the device is not found
     */
    public Lab isThereDeviceInLabs(MobileDevice md) 
    {
    	for ( Lab l : labs)
    	{
    		if ( l.isThereDevice(md) )
    			return l;
    	}
        return null;
    }

    /**
     * @param md "MobileDevice" to check availability for
     * @param requestDate the date the user would like to rent the device on
     * @param dueDate the date when the device should be returned
     * @return the lab where the device is available to be rented at, "null" if not available
     */
    public Lab rentDeviceAvailable(MobileDevice md, String requestDate, String dueDate) 
    {     	
    	if ( md != null )
    	{
    		for (Lab l : labs)
    		{
    			if ( l.isThereDevice(md) && l.rentRequest(md, requestDate, dueDate))
	    			return l;
    		}
    			
    	}
    	return null;    	
    }
    
    @Override
    public String toString()
    {
    	String o = "";  
    	o += labs.toString();
		return o;    	
    }
    
    @Override
    public boolean equals(Object o)
    {
    	if (o.getClass() == this.getClass())
    	{
    		Labs l = (Labs) o;
    		return l.labs == this.labs && l.numberOfLabs == this.numberOfLabs;
    	}
    	return false;
    }
}
