package DevOps_Team8;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    static CityReport cityReport;
    private MySQLSetUp mySQL;
    private Connection connection;
    static CapitalReport capitalReport;
    static PopulationReport populationReport;

    /**
     * Create arraylist and add arraylist for testing
     */
    @BeforeEach
    void setCountryReport() {
        // Assuming to fetch country data
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


    @BeforeEach
    void setCityReport() {
        // Assuming to fetch city data
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(new City("Washington", "United States", 572059, "District of Columbia", "North America", "North America"));
        cities.add(new City("New Delhi", "India", 301297, "Delhi", "Asia", "Southern and Central Asia"));
        cities.add(new City("Peking", "China", 7472000, "Peking", "Asia", "Eastern Asia"));
        cityReport = new CityReport(cities);
    }

    @Test
    public void testLoadCityData() throws SQLException {
        // Mocking database connection and result set
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Mocking query result
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("CityName")).thenReturn("Washington");
        when(mockResultSet.getString("CountryName")).thenReturn("United States");
        when(mockResultSet.getInt("CityPopulation")).thenReturn(572059);
        when(mockResultSet.getString("Continent")).thenReturn("North America");
        when(mockResultSet.getString("Region")).thenReturn("North America");
        when(mockResultSet.getString("District")).thenReturn("District of Columbia");

        // Create the CountryLoader instance
        CityLoader cityLoader = new CityLoader();

        // Call the method to be tested
        List<City> cities = cityLoader.loadCityData(mockConnection);

        // Verify the result
        assertEquals(1, cities.size());
        City city = cities.get(0);
        assertEquals("Washington", city.getName());
        assertEquals("United States", city.getCountry());
        assertEquals(572059, city.getPopulation());
        assertEquals("North America", city.getContinent());
        assertEquals("North America", city.getRegion());
        assertEquals("District of Columbia", city.getDistrict());
    }

    @BeforeEach
    void setCapitalReport() {
        // Assuming to fetch capital data
        ArrayList<Capital> capitals = new ArrayList<Capital>();
        capitals.add(new Capital("Washington", "United States", 572059, "North America", "North America"));
        capitals.add(new Capital("New Delhi", "India", 301297, "Asia", "Southern and Central Asia"));
        capitals.add(new Capital("Peking", "China", 7472000, "Asia", "Eastern Asia"));
        capitalReport = new CapitalReport(capitals);
    }

    @Test
    public void testLoadCapitalData() throws SQLException {
        // Mocking database connection and result set
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Mocking query result
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("CapitalCity")).thenReturn("Washington");
        when(mockResultSet.getString("CountryName")).thenReturn("United States");
        when(mockResultSet.getInt("CapitalPopulation")).thenReturn(572059);
        when(mockResultSet.getString("Continent")).thenReturn("North America");
        when(mockResultSet.getString("Region")).thenReturn("North America");

        // Create the CountryLoader instance
        CapitalLoader capitalLoader = new CapitalLoader();

        // Call the method to be tested
        List<Capital> capitals = CapitalLoader.loadCapitalData(mockConnection);

        // Verify the result
        assertEquals(1, capitals.size());
        Capital capital = capitals.get(0);
        assertEquals("Washington", capital.getName());
        assertEquals("United States", capital.getCountry());
        assertEquals(572059, capital.getPopulation());
        assertEquals("North America", capital.getContinent());
        assertEquals("North America", capital.getRegion());
    }

    @Test
    void countryIsNull() {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        countryReport.sortByPopulation();
    }

    @Test
    void countryIsEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countryReport.sortByPopulation();
    }

    /**
     * Sort By Population in Descending
     */
    @Test
    void testSortByPopulation() {
        // Execute the method under test
        ArrayList<Country> countries = new ArrayList<Country>();
        countryReport.sortByPopulation();

        cityReport.sortByPopulation();
        capitalReport.sortByPopulation();
    }

    /**
     * Sort By Population in Descending Continent
     */
    @Test
    void testSortByPopulationContinent() {
        // Execute the method under test
        countryReport.sortByPopulationContinent();
        cityReport.sortByPopulationContinent();
        capitalReport.sortByPopulationContinent();
    }

    /**
     * Sort By Population in Descending Region
     */
    @Test
    void testSortByPopulationRegion() {
        // Execute the method under test
        countryReport.sortByPopulationRegion();
        cityReport.sortByPopulationRegion();
        capitalReport.sortByPopulationRegion();
    }

    @Test
    void testSortByCountry() {
        cityReport.sortByPopulationCountry();
    }

    @Test
    void testSortByDistrict() {
        cityReport.sortByPopulationDistrict();
    }

    /**
     * Sort By Population in Descending by user input
     */
    @Test
    void testTopNPopulatedCountriesInWorld() {
        List<Country> topCountries = countryReport.getTopNPopulatedCountriesInWorld(3);
        List<City> topCities = cityReport.getTopNPopulatedCitiesInWorld(4);
        List<Capital> topCapitals = capitalReport.getTopNPopulatedCapitalsInWorld(3);
    }

    /**
     * Sort By Population in Descending Continent by user input
     */
    @Test
    void testTopNPopulatedCountriesContinent() {
        List<Country> topCountries = countryReport.getTopNPopulatedCountriesInContinent("Asia", 1);
        List<City> topCities = cityReport.getTopNPopulatedCitiesInContinent("Asia",1);
        List<Capital> topCapitals = capitalReport.getTopNPopulatedCapitalsInContinent("Asia", 1);
    }

    /**
     * Sort By Population in Descending Region by user input
     */
    @Test
    void testTopNPopulatedCountriesRegion() {
        List<Country> topCountries = countryReport.getTopNPopulatedCountriesInRegion("Eastern Asia", 1);
        List<City> topCities = cityReport.getTopNPopulatedCitiesInRegion("Eastern Asia", 1);
        List<Capital> topCapitals = capitalReport.getTopNPopulatedCapitalsInRegion("Eastern Asia", 3);
    }

    @BeforeEach
    void setUp() {
        mySQL = new MySQLSetUp();
    }

    @AfterEach
    void tearDown() {
        mySQL.disconnect();
    }

    @Test
    void testConnect() throws SQLException {
        mySQL.connect("localhost:33061", 30); // Assuming MySQL is running locally on port 3306
        Connection connection = mySQL.getConnection();


    }

    @Test
    void testDisconnect() throws SQLException {
        mySQL.connect("localhost:33061", 30); // Assuming MySQL is running locally on port 3306
        Connection connection = mySQL.getConnection();

        mySQL.disconnect();

    }

    @BeforeEach
    public void setPopulationReport() {
        connection = mock(Connection.class);
        populationReport = new PopulationReport(connection);
    }

    @Test
    public void testContinentPopulationReport() throws SQLException {
        // Mock ResultSet for ContinentPopulationReport
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("Name")).thenReturn("Asia");
        when(resultSet.getLong("TotalPopulation")).thenReturn(900937599400L);
        when(resultSet.getLong("PopulationInCities")).thenReturn(697604103L);
        when(resultSet.getLong("PopulationNotInCities")).thenReturn(900239995297L);

        // Mock PreparedStatement and executeQuery
        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Perform test
        populationReport.ContinentPopulationReport();
    }

    @Test
    public void testRegionPopulationReport() throws SQLException {
        // Mock ResultSet for RegionPopulationReport
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("Name")).thenReturn("Eastern Asia");
        when(resultSet.getLong("TotalPopulation")).thenReturn(499721664000L);
        when(resultSet.getLong("PopulationInCities")).thenReturn(317476534L);
        when(resultSet.getLong("PopulationNotInCities")).thenReturn(499404187466L);

        // Mock PreparedStatement and executeQuery
        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Perform test
        populationReport.RegionPopulationReport();
    }

    @Test
    public void testCountryPopulationReport() throws SQLException {
        // Mock ResultSet for CountryPopulationReport
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("Name")).thenReturn("Myanmar");
        when(resultSet.getLong("TotalPopulation")).thenReturn(729776000L);
        when(resultSet.getLong("PopulationInCities")).thenReturn(6203000L);
        when(resultSet.getLong("PopulationNotInCities")).thenReturn(723573000L);

        // Mock PreparedStatement and executeQuery
        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Perform test
        populationReport.CountryPopulationReport();
    }
}
