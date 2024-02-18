package DevOps_Team8;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CapitalReport {
    private final List<Capital> capitals;
    NumberFormat numberFormat = NumberFormat.getInstance();

    public CapitalReport(List<Capital> capitals) {
        this.capitals = capitals;
    }

    public void sortByPopulation() {
        if (capitals == null){
            System.out.print("Null Capital");
        }
        else
            sortByPopulationAndPrint("Capitals sorted by population:", capitals);
    }

    private void sortByPopulationAndPrint(String message, List<Capital> capitals) {
        capitals.sort(Comparator.comparingInt(Capital::getPopulation).reversed());
        printCapital(message, capitals);
    }

    public void sortByPopulationContinent() {
        Map<String, List<Capital>> capitalsByContinent = groupCapitalsByAttribute(Capital::getContinent);
        sortByPopulationAndPrintByAttribute(capitalsByContinent);
    }

    public void sortByPopulationRegion() {
        Map<String, List<Capital>> capitalsByRegion = groupCapitalsByAttribute(Capital::getRegion);
        sortByPopulationAndPrintByAttribute(capitalsByRegion);
    }

    private void sortByPopulationAndPrintByAttribute(Map<String, List<Capital>> citiesByAttribute) {
        citiesByAttribute.forEach((attribute, cities) ->
                sortByPopulationAndPrint(String.format("Cities in %s sorted by population:", attribute), cities)
        );
    }

    public Map<String, List<Capital>> groupCapitalsByAttribute(Function<Capital, String> attributeExtractor) {
        Map<String, List<Capital>> capitalsByAttribute = new HashMap<>();
        for (Capital capital : capitals) {
            String attribute = attributeExtractor.apply(capital);
            capitalsByAttribute
                    .computeIfAbsent(attribute, k -> new ArrayList<>())
                    .add(capital);
        }
        return capitalsByAttribute;
    }

    public List<Capital> getTopNPopulatedCapitalsInWorld(int n) {
        List<Capital> topNCapitals = capitals.stream()
                .sorted(Comparator.comparingInt(Capital::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());
        printCapital("Top " + n + " populated countries in the world:", topNCapitals);
        return topNCapitals;
    }

    public List<Capital> getTopNPopulatedCapitalsInContinent(String continent, int n) {
        List<Capital> topNCapitals = capitals.stream()
                .filter(capital -> capital.getContinent().equalsIgnoreCase(continent))
                .sorted(Comparator.comparingInt(Capital::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());

        printCapital("Top " + n + " populated countries in " + continent + ":", topNCapitals);
        return topNCapitals;
    }

    public List<Capital> getTopNPopulatedCapitalsInRegion(String region, int n) {
        List<Capital> topNCapitals = capitals.stream()
                .filter(capital -> capital.getRegion().equalsIgnoreCase(region))
                .sorted(Comparator.comparingInt(Capital::getPopulation).reversed())
                .limit(n)
                .collect(Collectors.toList());

        printCapital("Top " + n + " populated countries in " + region + ":", topNCapitals);
        return topNCapitals;
    }

    public void printCapital(String message, List<Capital> capitals) {
        System.out.println(message);
        System.out.printf("%-30s %-40s %-25s%n", "Capital", "Country", "Population");
        capitals.forEach(capital -> {
            System.out.printf("%-30s %-40s %-25s%n",
                    capital.getName(), capital.getCountry(),numberFormat.format(capital.getPopulation()));
        });
        System.out.println(); // Add blank line for separation
    }
}