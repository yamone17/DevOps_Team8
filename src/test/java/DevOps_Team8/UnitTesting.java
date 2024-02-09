package DevOps_Team8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testing for function is it work perfect or not
 */
public class UnitTesting {

    static CountryReport countryReport;

    /**
     * Create arraylist and add arraylist for testing
     */
    @BeforeAll
    static void init() {
        // Assuming you have a method to fetch country data
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("USA", "United States", 278357000, "North America", "North America", "Washington"));
        countries.add(new Country("IND", "India", 1013662000, "Asia", "Southern and Central Asia", "New Delhi"));
        countries.add(new Country("CHN", "China", 1277558000, "Asia", "Eastern Asia", "Peking"));
        countryReport = new CountryReport(countries);
    }

    /**
     * Sort By Population in Descending
     */
    @Test
    void testSortByPopulation() {
        // Execute the method under test
        countryReport.sortByPopulation();
    }

    /**
     * Sort By Population in Descending Continent
     */
    @Test
    void testSortByPopulationContinent() {
        // Execute the method under test
        countryReport.sortByPopulationContinent();
    }

    /**
     * Sort By Population in Descending Region
     */
    @Test
    void testSortByPopulationRegion() {
        // Execute the method under test
        countryReport.sortByPopulationRegion();
    }

}
