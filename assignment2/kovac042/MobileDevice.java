package assignment2;

class MobileDevice {

    String       deviceName;  // the device name
    int          valueTag;    // an integer between -100 and 100
    Lab          lab;         // the lab having this device it its inventory
    RentSettings rs;          // rent settings

    /**
     * Default constructor, sets all variables to a safe state
     */
    public MobileDevice() 
    {
    	deviceName = null;
    	valueTag = 0;
    	lab = null;
    	rs = null;
    }

    /**
     * Constructor for a mobile device, 
     * @param deviceName sets the device name
     * @param valueTag sets the value tag
     */
    public MobileDevice(String deviceName, int valueTag) 
    {
    	this.deviceName = deviceName;
    	this.valueTag = valueTag;
    }

    /**
     * @param lab sets this device's lab to parameter
     */
    public void setLab(Lab lab) 
    {
    	this.lab = lab;    	
    }

    // set the rent dates; if dates are not valid catch DateFormatException and return false,
    // if rentDate > dueDate catch RentPeriodException and return false
    // if one the exceptions occur there is no RentSettings object
    /**
     * @param rentDate when the device would like to be taken out
     * @param dueDate when the device should be returned
     * @param lab where this device is going to be rented
     * @return true if device is possible to be rented out
     */
    public boolean rentDevice(String rentDate, String dueDate, Lab lab) 
    {
        RentSettings rs = null;
       
        try {
			if ((Helper.isValidDate(rentDate) && Helper.isValidDate(rentDate)) && Helper.timeDifference(dueDate, rentDate) < 0)
				rs = new RentSettings(rentDate, dueDate, lab);			
			else
				return false;
		} catch (DateFormatException | RentPeriodException e) {
			e.printStackTrace();
		}
        
        this.rs = rs;
        return true;
    }

    // destroy the RentSettings object for this device
    /**
     * @param lab desired lab the device should be returned to
     * @return a device back to the lab
     */
    public void returnDevice(Lab lab) 
    {
       if (this.lab == lab)
    	   this.rs = null;
    }

    /**
     * @param lab to check
     * @return the date when this device is available
     */
    public String availableDate(Lab lab) 
    {    	
    	String d = "";
    	
    	if ( getRs() == null )
    		d += Helper.getCurrentDate();
		else
			if ( isDeviceOverdue() )
				d += "When the person who rented out the device has returned it!";
			else
				d += rs.dueDate;
    	
    	return d;
    }

    /**
     * @return true if the current date is greater than the due date
     */
    public boolean isDeviceOverdue() 
    {
        try {
			return Helper.timeDifference(rs.dueDate, Helper.getCurrentDate()) > 0;
		} catch (DateFormatException e) {
			e.printStackTrace();
		}
        return false;
    }

    /**
     * @param l to be checked if device is rented 
     * @return true if the device is rented from lab
     */
    public boolean isRented(Lab l)
    {    	
        return ( l == this.lab && rs != null );
    }

    /**
     * @return rent settings for this device
     */
    public RentSettings getRs()
    {
    	return rs;
    }

    /**
     * @param rs for this device to be given
     */
    public void setRs(RentSettings rs)
    {       
    	this.rs = rs;
    }

    @Override
    public boolean equals(Object o)
    {    	
    	if ( o.getClass() == this.getClass() )
    	{
    		MobileDevice m = (MobileDevice) o;
    		return ( m.deviceName == this.deviceName && m.valueTag == this.valueTag 
    				&& m.lab == this.lab && m.getRs() == this.getRs() );
    	}
    	return false;
    }

    @Override
    public int hashCode() {
        int result = deviceName != null ? deviceName.hashCode() : 0;
        result = 31 * result + valueTag;
        return result;
    }

    @Override
    public String toString() {
    	String o = "";
    	
    	o += "(" + deviceName + ", " + valueTag;
    	
    	if (lab == null)
    	{
    		o += ")";
    	}
    	else
    	{    	
    		o += " => " + lab.labName + ")";
    		if (rs != null)
    			o += " " + rs.toString();
    	}
    	   	return o;
     }

    /**
     * @return device name
     */
    public String deviceName() {
        return "(" + deviceName + ", " + valueTag + ')';
    }

    private class RentSettings {

        private String rentDate;          // date when the item is requested
        private String dueDate;           // date when the item must be returned
        private boolean borrowed = false; // true if the item is rented

        //default ctr
        private RentSettings() throws DateFormatException 
        {
            //TODO
        	rentDate = null;
        	dueDate = null;        
        }

        // private ctr must throw DateFormatException and RentPeriodException
        private RentSettings(String rentDate, String dueDate, Lab lab) 
        		throws DateFormatException, RentPeriodException 
        {
            //TODO
        	if (Helper.isValidDate(rentDate) && Helper.isValidDate(dueDate) && lab != null)
        	{
        		this.rentDate = rentDate;
        		this.dueDate = dueDate; 
        		borrowed = true;
        	}        	
        }

        @Override
        public String toString() 
        {
            return "RentSettings{" +
                    "rentDate='" + rentDate + '\'' +
                    ", dueDate='" + dueDate + '\'' + MobileDevice.this.lab.labName +
                    ", borrowed=" + borrowed +
                    '}';
        }
    }
}