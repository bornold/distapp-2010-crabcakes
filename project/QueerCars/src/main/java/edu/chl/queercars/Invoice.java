/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars;

/**
 *
 * @author johanssb
 */
public class Invoice {

    long duration;   //in ridiculous milliseconds.
    long durationInHours;
    int mileage; //in kilometers.
    int hourlyFee; //per hour.
    int fuelPrice; //per liter.
    double emissionsFee; //per gram.
    double totalFuelConsumption; //per kilometer.
    int totalFuelCost;
    int totalEmissions;
    int greenCost;
    int hourlyCost;
    int evilCost; //For douchebags.
    int niceGuyCost;
    Model model;

    public Invoice(long duration, int mileage, Model model) {
        this.duration = duration;
        this.mileage = mileage;
        this.model = model;
        durationInHours = 1 + (duration / (1000 * 60 * 60)); //Convert to hours, debit for each started hour.

        //Hard coded price information.
        hourlyFee = 120;
        fuelPrice = 12;
        emissionsFee = 0.01;

        //Calculate
        totalFuelConsumption = mileage * model.getFuelConsumption();
        totalFuelCost = (int) (fuelPrice * totalFuelConsumption); //Rounding up!
        totalEmissions = mileage * model.getEmission();
        hourlyCost = (int) durationInHours * hourlyFee;
        evilCost = hourlyCost + totalFuelCost;
        greenCost = (int) (totalEmissions * emissionsFee);
        niceGuyCost = greenCost + evilCost;
    }

    public String getInvoiceText(){
        String text = "INVOICE:\n";
        text += hourlyCost + " SEK - Rental duration (in hours): " + durationInHours + "\n";
        text += totalFuelCost + " SEK - Fuel consumption (in liters): " + totalFuelConsumption + " calculated from your mileage of " + mileage + " km.\n";
        text += evilCost + " SEK - Total amount due excluding carbon emission compensation.\n\n";
        text += greenCost + " SEK - Optional fee for carbon emissions, calculated from your emissions of " + totalEmissions + " grams.\n";
        text += niceGuyCost + " SEK - Total amount to be payed if you wish to compensate for your carbon emissions.\n";

        return text;
    }
}
