package DevOps_Team8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Class for integration testing that classes are performing well with other classes
 */
public class IntegrationTesting {
    static MySQLSetUp mySQL;
    static CountryLoader countryLoader;
    static List<Country> countries;
    static CountryReport countryReport;
    static CityLoader cityLoader;
    static List<City> cities;
    static CityReport cityReport;

    /**
     * Connect to database, load data from database and create country arraylist
     */
    @BeforeAll
    static void init() {
        mySQL = new MySQLSetUp();
        mySQL.connect("localhost:33061", 30000);

        countryLoader = new CountryLoader();
        countries = countryLoader.loadCountryData(mySQL.getConnection());
        countryReport = new CountryReport(countries);

        cityLoader = new CityLoader();
        cities = cityLoader.loadCityData(mySQL.getConnection());
        cityReport = new CityReport(cities);
    }

    /**
     * Test sortByPopulation method from CountryReport
     */
    @Test
    void countrySortByPopulation() {
        countryReport.sortByPopulation();
        assertEquals("CHN", countries.get(0).getCode());
    }

    /**
     * Test sortByPopulationContinent method from CountryReport
     */
    @Test
    void countrySortByPopulationContinent() {

        // Get countries in South America
        List<Country> countriesInSouthAmerica = countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase("South America"))
                .collect(Collectors.toList());

        // Sort countries in South America by population
        countriesInSouthAmerica.sort(Comparator.comparingInt(Country::getPopulation).reversed());

        // Get the code of the most populated country in South America
        String mostPopulatedCountryInSouthAmerica = countriesInSouthAmerica.get(0).getCode();

        assertEquals("BRA", mostPopulatedCountryInSouthAmerica);
    }

    /**
     * Test sortByPopulationContinent method from CountryReport
     */
    @Test
    void countrySortByPopulationRegion() {
        // Get countries in South America
        List<Country> countriesInSouthAmerica = countries.stream()
                .filter(country -> country.getRegion().equalsIgnoreCase("South America"))
                .collect(Collectors.toList());

        // Sort countries in South America by population
        countriesInSouthAmerica.sort(Comparator.comparingInt(Country::getPopulation).reversed());

        // Get the code of the most populated country in South America
        String mostPopulatedCountryInSouthAmerica = countriesInSouthAmerica.get(0).getCode();

        assertEquals("BRA", mostPopulatedCountryInSouthAmerica);
    }

    /**
     * Test sortByPopulationNCountry method from CountryReport
     */
    @Test
    void countryTopNPopulatedCountriesInWorld() {
        List<Country> topNCountries = countryReport.getTopNPopulatedCountriesInWorld(3);
        assertEquals(3, topNCountries.size());
        assertEquals("CHN", topNCountries.get(0).getCode());
    }

    /**
     * Test sortByPopulationNCountryContinent method from CountryReport
     */
    @Test
    void countryTopNPopulatedCountriesInContinent() {
        List<Country> topNCountries = countryReport.getTopNPopulatedCountriesInContinent("Asia", 3);
        assertEquals(3, topNCountries.size());
        assertEquals("CHN", topNCountries.get(0).getCode());
    }

    /**
     * Test sortByPopulationNRegion method from CountryReport
     */
    @Test
    void countryTopNPopulatedCountriesInRegion() {
        List<Country> topNCountries = countryReport.getTopNPopulatedCountriesInRegion("Southeast Asia", 3);
        assertEquals(3, topNCountries.size());
        assertEquals("IDN", topNCountries.get(0).getCode());
    }

    /**
     * Test print countries function
     */
    @Test
    void countryPrintCountries() {
        countryReport.printCountries("Test Message", countries);
        // No assertions needed as it's a void method
    }

    /**
     * Test attribute function
     */
    @Test
    void countryGroupCountriesByAttribute() {
        // Test grouping by continent
        String continent = "North America";
        int expectedSize = 37; // Number of countries in North America
        List<Country> countriesInContinent = countryReport.groupCountriesByAttribute(Country::getContinent).get(continent);
        assertNotNull(countriesInContinent);
        assertEquals(expectedSize, countriesInContinent.size());
    }

    /**
     * Test city sort by population
     */
    @Test
    void citySortByPopulation() {
        cityReport.sortByPopulation();
        assertEquals("Mumbai (Bombay)", cities.get(0).getName());
        assertEquals("Adamstown", cities.get(cities.size() - 1).getName());
    }

    /**
     * Test city sort by population continent
     */
    @Test
    void citySortByPopulationContinent() {
        List<City> citiesInMyanmar = cities.stream()
                .filter(city -> city.getCountry().equalsIgnoreCase("Myanmar"))
                .collect(Collectors.toList());

        // Sort countries in South America by population
        citiesInMyanmar.sort(Comparator.comparingInt(City::getPopulation).reversed());

        // Get the code of the most populated country in South America
        String mostPopulatedCityInMyanmar = citiesInMyanmar.get(0).getName();

        assertEquals("Rangoon (Yangon)", mostPopulatedCityInMyanmar);
    }
}
