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
     * Construct method for city report class
     */
    public CityReport(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Sort countries by population in descending order and print them
     */
    public void sortByPopulation() {
        if (cities == null) {
            System.out.print("Null City");
        } else
            sortByPopulationAndPrint("Cities sorted by population:", cities);
    }

    /**
     * Sort cities by population in descending order from each continent and print them
     */
    public void sortByPopulationContinent() {
        Map<String, List<City>> citiesByContinent = groupCitiesByAttribute(City::getContinent);
        sortByPopulationAndPrintByAttribute(citiesByContinent);
    }

    /**
     * Sort cities by population in descending order from each region and print them
     */
    public void sortByPopulationRegion() {
        Map<String, List<City>> citiesByRegion = groupCitiesByAttribute(City::getRegion);
        sortByPopulationAndPrintByAttribute(citiesByRegion);
    }

    /**
     * Sort cities by population in descending order from each district and print them
     */
    public void sortByPopulationDistrict() {
        Map<String, List<City>> citiesByDistrict = groupCitiesByAttribute(City::getDistrict);
        sortByPopulationAndPrintByAttribute(citiesByDistrict);
    }

    /**
     * Sort cities by population in descending order from each country and print them
     */
    public void sortByPopulationCountry() {
        Map<String, List<City>> citiesByCountry = groupCitiesByAttribute(City::getCountry);
        sortByPopulationAndPrintByAttribute(citiesByCountry);
    }

    /**
     * Print list of cities
     *
     * @param message The message will be displayed before cities list
     * @param cities  List of cities to display
     */
    private void sortByPopulationAndPrint(String message, List<City> cities) {
        cities.sort(Comparator.comparingInt(City::getPopulation).reversed());
        printCities(message, cities);
    }

    /**
     * Sort the cities list by descending order in each attribute and display them
     *
     * @param citiesByAttribute List of cities that are classified by attribute
     */
    private void sortByPopulationAndPrintByAttribute(Map<String, List<City>> citiesByAttribute) {
        citiesByAttribute.forEach((attribute, cities) ->
                sortByPopulationAndPrint(String.format("Cities in %s sorted by population:", attribute), cities)
        );
    }

    /**
     * Print list of cities from each attribute
     *
     * @param attributeExtractor To extract attribute name from cities
     * @return A map contains list of cities classified by attribute
     */
    public Map<String, List<City>> groupCitiesByAttribute(Function<City, String> attributeExtractor) {
        Map<String, List<City>> citiesByAttribute = new HashMap<>();
        for (City city : cities) {
            String attribute = attributeExtractor.apply(city);
            citiesByAttribute
                    .computeIfAbsent(attribute, k -> new ArrayList<>())
                    .add(city);
        }
        return citiesByAttribute;
    }

    /**
     * Print list of cities
     *
     * @param message The message will be displayed before cities list
     * @param cities  List of cities to display
     */
    public void printCities(String message, List<City> cities) {
        System.out.println(message);
        System.out.printf("%-30s %-30s %-30s %-25s%n", "City", "Country", "District", "Population");
        cities.forEach(city -> {
            System.out.printf("%-30s %-30s %-30s %-25s%n",
                    city.getName(), city.getCountry(), city.getDistrict(),
                    numberFormat.format(city.getPopulation()));
        });
        System.out.println(); // Add blank line for separation
    }
    public List<City> getTopNPopulatedCitiesInWorld(int n) {
        List<City> topNCities = cities.stream()
                .sorted(Comparator.comparingInt(City::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());
        printCities("Top " + n + " populated cities in the world:", topNCities);
        return topNCities;
    }
    public List<City> getTopNPopulatedCitiesInContinent(String continent, int n) {
        List<City> topNCities = cities.stream()
                .filter(city -> city.getContinent().equalsIgnoreCase(continent))
                .sorted(Comparator.comparingInt(City::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());

        printCities("Top " + n + " populated countries in " + continent + ":", topNCities);
        return topNCities;
    }
    public List<City> getTopNPopulatedCitiesInRegion(String region, int n) {
        List<City> topNCities = cities.stream()
                .filter(city -> city.getRegion().equalsIgnoreCase(region))
                .sorted(Comparator.comparingInt(City::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());

        printCities("Top " + n + " populated countries in " + region + ":", topNCities);
        return topNCities;
    }
    public List<City> getTopNPopulatedCitiesInCountry(String country, int n) {
        List<City> topNCities = cities.stream()
                .filter(city -> city.getCountry().equalsIgnoreCase(country))
                .sorted(Comparator.comparingInt(City::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());

        printCities("Top " + n + " populated countries in " + country + ":", topNCities);
        return topNCities;
    }

}

