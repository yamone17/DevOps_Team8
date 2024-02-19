package DevOps_Team8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class PopulationReport {

    private Connection connection;
    NumberFormat numberFormat = NumberFormat.getInstance();

    public PopulationReport(Connection connection) {
        this.connection = connection;
    }

    public void ContinentPopulationReport() {
        String continentQuery = "SELECT Continent AS Name, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS PopulationInCities, " +
                "(SUM(country.Population) - SUM(city.Population)) AS PopulationNotInCities " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY Continent";

        try (PreparedStatement statement = connection.prepareStatement(continentQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String continentName = resultSet.getString("Name");
                long totalPopulation = resultSet.getLong("TotalPopulation");
                long populationInCities = resultSet.getLong("PopulationInCities");
                long populationNotInCities = resultSet.getLong("PopulationNotInCities");

                double populationInCitiesPercentage = ((double) populationInCities / totalPopulation) * 100;
                double populationNotInCitiesPercentage = ((double) populationNotInCities / totalPopulation) * 100;

                System.out.println("Continent: " + continentName);
                System.out.println("Total Population: " + numberFormat.format(totalPopulation));
                System.out.println("Population in Cities: " + numberFormat.format(populationInCities) + " (" + populationInCitiesPercentage + "%)");
                System.out.println("Population not in Cities: " + numberFormat.format(populationNotInCities) + " (" + populationNotInCitiesPercentage + "%)");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RegionPopulationReport() {
        String regionQuery = "SELECT Region AS Name, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS PopulationInCities, " +
                "(SUM(country.Population) - SUM(city.Population)) AS PopulationNotInCities " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY Region";

        try (PreparedStatement statement = connection.prepareStatement(regionQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String regionName = resultSet.getString("Name");
                long totalPopulation = resultSet.getLong("TotalPopulation");
                long populationInCities = resultSet.getLong("PopulationInCities");
                long populationNotInCities = resultSet.getLong("PopulationNotInCities");

                double populationInCitiesPercentage = ((double) populationInCities / totalPopulation) * 100;
                double populationNotInCitiesPercentage = ((double) populationNotInCities / totalPopulation) * 100;

                System.out.println("Region: " + regionName);
                System.out.println("Total Population: " + numberFormat.format(totalPopulation));
                System.out.println("Population in Cities: " + numberFormat.format(populationInCities) + " (" + populationInCitiesPercentage + "%)");
                System.out.println("Population not in Cities: " + numberFormat.format(populationNotInCities) + " (" + populationNotInCitiesPercentage + "%)");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CountryPopulationReport() {
        String countryQuery = "SELECT country.Name AS Name, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS PopulationInCities, " +
                "(SUM(country.Population) - SUM(city.Population)) AS PopulationNotInCities " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY Name";

        try (PreparedStatement statement = connection.prepareStatement(countryQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String countryName = resultSet.getString("Name");
                long totalPopulation = resultSet.getLong("TotalPopulation");
                long populationInCities = resultSet.getLong("PopulationInCities");
                long populationNotInCities = resultSet.getLong("PopulationNotInCities");

                double populationInCitiesPercentage = ((double) populationInCities / totalPopulation) * 100;
                double populationNotInCitiesPercentage = ((double) populationNotInCities / totalPopulation) * 100;

                System.out.println("Country Name: " + countryName);
                System.out.println("Total Population: " + numberFormat.format(totalPopulation));
                System.out.println("Population in Cities: " + numberFormat.format(populationInCities) + " (" + populationInCitiesPercentage + "%)");
                System.out.println("Population not in Cities: " + numberFormat.format(populationNotInCities) + " (" + populationNotInCitiesPercentage + "%)");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

