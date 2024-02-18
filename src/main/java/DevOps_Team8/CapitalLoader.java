package DevOps_Team8;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapitalLoader {
    public static List<Capital> loadCapitalData(Connection mySQL) {
        List<Capital> capitals = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalCity, city.Population AS CapitalPopulation, " +
                "country.Name AS CountryName, country.Continent, country.Region " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital";


        try (Statement statement = mySQL.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String name = resultSet.getString("CapitalCity");
                int population = resultSet.getInt("CapitalPopulation");
                String country = resultSet.getString("CountryName");
                String region = resultSet.getString("Region");
                String continent = resultSet.getString("Continent");

                Capital capital = new Capital (name, country, population, continent, region);
                capitals.add(capital);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return capitals;
    }
}