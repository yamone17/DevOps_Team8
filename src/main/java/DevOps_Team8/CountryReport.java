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
     * Sort countries by population in descending order from each continent and print them
     */
    public void sortByPopulationContinent() {
        Map<String, List<Country>> countriesByContinent = groupCountriesByAttribute(Country::getContinent);
        sortByPopulationAndPrintByAttribute(countriesByContinent);
    }

    public void sortByPopulationRegion() {
        Map<String, List<Country>> countriesByRegion = groupCountriesByAttribute(Country::getRegion);
        sortByPopulationAndPrintByAttribute(countriesByRegion);
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
     * Sort the countries list by descending order in each attribute and display them
     * @param countriesByAttribute List of countries that are classified by attribute
     */
    private void sortByPopulationAndPrintByAttribute(Map<String, List<Country>> countriesByAttribute) {
        countriesByAttribute.forEach((attribute, countries) ->
                sortByPopulationAndPrint(String.format("Countries in %s sorted by population:", attribute), countries)
        );
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

    /**
     * Print list of countries from each attribute
     * @param attributeExtractor To extract attribute name from countries
     * @return A map contains list of countries classified by attribute
     */
    private Map<String, List<Country>> groupCountriesByAttribute(Function<Country, String> attributeExtractor) {
        Map<String, List<Country>> countriesByAttribute = new HashMap<>();
        for (Country country : countries) {
            String attribute = attributeExtractor.apply(country);
            countriesByAttribute
                    .computeIfAbsent(attribute, k -> new ArrayList<>())
                    .add(country);
        }
        return countriesByAttribute;
    }
}
