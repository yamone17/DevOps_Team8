package DevOps_Team8;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

/**
 * Class for connect database, load country data, functions and disconnect from database
 */
public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        MySQLSetUp mySQL = new MySQLSetUp();
        if(args.length < 1){
            mySQL.connect("localhost:33060", 30000);
        }else{
            mySQL.connect(args[0], Integer.parseInt(args[1]));
        }


        //Load country data
        CountryLoader countryData = new CountryLoader();
        List<Country> countries = countryData.loadCountryData(mySQL.getConnection());
        CountryReport countryReport = getCountryReport(countries);


        //Load city data
        CityLoader cityData = new CityLoader();
        List<City> cities = cityData.loadCityData(mySQL.getConnection());
        CityReport cityReport = getCityReport(cities);

        // Disconnect from database
        mySQL.disconnect();
    }

    private static CityReport getCityReport(List<City> cities) {
        CityReport report = new CityReport(cities);

        // Sort cities by population
        report.sortByPopulation();

        //Sort cities by population in each continent
        report.sortByPopulationContinent();

        //Sort cities by population in each region
        report.sortByPopulationRegion();

        //Sort cities by population in each country
        report.sortByPopulationCountry();

        //Sort cities by population in each district
        report.sortByPopulationDistrict();

        return report;
    }

    private static CountryReport getCountryReport(List<Country> countries) {
        CountryReport report = new CountryReport(countries);

        // Sort countries by population
        report.sortByPopulation();

        // Sort countries by population in each continent
        report.sortByPopulationContinent();

        // Sort countries by population in each region
        report.sortByPopulationRegion();

        // Display top 5 countries by population
        report.getTopNPopulatedCountriesInWorld(5);

        //Display top 5 countries in asia by population
        report.getTopNPopulatedCountriesInContinent("Asia", 5);

        //Display top 5 countries in southeast asia by population
        report.getTopNPopulatedCountriesInRegion("Southeast Asia", 5);

        return report;
    }
}