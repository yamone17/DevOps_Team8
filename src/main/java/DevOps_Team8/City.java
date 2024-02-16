package DevOps_Team8;

/**
 * Define a city with its attributes
 */
public class City {
    private String Name;
    private String Country;
    private int Population;
    private String District;
    private String Continent;
    private String Region;

    /**
     * Constructs new city object with the given attributes.
     * @param name city name
     * @param country country name
     * @param population city population
     * @param district district name
     * @param continent continent name
     * @param region region name
     */
    public City(String name, String country, int population, String district, String continent, String region) {
        Name = name;
        Country = country;
        Population = population;
        District = district;
        Continent = continent;
        Region = region;
    }

    /**
     * Get city name
     * @return city name
     */
    public String getName() {
        return Name;
    }

    /**
     * Get country name
     * @return country name
     */
    public String getCountry() {
        return Country;
    }

    /**
     * Get city population
     * @return city population
     */
    public int getPopulation() {
        return Population;
    }

    /**
     * Get district name
     * @return district name
     */
    public String getDistrict() {
        return District;
    }

    /**
     * Get continent name
     * @return continent name
     */
    public String getContinent() {
        return Continent;
    }

    /**
     * Get region name
     * @return region name
     */
    public String getRegion() {
        return Region;
    }
}