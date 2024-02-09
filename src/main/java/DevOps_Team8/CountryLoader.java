package DevOps_Team8;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryLoader {
    private static final String JDBC_URL = "jdbc:mysql://db:33061/world?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "example";

    public List<Country> loadCountryData(Connection con) {
        List<Country> countries = new ArrayList<>();

        String sql = "SELECT country.Code, country.Name, country.Population, country.Continent, " +
                    "country.Region, city.Name AS Capital " +
                    "FROM country country LEFT JOIN city city ON country.Capital = city.ID";
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String code = resultSet.getString("Code");
                    String name = resultSet.getString("Name");
                    int population = resultSet.getInt("Population");
                    String continent = resultSet.getString("Continent");
                    String region = resultSet.getString("Region");
                    String capital = resultSet.getString("Capital");

                    Country country = new Country(code, name, population, continent, region, capital);
                    countries.add(country);
                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return countries;
    }
}