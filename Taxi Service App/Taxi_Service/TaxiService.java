
public class TaxiService
{
   //Qn.1
    public String taxiDriver;
    public String passengerName;
    public String passengerCountry;
    public boolean availableStatus;
    public String hireDate;
    public int noOfDays;
    public double dailyRate;
    public double downPayment;
    public String passengerDestination;

    //Qn.2
    public TaxiService(String passengerCountry , String passengerName, String passengerDestination, int noOfDays) {
        this.taxiDriver =taxiDriver;
        this.dailyRate =dailyRate;
        this.passengerName = "";
        this.passengerCountry= "";
        this.passengerDestination= "";
        this.hireDate= "";
        this.noOfDays= 0;
        this.downPayment= 0.0;
        this.availableStatus= true;
    }

    //Qn.3
    public String getTaxiDriver(){
        return this.taxiDriver;
    }

    public String getPassengerName(){
        return this.passengerName;
    }

    public String getPassengerCountry(){
        return this.passengerCountry;
    }

    public String getPassengerDestination(){
        return this.passengerDestination;
    }

    public String getHireDate(){
        return this.hireDate;
    }

    public int getNoOfDays(){
        return this.noOfDays;
    }

    public double getDailyRate(){
        return this.dailyRate;
    }

    public double getDownPayment(){
        return this.downPayment;
    }

    public boolean getAvailableStatus(){
        return this.availableStatus;
    }


    //Qn.4
    public void setDailyRate(double rate){
        this.dailyRate = rate;
    }

    public void setDuration(int days){
        this.noOfDays = days;
    }

    //Qn.5
    public void booking(String passenger_name,String passenger_country,String date,String passenger_destination,int passenger_nofdays,double downpayment){
        if (this.availableStatus == true){
            this.passengerName = passenger_name;
            this.passengerCountry = passenger_country;
            this.hireDate = date;
            this.passengerDestination = passenger_destination;
            this.noOfDays = passenger_nofdays;
            this.downPayment = downpayment;
            this.availableStatus = false;
        }else{
            System.out.println("The taxi service is not available"+ this.noOfDays);
        }
    }

    //Qn.6
    public void defaultGuide(){
        if(this.availableStatus == true){
            System.out.println("The taxi service is available to book");
        }
        else{
            this.passengerName = "";
            this.passengerCountry = "";
            this.noOfDays = 0;
            this.passengerDestination = "";
            this.hireDate = "";
            this.downPayment= 0.0;
        }
    }

    //Qn.7
    public void printInfo(){
        System.out.println("Taxi Driver : " + this.taxiDriver);
        System.out.println("Daily Rate : " + this.dailyRate);
    }
}
