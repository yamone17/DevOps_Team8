package DevOps_Team8;

import java.util.*;
import java.util.function.Function;

public class CountryReport {
    //Declare private variable
    private final List<Country> countries;


    /**
     *Construct method for country report class
     */
    public CountryReport(List<Country> countries) {
        this.countries = countries;
    }

    /**
     * Sort countries by population in descending order and print them
     */
    public void sortByPopulation() {
        sortByPopulationAndPrint("Countries sorted by population:", countries);
    }


    /**
     * Sort the countries list by descending order and display them with a message
     *
     * @param message The message will be displayed before countries list
     * @param countries List of countries to sort
     */
    private void sortByPopulationAndPrint(String message, List<Country> countries) {
        countries.sort(Comparator.comparingInt(Country::getPopulation).reversed());
        printCountries(message, countries);
    }



    /**
     * Print list of countries
     * @param message The message will be displayed before countries list
     * @param countries List of countries to display
     */
    private void printCountries(String message, List<Country> countries) {
        System.out.println(message);
        System.out.printf("%-5s %-50s %-20s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        countries.forEach(country -> System.out.printf("%-5s %-50s %-20s %-25s %-20s %-20s%n",
                country.getCode(), country.getName(), country.getContinent(),
                country.getRegion(), country.getPopulation(), country.getCapital()));
        System.out.println(); // Add blank line for separation
    }


}
