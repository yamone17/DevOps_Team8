package DevOps_Team8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * To generate reports for countries that are sorted by population in the world, in
 * each continent and in each region and extract number of countries and continent or region from user input
 */
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
        if (countries == null){
            System.out.print("Null Country");
        }
        else
            sortByPopulationAndPrint("Countries sorted by population:", countries);
    }

    /**
     * Sort countries by population in descending order from each continent and print them
     */
    public void sortByPopulationContinent() {
        Map<String, List<Country>> countriesByContinent = groupCountriesByAttribute(Country::getContinent);
        sortByPopulationAndPrintByAttribute(countriesByContinent);
    }

    /**
     * Sort countries by population in descending form each region and print them
     */
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
    public void printCountries(String message, List<Country> countries) {
        System.out.println(message);
        System.out.printf("%-5s %-50s %-20s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        countries.forEach(country -> System.out.printf("%-5s %-50s %-20s %-25s %-20d %-20s%n",
                country.getCode(), country.getName(), country.getContinent(),
                country.getRegion(), country.getPopulation(), country.getCapital()));
        System.out.println(); // Add blank line for separation
    }

    /**
     * Get number of countries to display in output data
     * @param scanner To get user input data
     * @return number of countries
     */
    private int getNumberOfCountriesFromUserInput(Scanner scanner) {
        System.out.print("Enter the number of countries: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    /**
     * To display data from top n populated countries in the world
     * @return countries data in descending data
     */
    public List<Country> getTopNPopulatedCountriesInWorld() {
        Scanner scanner = new Scanner(System.in);
        int n = getNumberOfCountriesFromUserInput(scanner);
        List<Country> topNCountries = countries.stream()
                .sorted(Comparator.comparingInt(Country::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());
        printCountries("Top " + n + " populated countries in the world:", topNCountries);
        return topNCountries;
    }



    /**
     * Print list of countries from each attribute
     * @param attributeExtractor To extract attribute name from countries
     * @return A map contains list of countries classified by attribute
     */
    public Map<String, List<Country>> groupCountriesByAttribute(Function<Country, String> attributeExtractor) {
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