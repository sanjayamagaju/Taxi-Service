import java.util.ArrayList;
import java.util.Collections;


public class TaxiCompany
{
    //Qn.8
    private ArrayList<TaxiService> taxiServiceList = new ArrayList<TaxiService>();

    //Qn.9
    public void addTaxiService( String passengerCountry, String passengerName, String passengerDestination, int noOfDays){
        this.taxiServiceList.add(new TaxiService( passengerCountry, passengerName, passengerDestination, noOfDays));
    }

    //Qn.10
    public void removeTaxiService(int index){
        try{
            this.taxiServiceList.remove(index);
        } catch(IndexOutOfBoundsException ex) {
            System.out.println("Taxi Service not Available");
        }
    }
    //Qn.11
    public void bookTaxiService(int index, String passengerName, String passengerCountry, String hireDate, String passengerDestination, int noOfDays, double downPayment){
        try{
            TaxiService ts = this.taxiServiceList.get(index);
            ts.booking(passengerName,passengerCountry,hireDate,passengerDestination,noOfDays,downPayment);
        }catch (IndexOutOfBoundsException ex){
            System.out.println("Taxi Service not Available");
        }
    }

    //Qn.12
    public void openTaxiServiceAvailable(int index){
        try{
            TaxiService ts = this.taxiServiceList.get(index);
            ts.defaultGuide();
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Taxi Service not Available");
        }
    }
    //Qn.13
    public void showAvailableTaxiService(int index){
        for (TaxiService ts : taxiServiceList) {
            if(ts.availableStatus == true){
                System.out.println(taxiServiceList.indexOf(index));
                ts.printInfo();
            }
        }
    }
    
        //Question.14//
    public void Finaldisplay() {
        for(int x=0;x<taxiServiceList.size();x++) {
            TaxiService objt = taxiServiceList.get(x);
            if(objt.getAvailableStatus()) {
                  System.out.println(" Passenger-Name:: " + objt.getPassengerName());
                System.out.println(" Passenger-Country:: " + objt.getPassengerCountry());
           
            }
        }
    }
}
