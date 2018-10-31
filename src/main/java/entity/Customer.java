package entity;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        this._name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            switch (each.getMovie().getPricecode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysrented() > 2) {
                        thisAmount += (each.getDaysrented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysrented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysrented() > 3) {
                        thisAmount += (each.getDaysrented() - 3) * 1.5;
                    }
                    break;
                default:
                    break;
            }

            frequentRenterPoints ++;

            if ((each.getMovie().getPricecode() == Movie.NEW_RELEASE) && each.getDaysrented() > 1) {
                frequentRenterPoints ++;
            }

            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }
}