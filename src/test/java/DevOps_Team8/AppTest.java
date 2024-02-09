package DevOps_Team8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit testing for function is it work perfect or not
 */
public class AppTest {

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

    @Test
    public void testLoadCountryData() throws SQLException {
        // Mocking database connection and result set
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Mocking query result
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one row in the result set
        when(mockResultSet.getString("Code")).thenReturn("USA");
        when(mockResultSet.getString("Name")).thenReturn("United States");
        when(mockResultSet.getInt("Population")).thenReturn(278357000);
        when(mockResultSet.getString("Continent")).thenReturn("North America");
        when(mockResultSet.getString("Region")).thenReturn("North America");
        when(mockResultSet.getString("Capital")).thenReturn("Washington");

        // Create the CountryLoader instance
        CountryLoader countryLoader = new CountryLoader();

        // Call the method to be tested
        List<Country> countries = countryLoader.loadCountryData(mockConnection);

        // Verify the result
        assertEquals(1, countries.size());
        Country country = countries.get(0);
        assertEquals("USA", country.getCode());
        assertEquals("United States", country.getName());
        assertEquals(278357000, country.getPopulation());
        assertEquals("North America", country.getContinent());
        assertEquals("North America", country.getRegion());
        assertEquals("Washington", country.getCapital());
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

     /**
      * Sort By Population in Descending by user input
     */
    @Test
    void testTopNPopulatedCountriesInWorld() {

        // Execute the method under test
        List<Country> topCountries = countryReport.getTopNPopulatedCountriesInWorld(3);
    }

    /**
     * Sort By Population in Descending Continent by user input
     */
    @Test
    void testTopNPopulatedCountriesContinent() {

        // Execute the method under test
        List<Country> topCountries = countryReport.getTopNPopulatedCountriesInContinent("Asia", 1);
    }

    /**
     * Sort By Population in Descending Region by user input
     */
    @Test
    void testTopNPopulatedCountriesRegion() {
        // Execute the method under test
        List<Country> topCountries = countryReport.getTopNPopulatedCountriesInRegion("Eastern Asia", 1);
    }
}
