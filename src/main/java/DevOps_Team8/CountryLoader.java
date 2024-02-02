package DevOps_Team8;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryLoader {
    private static final String JDBC_URL = "jdbc:mysql://db:3306/world?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "example";

    public List<Country> loadCountryData() {
        List<Country> countries = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT Code, Name, Population, Continent, Region, Capital FROM country";
            try (Statement statement = connection.createStatement();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }
}