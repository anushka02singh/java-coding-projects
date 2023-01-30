public class LeasingCost {
    
    /* 
     * Description:
     *      This method creates an array of Vehicles objects from the given file name
     *      ******IMPORTANT******
     *      This method calls buildVehicle() method, which is STUDENT'S task to complete.
     *
     * Parameters:
     *      filename: the file name containing the vehicles description
     *
     * File format:
     *      the first line of the file containing an integer representing how many vehicles will be described 
     *      in the following lines.
     *      Each line other than the first line describes one vehicle; 
     *      7 or 8 fragments of data in randomly placed order describing the vehicle (7 for gas car, 8 for electric car) in each line. 
     *      Each fragment end with the ';' character
     * 
     *   All possible fragments:
     *      type:FULETYPE;
     *          FULETPE can be gas or electric
     *      name:CARNAME;
     *          CARNAME is the name of the car
     *      due:DUEATSIGNING;
     *          DUEATSIGNING is a double number describing the dollar amount due when signing the lease
     *      length:LEASELENGTH;
     *          LEASELENGTH is an integer number describing the lease length in months
     *      monthly:MONTHLYCOST;
     *          MONTHLYCOST is a double number describing the monthly lease cost in dollar
     *      mile/unit:USAGE; 
     *          USAGE is a double describing miles the car can drive per gallon if fuel type is GAS, or miles the car can drive per kWh if fuel type is ELECTRIC
     *      allowance:MILEAGEALLOWANCE;
     *          MILEAGEALLOWANCE is an integer describing the maximum distance the car is allowed to travel per month
     *      charger:CHARGERCOST;
     *          CHARGERCOST is a double describing the cost of charger for electric cars, this fragment can only appear if the line is describing an electrical car
     *   Example of a line:
     *      type:gas; name:civic; due:1000; length:3; monthly:295; mile/unit:34; 
     *
     * Returns:
     *      this method returns an array of Vehicle objects 
     */
	public static Vehicle[] createAllVehicles(String filename) {
        StdIn.setFile(filename);

        int numberOfCars = Integer.parseInt( StdIn.readLine() );
        Vehicle[] vehicles = new Vehicle[numberOfCars];

        for ( int i = 0; i < numberOfCars; i++ ) {
        	String line = StdIn.readLine();
            vehicles[i] = createVehicle(line);
        }
        return vehicles;
    }

    /* 
     * Description:
     *      This method calculates the CO2 emission given several parameters
     * Parameters:
     *      numberOfMonth: 
     *          the lease length in months
     *      usage: 
     *          miles the car can drive per gallon if fuelType is GAS, or
     *			miles the car can drive per kWh    if fuelType is ELECTRIC
     *      mileageAllowance: 
     *			mileage allowance per month
     *		co2PerUnit:
     *			kg of CO2 released with the combustion of each gallon of gasoline, or
     *			kg of CO2 are emitted to generate 1 kWh on average
     * Returns:
     *      this method returns a double representing the CO2 emission produced by the car during the lease.
     */
	public static double computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit ){
		double miles = numberOfMonth * mileageAllowance ;
		return miles / usage*co2PerUnit;
    }

    /* 
     * Description:
     *      This method calculates the cost the fuel during the lease given several parameters
     * Parameters:
     *      numberOfMonth: 
     *          the lease length in months
     *      usage: 
     *          miles the car can drive per gallon if fuelType is GAS, or
     *			miles the car can drive per kWh    if fuelType is ELECTRIC
     *      mileageAllowance: 
     *			mileage allowance per month
     *		fuelPrice: 
     *			price of 1 kWh in cents of a dollar,  if fuelType is GAS, or
     *			price of one gallon of gas in dollars if fuelType is ELECTRIC
     * Returns:
     *      this method returns a double representing the fuel cost during the lease
     */
	public static double computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice){
    	double miles = numberOfMonth * mileageAllowance;
        double cost = miles/usage * fuelPrice;
    	return cost;
    }

    /* 
     * Description:
     *      This method calculates the cost of lease
     * Parameters:
     *      dueAtSigning: 
     *          the dollar amount due at signing the lease
     *      numberOfMonths: 
     *          lease length in months
     *      monthlyCost: 
     *			cost of the lease per month
     * Returns:
     *      this method returns a double representing the lease cost during the entire lease
     */
	public static double computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost){
    	
        return dueAtSigning + numberOfMonths*monthlyCost;
    }

    /* 
     * Description:
     *      This method creates and returns an Vehicle object with name, Lease, and Fuel properly populated based on the given string
     *      
     * Parameters:
     *      one string containing 7~8 fragments descrbing the 
     *   All possible fragments:
     *      type:FULETYPE;
     *          FULETPE can be gas or electric
     *      name:CARNAME;
     *          CARNAME is the name of the car
     *      due:DUEATSIGNING;
     *          DUEATSIGNING is a double number describing the dollar amount due when signing the lease
     *      length:LEASELENGTH;
     *          LEASELENGTH is an integer number describing the lease length in months
     *      monthly:MONTHLYCOST;
     *          MONTHLYCOST is a double number describing the monthly lease cost in dollar
     *      mile/unit:USAGE; 
     *          USAGE is a double describing miles the car can drive per gallon if fuel type is GAS, or miles the car can drive per kWh if fuel type is ELECTRIC
     *      allowance:MILEAGEALLOWANCE;
     *          MILEAGEALLOWANCE is an integer describing the maximum distance the car is allowed to travel per month
     *      charger:CHARGERCOST;
     *          CHARGERCOST is a double describing the cost of charger for electric cars, this fragment can only appear if the line is describing an electrical car
     *   Example of a line:
     *          type:gas.name:civic.due:1000.length:3.monthly:295.mile/unit:34.mileageAllowance:1200.
     *          monthly:238.name:Bolt.due:1000.mileageAllowance:20000.length:15.mile/unit:50.type:electric.charger:500.
     * Returns:
     *      this method creates and returns an Vehicle object with name, Lease, and Fuel properly populated
     *
     * Hint: 
     *      to extract the information of each fragments, we can use 
     *          s.substring(int startIndex, int endIndex) 
     *          s.indexOf(String target)
     *          s.indexOf(char target)
     *
     *      for example, assume we have: 
     *          String s = "description1:ABCD;  description2:EFGH;  description3:IJKL;"
     *      if we want to find the data for description 2, we can first take the substring of the entire string from the letter 'E'
     *      but first we need to find the index of 'E', we can do it by find the index of the string "description2:" and add 13("description2" is 13 chars long)to it
     *      and then we can take the substring from 'E' until the end of the entire string
     *      now use s.substring to exract:
     *          "EFGH;  description3:IJKL;" and let's call it newString for now. 
     *      to extract "EFGH" (the data we want) from newString. we need to find the index of the first ';' which we can simply use newString.indexOf(';')
     *      then we can take the substring of the newString knowing the index of ;
     *      we now have extracted "EFGH" from the String s
     *      the last step is just to convert the datatype based on what type of data we are extracting
     */
	public static Vehicle createVehicle(String description)
    {
        String name = descSubstring(description, "name:");
        String type = descSubstring(description, "type:");
        double due = Double.parseDouble(descSubstring(description, "due:"));
        int leaseLength = Integer.parseInt(descSubstring(description, "length:"));
        double monthlyCost = Double.parseDouble(descSubstring(description, "monthly:"));
        double usage = Double.parseDouble(descSubstring(description, "mile/unit:"));
        int allowance = Integer.parseInt(descSubstring(description, "allowance:"));
        Lease lease = new Lease(due, leaseLength, monthlyCost, allowance);
        if (type.equals("electric")) 
        {
            double charger = Double.parseDouble(descSubstring(description, "charger:"));
            Fuel fuel = new Fuel(usage, charger);return new Vehicle(name, fuel, lease);
        } 
        else 
        {
            Fuel fuel = new Fuel(usage);return new Vehicle(name, fuel, lease);
        }
    }
    private static String descSubstring(String description, String descType) 
    {
        int indexOfSub = description.indexOf(descType) + descType.length();
        String descSub = description.substring(indexOfSub, description.length());
        return description.substring(indexOfSub, indexOfSub + descSub.indexOf(';'));

    }
        /*
        String newDes = description;
        String type = "";
        String name = "";
        String due = "";
        String lease = "";
        String cost = "";
        String usage = "";
        String mileage = "";
        String charge = "";
        int x = 0; int q = 0; int d = 0; int p = 0; int g = 0; int b = 0; int k = 0; int f = 0;
        x = description.indexOf("type:");
        newDes = description.substring(x);
        type = newDes.substring(5, newDes.indexOf(";"));
        g = description.indexOf("name:");
        newDes = description.substring(g);
        name = newDes.substring(5, newDes.indexOf(";"));
        d = description.indexOf("due:");
        newDes = description.substring(d);
        due = newDes.substring(4, newDes.indexOf(";"));
        p = description.indexOf("length:");
        newDes = description.substring(p);
        lease = newDes.substring(6, newDes.indexOf(";"));
        g = description.indexOf("monthly:");
        newDes = description.substring(g);
        cost = newDes.substring(5, newDes.indexOf(";"));
        b = description.indexOf("mile/unit:");
        newDes = description.substring(b);
        usage = newDes.substring(6, newDes.indexOf(";"));
        k = description.indexOf("allowance:");
        newDes = description.substring(k);
        mileage = newDes.substring(8, newDes.indexOf(";"));
        if (description.indexOf("charger:") != -1)
        {
        f = description.indexOf("charge:");
        newDes = description.substring(f);
        charge = newDes.substring(7, newDes.indexOf(";"));
        }
        Lease v = new Lease(Double.parseDouble(due), Integer.parseInt(lease), Double.parseDouble(cost), Integer.parseInt(mileage));
        Fuel m = new Fuel (Double.parseDouble(usage));
        if (type.equals("electric"))
        {
            m = new Fuel (Double.parseDouble(usage), Double.parseDouble(charge));
        }
        Vehicle h = new Vehicle (name, m, v);
	    return h;
    }
        */

    /* 
     * Description:
     *      The method calculates and assign CO2Emission, FuelCost, leastCost, of each vehicle.
     *      
     * Parameters:
     *      vehicles: 
     *          an array of Vehicle objects, initilized by getVehicles() method
     *      gasPrice: 
     *          a double representing the price of gas in dollar/gallon
     *      electricityPrice: 
     *			a double representing the price of gas in dollar/kWh
     * Hint:
     *      **********REMEMBER charger cost for electric cars***************
     *      feel free to use:
     *          computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit )
     *          computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice)
     *          computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost)
     */
	public static void computeCO2EmissionsAndCost( Vehicle[] vehicles, double gasPrice, double electricityPrice )
    {
        for (Vehicle vehicle : vehicles) 
           {
               // Compute Fuel Cost
               if (vehicle.getFuel().getType() == 1) 
               {
                   vehicle.setFuelCost(computeFuelCost(vehicle.getLease().getLeaseLength(),vehicle.getFuel().getUsage(), vehicle.getLease().getMileageAllowance(), gasPrice));
                   double leaseCost = computeLeaseCost(vehicle.getLease().getDueAtSigning(),vehicle.getLease().getLeaseLength(), vehicle.getLease().getMonthlyCost());
                   vehicle.setTotalCost(vehicle.getFuelCost() + leaseCost);vehicle.setCO2Emission(computeCO2(vehicle.getLease().getLeaseLength(),vehicle.getFuel().getUsage(), vehicle.getLease().getMileageAllowance(),Fuel.CO2EMITTED_GASCOMBUSTION));
                }
                else 
                {
                    vehicle.setFuelCost(computeFuelCost(vehicle.getLease().getLeaseLength(),vehicle.getFuel().getUsage(), vehicle.getLease().getMileageAllowance(), electricityPrice));
                    double leaseCost = computeLeaseCost(vehicle.getLease().getDueAtSigning(),vehicle.getLease().getLeaseLength(), vehicle.getLease().getMonthlyCost());vehicle.setTotalCost(vehicle.getFuelCost() + leaseCost + vehicle.getFuel().getCharger());
                    vehicle.setCO2Emission(computeCO2(vehicle.getLease().getLeaseLength(),vehicle.getFuel().getUsage(), vehicle.getLease().getMileageAllowance(),Fuel.CO2EMITTED_ELECTRICITYCOMBUSTION));
                }
            }
        }
        /*
	    for (int n = 0; n < vehicles.length; n++)
            {
                double x = 0.0;
                double y = 0.0;
                double w = 0.0;
                double t = 0.0;
                if (vehicles[n].getFuel().getType() == 1)
                {
                    x = computeCO2(vehicles[n].getLease().getLeaseLength(), vehicles[n].getFuel().getUsage(), vehicles[n].getLease().getMileageAllowance(), 8.887);
                    y = computeFuelCost(vehicles[n].getLease().getLeaseLength(), vehicles[n].getFuel().getUsage(), vehicles[n].getLease().getMileageAllowance(), gasPrice);
                    w = computeLeaseCost(vehicles[n].getLease().getDueAtSigning(), vehicles[n].getLease().getLeaseLength(),vehicles[n].getLease().getMonthlyCost());
                    vehicles[n].setCO2Emission(x);
                    vehicles[n].setFuelCost(y);
                    vehicles[n].setTotalCost(y+w);
                }
                else 
                {
                    x = computeCO2(vehicles[n].getLease().getLeaseLength(), vehicles[n].getFuel().getUsage(), vehicles[n].getLease().getMileageAllowance(), 0.453);
                    y = computeFuelCost(vehicles[n].getLease().getLeaseLength(), vehicles[n].getFuel().getUsage(), vehicles[n].getLease().getMileageAllowance(), electricityPrice);
                    w = computeLeaseCost(vehicles[n].getLease().getDueAtSigning(), vehicles[n].getLease().getLeaseLength(),vehicles[n].getLease().getMonthlyCost());
                    t = vehicles[n].getFuel().getCharger();
                }
                vehicles[n].setCO2Emission(x);
                vehicles[n].setFuelCost(y);
                vehicles[n].setTotalCost(y+w+t);
            }
        }
            */


    /**
     * How to compile:
     *     javac LeasingCost.java
     * How to run:         
     *     java LeasingCost vehicles.txt 3.85 11.0
     **/
	public static void main (String[] args) {
        String filename         = args[0];
        double gasPrice 		= Double.parseDouble( args[1] );
		double electricityPrice = Double.parseDouble( args[2] );

		Vehicle[] vehicles = createAllVehicles(filename); 
		computeCO2EmissionsAndCost(vehicles, gasPrice, electricityPrice);

		for ( Vehicle v : vehicles ) 
            System.out.println(v.toString());
    }
}
