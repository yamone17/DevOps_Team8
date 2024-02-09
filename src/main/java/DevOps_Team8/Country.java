package DevOps_Team8;

import java.util.Locale;

/**
 * Define a country with its attributes
 */
public class Country {
    private String Code;
    private String Name;
    private int Population;

    private String Continent;

    private String Region;

    private String Capital;

    /**
     * Constructs new country object with the given attributes.
     * @param code
     * @param name
     * @param population
     * @param continent
     * @param region
     * @param capital
     */
    public Country(String code, String name, int population, String continent, String region, String capital) {
        Code = code;
        Name = name;
        Population = population;
        Continent = continent;
        Region = region;
        Capital = capital;
    }

    /**
     * Get Country Code
     * @return Code
     */
    public String getCode() {
        return Code;
    }

    /**
     * Get Country Name
     * @return Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Get Country Population
     * @return Population
     */
    public int getPopulation() {
        return Population;
    }

    /**
     * Get Country Continent
     * @return Continent
     */
    public String getContinent() {
        return Continent;
    }

    /**
     * Get Country Region
     * @return Region
     */
    public String getRegion() {
        return Region;
    }

    /**
     * Get Country Capital
     * @return Capital
     */
    public String getCapital() {
        return Capital;
    }
}