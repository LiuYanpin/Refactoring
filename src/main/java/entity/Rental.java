package entity;

public class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        this._movie = movie;
        this._daysRented = daysRented;
    }

    public int getDaysrented() {
        return _daysRented;
    }
    public Movie getMovie() {
        return _movie;
    }

    double getCharge() {
        double result = 0;
        switch (getMovie().getPricecode()) {
            case Movie.REGULAR:
                result += 2;
                if (getDaysrented() > 2) {
                    result += (getDaysrented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += getDaysrented() * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if (getDaysrented() > 3) {
                    result += (getDaysrented() - 3) * 1.5;
                }
                break;
            default:
                break;
        }
        return result;
    }
}
