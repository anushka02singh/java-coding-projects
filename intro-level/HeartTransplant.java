/**
 * 
 * HeartTransplant class
 * 
 * @author Ana Paula Centeno
 * @author Haolin (Daniel) Jin
 */
public class HeartTransplant 
{

    // patient array, each Patient is read from the data file
    private Patient[] patients;

    // SurvivabilityByAge array, each rate is read from data file
    private SurvivabilityByAge survivabilityByAge;

    // SurvivabilityByCause array, each rate is read from data file
    private SurvivabilityByCause survivabilityByCause;

    /*
     * Default constructor
     * Initializes patients to null.
     * Initializes survivabilityByAge to null.
     * Initializes survivabilityByCause to null. 
     */
    public HeartTransplant() 
    {
        this.patients = null;
        this.survivabilityByAge = null;
        this.survivabilityByCause = null;
    }

    /*
     * Returns patients
     */
    public Patient[] getPatients() 
    {

        return this.patients;
     } 

    /*
     * Returns survivabilityByAge
     */
    public SurvivabilityByAge getSurvivabilityByAge() 
    {
        return this.survivabilityByAge;
    }

    /*
     * Returns survivabilityByCause
     */
    public SurvivabilityByCause getSurvivabilityByCause() 
    {
        return this.survivabilityByCause;
    }

    /*
     * 1) Initialize the instance variable patients array with numberOfLines length.
     * 
     * 2) Reads from the command line data file, use StdIn.readInt() to read an integer.
     *    File Format: 
     *      ID, ethnicity, Gender, Age, Cause, Urgency, State of health
     * 
     *    Each line refers to one Patient, all values are integers.
     * 
     */
    public void readPatients (int numberOfLines) 
    {
        this.patients = new Patient[numberOfLines];
        for(int i = 0; i < patients.length; i++)
        {
            int id = StdIn.readInt();
            int ethnicity = StdIn.readInt();
            int gender = StdIn.readInt();
            int age = StdIn.readInt();
            int cause = StdIn.readInt();
            int urgency = StdIn.readInt();
            int stateOfHealth = StdIn.readInt();
            this.patients[i] = new Patient(id, ethnicity, gender, age, cause, urgency, stateOfHealth);
        } 

    }

    /*
     * 1) Initialize the instance variable survivabilityByAge with a new survivabilityByAge object.
     * 
     * 2) Reads from the command line file to populate the object. 
     *    Use StdIn.readInt() to read an integer and StdIn.readDouble() to read a double.
     * 
     *    File Format: Age YearsPostTransplant Rate
     *    Each line refers to one survivability rate by age.
     * 
     */
    public void readSurvivabilityByAge (int numberOfLines) 
    {
        this.survivabilityByAge = new SurvivabilityByAge();
        for (int i = 0; i < numberOfLines; i++)
        {    
            int age = StdIn.readInt();
            int year = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByAge.addData(age, year, rate);
        }
    }

    /*
     * 1) Initialize the instance variable survivabilityByCause with a new survivabilityByCause object.
     * 
     * 2) Reads from the command line file to populate the object. Use StdIn.readInt() to read an 
     *    integer and StdIn.readDouble() to read a double.
     * 
     *    File Format: Cause YearsPostTransplant Rate
     *    Each line refers to one survivability rate by cause.
     * 
     */
    public void readSurvivabilityByCause (int numberOfLines) 
    {
        this.survivabilityByCause = new SurvivabilityByCause();
        for (int i = 0; i < numberOfLines; i++)
        {    
            int cause = StdIn.readInt();
            int year = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByCause.addData(cause, year, rate);
        }
    }
    
    /*
     * Returns a Patient array containing the patients, 
     * from the patients array, that have age above the parameter age.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of Patients with age above the parameter age.
     * 
     * Return null if there is no Patient with age above the 
     * parameter age.
     */ 
    public Patient[] getPatientsWithAgeAbove(int age) 
    {
        ArrayList<Patient> tempPatients = new ArrayList<Patient>();
        for (Patient p: this.patients) 
        {
            if (p.getAge() >= age) 
            {
                tempPatients.add(p);
            }

        }

        if (tempPatients.isEmpty()) 
        {
            return null;

        } 
        else 
        {
            // convert arraylist to an array to return
            return tempPatients.toArray(new Patient[tempPatients.size()]);
        }

    }

