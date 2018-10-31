package refactoring;

import entity.Customer;
import entity.Movie;
import entity.Rental;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleTest {
    private Movie goneWithTheWind = new Movie("Gone With the Wind", 0);
    private Movie leon = new Movie("LEON", 1);
    private Movie shawShank = new Movie("ShawShank", 2);

    @Test
    void should_be_true() {
        assertTrue(true);
    }

    @Test
    void should_get_correct_result_when_rent_one_movie() {
        Customer alice = new Customer("Alice");
        alice.addRental(new Rental(goneWithTheWind, 1));
        String actual = alice.statement();
        assertEquals("Rental record for Alice\n" +
                "\tGone With the Wind\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points", actual);
    }

    @Test
    void should_get_correct_result_when_rent_many_movies() {
        Customer alice = new Customer("Alice");
        alice.addRental(new Rental(goneWithTheWind, 1));
        alice.addRental(new Rental(leon, 2));
        String actual = alice.statement();
        assertEquals("Rental record for Alice\n" +
                "\tGone With the Wind\t2.0\n" +
                "\tLEON\t6.0\n" +
                "Amount owed is 8.0\n" +
                "You earned 3 frequent renter points", actual);
    }

    @Test
    void should_get_correct_result_when_different_customer_rent_movie() {
        Customer alice = new Customer("Alice");
        alice.addRental(new Rental(goneWithTheWind, 1));
        alice.addRental(new Rental(leon, 2));
        alice.addRental(new Rental(shawShank, 3));
        String actual1 = alice.statement();

        Customer amy = new Customer("Amy");
        amy.addRental(new Rental(goneWithTheWind, 4));
        amy.addRental(new Rental(leon, 5));
        amy.addRental(new Rental(shawShank, 6));
        String actual2 = amy.statement();


        assertEquals("Rental record for Alice\n" +
                "\tGone With the Wind\t2.0\n" +
                "\tLEON\t6.0\n" +
                "\tShawShank\t1.5\n" +
                "Amount owed is 9.5\n" +
                "You earned 4 frequent renter points", actual1);
        assertEquals("Rental record for Amy\n" +
                "\tGone With the Wind\t5.0\n" +
                "\tLEON\t15.0\n" +
                "\tShawShank\t6.0\n" +
                "Amount owed is 26.0\n" +
                "You earned 4 frequent renter points", actual2);


    }
}
