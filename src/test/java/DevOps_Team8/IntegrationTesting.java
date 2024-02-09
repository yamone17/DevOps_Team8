package DevOps_Team8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for integration testing that classes are performing well with other classes
 */
public class IntegrationTesting {
    static App app;
    static CountryLoader dataLoader;
    static List<Country> countries;
    static CountryReport countryReport;

    /**
     * Connect to database, load data from database and create country arraylist
     */
    @BeforeAll
    static void init()
    {
        app = new App();
        Connection con = app.connect("localhost:33061", 30000);
        dataLoader = new CountryLoader();
        countries = dataLoader.loadCountryData(con);
        countryReport = new CountryReport(countries);
    }

    /**
     * Test sortByPopulation method from CountryReport
     */
    @Test
    void testCountryReportSortByPopulation() {
        // Test sortByPopulation method of CountryReport
        countryReport.sortByPopulation();
        assertEquals("CHN", countries.get(0).getCode());
    }

    /**
     * Test sortByPopulationContinent method from CountryReport
     */
    @Test
    void testCountryReportSortByPopulationContinent() {
        // Test sortByPopulationContinent method of CountryReport
        countryReport.sortByPopulationContinent();

        String firstCountryCodeInAsia = null;
        for (Country country : countries) {
            if (country.getContinent().equals("Asia")) {
                firstCountryCodeInAsia = country.getCode();
                break;
            }
        }

        assertEquals("CHN", firstCountryCodeInAsia);
    }

    /**
     * Test sortByPopulationContinent method from CountryReport
     */
    @Test
    void testCountryReportSortByPopulationRegion() {
        // Test sortByPopulationRegion method of CountryReport
        countryReport.sortByPopulationRegion();

        String firstCountryCodeInEasternAsia  = null;
        for (Country country : countries) {
            if (country.getRegion().equals("Eastern Asia")) {
                firstCountryCodeInEasternAsia = country.getCode();
                break;
            }
        }

        assertEquals("CHN", firstCountryCodeInEasternAsia);
    }
}
