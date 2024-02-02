package DevOps_Team8;

import java.util.Comparator;
import java.util.List;

/**
 * Functions for Country Report: Get country data from database and display data
 */
public class CountryReport {
    private final List<Country> countries;


    /**
     * Contract country data object
     * @param countries
     */
    public CountryReport(List<Country> countries) {
        this.countries = countries;
    }

    /**
     * Sort all country around the world by population descending and display data
     */
    public void sortByPopulation() {
        countries.sort(Comparator.comparingInt(Country::getPopulation).reversed());
        System.out.println("Countries sorted by population:");
        System.out.printf("%-5s %-50s %-20s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population", "Capital");
        for (Country country : countries) {
            System.out.printf("%-5s %-50s %-20s %-25s %-20s %-20s%n",
                    country.getCode(), country.getName(), country.getContinent(),
                    country.getRegion(), country.getPopulation(), country.getCapital());
        }
    }
}