    /*
     * Returns a Patient array containing the patients, from the patients array, 
     * that have the heart condition cause equal to the parameter cause.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of Patients with the heart condition cause equal to the parameter cause.
     * 
     * Return null if there is no Patient with the heart condition cause 
     * equal to the parameter cause.
     */ 
    public Patient[] getPatientsByHeartConditionCause(int cause) 
    {
        ArrayList<Patient> tempPatients = new ArrayList<Patient>();
        for (Patient p: this.patients) 
        {
            if (p.getCause() >= cause) 
            {
                tempPatients.add(p);
            }

        }

        if (tempPatients.isEmpty()) 
        {
            return null;

        } 
        else 
        {
            return tempPatients.toArray(new Patient[tempPatients.size()]);
        }
    }

    /*
     * Returns a Patient array containing patients, from the patients array,
     * that have the state of health equal to the parameter state.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of Patients with the state of health equal to the parameter state.
     * 
     * Return null if there is no Patient with the state of health 
     * equal to the parameter state.
     */ 
    public Patient[] getPatientsByUrgency(int urgency) 
    {

        ArrayList<Patient> tempPatients = new ArrayList<Patient>();
        for (Patient p: this.patients) 
        {
            if (p.getUrgency() >= urgency) 
            {
                tempPatients.add(p);
            }

        }

        if (tempPatients.isEmpty()) 
        {
            return null;

        } 
        else 
        {
            return tempPatients.toArray(new Patient[tempPatients.size()]);
        }
    }

    /*
     * Assume there is a heart available for transplantation surgery.
     * Also assume that the heart is of the same blood type as the
     * Patients on the patients array.
     * This method finds the Patient to be the recepient of this
     * heart.
     * 
     * The method returns a Patient from the patients array with
     * he highest potential for survivability after the transplant.
     * 
     * Assume the patient returned by this method will receive a heart,
     * therefore the Patient will no longer need a heart.
     * 
     * There is no correct solution, you may come up with any 
     * function to find the patient with the highest potential 
     * for survivability after the transplant.
     */ 
    public Patient getPatientForTransplant ()
    {
        ArrayList<Patient> urgency9 = new ArrayList<Patient>();
        ArrayList<Patient> urgency8 = new ArrayList<Patient>();
        double rate9 = 0; // highest survivability rate among urgency = 9 patients
        double rate8 = 0; // highest survivability rate among urgency = 8 patients
        for (Patient p: this.patients) {
            if (p.getNeedHeart()) {
                // year is completely arbitrary cause there's no patient info on it?
                double ageRate = survivabilityByAge.getRate(p.getAge(), 1);
                double causeRate = survivabilityByCause.getRate(p.getAge(), 1);
                double currRate = (ageRate + causeRate)/ 2;
                if (p.getUrgency() == 9) {
                    if (urgency9.isEmpty()) {
                        urgency9.add(p);
                        rate9 = currRate;
                    } else {
                        // get survivability rate
                        if (rate9 == currRate) {
                            urgency9.add(p);
                        } else if (currRate > rate9) {
                            // remove existing patients from list and start over
                            urgency9 = new ArrayList<Patient>();
                            urgency9.add(p);
                            rate9 = currRate;
                        } else {
                            // do nothing
                        }
                    }
                } else { // p.getUrgency == 7
                    if (urgency8.isEmpty()) {
                        urgency8.add(p);
                        rate8 = currRate;
                    } else {
                        // get survivability rate
                        if (rate8 == currRate) {
                            urgency8.add(p);
                        } else if (currRate > rate8) {
                            // remove existing patients from list and start over
                            urgency8 = new ArrayList<Patient>();
                            urgency8.add(p);
                            rate8 = currRate;
                        } else {
                            // do nothing
                        }
                    }
                }
            }
            
        }

        // start with 9, then 8
        if (urgency9.isEmpty() & urgency8.isEmpty()) {
            return null; // no patient that needs it
        } else {
            if (urgency9.isEmpty()) {
                // grab one from 8
                Patient next = urgency8.get(0);
                next.setNeedHeart(false);
                return next;
            } else {
                Patient next = urgency9.get(0);
                next.setNeedHeart(false);
                return next;
            }
        }  

    }
}
