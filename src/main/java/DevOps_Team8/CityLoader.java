package DevOps_Team8;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityLoader {
    public List<City> loadCityData(Connection mySQL) {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT city.Name AS CityName, city.Population AS CityPopulation, city.District, " +
                "country.Name AS CountryName, country.Region, country.Continent " +
                "FROM city " +
                "LEFT JOIN country ON city.CountryCode = country.Code";

        try (Statement statement = mySQL.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String name = resultSet.getString("CityName");
                int population = resultSet.getInt("CityPopulation");
                String district = resultSet.getString("District");
                String country = resultSet.getString("CountryName");
                String region = resultSet.getString("Region");
                String continent = resultSet.getString("Continent");

                City city = new City (name, country, population, district, continent, region);
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cities;
    }
}