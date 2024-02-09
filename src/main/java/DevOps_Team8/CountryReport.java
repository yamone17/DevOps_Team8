package DevOps_Team8;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountryReport {
    private final List<Country> countries;

    public CountryReport(List<Country> countries) {
        this.countries = countries;
    }

    public void sortByPopulation() {
        if (countries == null){
            System.out.print("Null Country");
        }
        else
            sortByPopulationAndPrint("Countries sorted by population:", countries);
    }

    public void sortByPopulationContinent() {
        Map<String, List<Country>> countriesByContinent = groupCountriesByAttribute(Country::getContinent);
        sortByPopulationAndPrintByAttribute(countriesByContinent);
    }

    public void sortByPopulationRegion() {
        Map<String, List<Country>> countriesByRegion = groupCountriesByAttribute(Country::getRegion);
        sortByPopulationAndPrintByAttribute(countriesByRegion);
    }

    private void sortByPopulationAndPrint(String message, List<Country> countries) {
        countries.sort(Comparator.comparingInt(Country::getPopulation).reversed());
        printCountries(message, countries);
    }

    private void sortByPopulationAndPrintByAttribute(Map<String, List<Country>> countriesByAttribute) {
        countriesByAttribute.forEach((attribute, countries) ->
                sortByPopulationAndPrint(String.format("Countries in %s sorted by population:", attribute), countries)
        );
    }

    public void printCountries(String message, List<Country> countries) {
        System.out.println(message);
        System.out.printf("%-5s %-50s %-20s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        countries.forEach(country -> System.out.printf("%-5s %-50s %-20s %-25s %-20d %-20s%n",
                country.getCode(), country.getName(), country.getContinent(),
                country.getRegion(), country.getPopulation(), country.getCapital()));
        System.out.println();
    }

    public List<Country> getTopNPopulatedCountriesInWorld(int n) {
        List<Country> topNCountries = countries.stream()
                .sorted(Comparator.comparingInt(Country::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());
        printCountries("Top " + n + " populated countries in the world:", topNCountries);
        return topNCountries;
    }

    public List<Country> getTopNPopulatedCountriesInContinent(String continent, int n) {
        List<Country> topNCountries = countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .sorted(Comparator.comparingInt(Country::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());

        printCountries("Top " + n + " populated countries in " + continent + ":", topNCountries);
        return topNCountries;
    }

    public List<Country> getTopNPopulatedCountriesInRegion(String region, int n) {
        List<Country> topNCountries = countries.stream()
                .filter(country -> country.getRegion().equalsIgnoreCase(region))
                .sorted(Comparator.comparingInt(Country::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());

        printCountries("Top " + n + " populated countries in " + region + ":", topNCountries);
        return topNCountries;
    }

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