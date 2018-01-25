package assignment2;

import java.util.Vector;

public class Lab implements MaxTagValue {
    String labName;
    Vector<MobileDevice> devices;

    /**
     * @param labName
     * Constructs a single lab with the desired lab name
     */
    public Lab(String labName) 
    {
    	if (labName != null)
    	{
    		this.labName = labName;
    		devices = new Vector<MobileDevice>();
    	}
    }

    /**
     * @param md "MobileDevice" being added to the lab
     * Adds a mobile device to a vector and sets the lab to the current referred lab
     */
    public void addDevice(MobileDevice md) 
    {
    	if (md != null)    	
    	{
    		devices.addElement(md);
    		md.setLab(this);
    	}
    }

    @Override
    public String toString() 
    {
        String r = "";
        for (MobileDevice d : devices)
        {
        	r += d.toString() + "\n";
        }        
        return r;
    }

    /**
     * @param md "MobileDevice" being checked
     * @return true if the device resides in the current lab
     */
    public boolean isThereDevice(MobileDevice md) 
    {
    	for ( MobileDevice m : devices)
    	{
    		if ( m.deviceName.equals(md.deviceName) && m.valueTag == md.valueTag )
    			return true;    		    			
    	}    	
    	return false;
    }

    public int findMaximumValueTag() 
    {
       int max = 0;
       for (MobileDevice m : devices)
       {
    	   if ( m.valueTag > max )
    		   max = m.valueTag;
       }
       
       return max;
    }

    @Override
    public boolean equals(Object o) 
    {
    	if ( o.getClass() == this.getClass())
    	{
    		Lab l = (Lab) o;
    		return ( l == this );
    	}
        return false;
    }

    @Override
    public int hashCode() 
    {
        int result = labName != null ? labName.hashCode() : 0;
        result = 31 * result + (devices != null ? devices.hashCode() : 0);
        return result;
    }

    /**
     * @param wanted "MobileDevice" referring to wanting to rent
     * @param requestDate the date in mm/dd/yyyy format you want the device
     * @param dueDate when the device should be returned in mm/dd/yyyy format
     * @return true if the device is available on requested date, with valid dates, and due date after the rent date
     */
    public boolean rentRequest(MobileDevice wanted, String requestDate, String dueDate)
    {   
    	try {
			if (wanted.getRs() == null && Helper.isValidDate(requestDate) && Helper.isValidDate(dueDate)
					&& Helper.timeDifference(dueDate, requestDate) < 0)
			{
				//wanted.rentDevice(requestDate, dueDate, this);
				return true;
			}
		} catch (DateFormatException e1) {
			e1.printStackTrace();
		}
    	
    	return false;  	    	
    }
}