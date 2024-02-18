package DevOps_Team8;

public class Capital {
    private String Name;
    private String Country;
    private int Population;
    private String Continent;
    private String Region;

    public Capital(String name, String country, int population, String continent, String region) {
        Name = name;
        Country = country;
        Population = population;
        Continent = continent;
        Region = region;
    }

    public String getName() {
        return Name;
    }

    public String getCountry() {
        return Country;
    }

    public int getPopulation() {
        return Population;
    }

    public String getContinent() {
        return Continent;
    }

    public String getRegion() {
        return Region;
    }
}