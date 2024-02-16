package DevOps_Team8;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * To generate reports for cities that are sorted by population in the world, in
 * each continent and in each region and extract number of countries and continent or region from user input
 */
public class CityReport {
    private final List<City> cities;
    NumberFormat numberFormat = NumberFormat.getInstance();


    /**
     *Construct method for city report class
     */
    public CityReport(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Sort countries by population in descending order and print them
     */
    public void sortByPopulation() {
        if (cities == null){
            System.out.print("Null City");
        }
        else
            sortByPopulationAndPrint("Cities sorted by population:", cities);
    }

    /**
     * Print list of cities
     * @param message The message will be displayed before cities list
     * @param cities List of cities to display
     */
    private void sortByPopulationAndPrint(String message, List<City> cities) {
        cities.sort(Comparator.comparingInt(City::getPopulation).reversed());
        printCities(message, cities);
    }

    /**
     * Print list of cities
     * @param message The message will be displayed before cities list
     * @param cities List of cities to display
     */
    public void printCities(String message, List<City> cities) {
        System.out.println(message);
        System.out.printf("%-30s %-30s %-30s %-25s%n", "City", "Country", "District", "Population");
        cities.forEach(city -> {
            System.out.printf("%-30s %-30s %-30s %-25s%n",
                    city.getName(), city.getCountry(),city.getDistrict(),
                    numberFormat.format(city.getPopulation()));
        });
        System.out.println(); // Add blank line for separation
    }
}