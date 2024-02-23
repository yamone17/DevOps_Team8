package DevOps_Team8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class Language {
    private Connection connection;
    private NumberFormat numberFormat = NumberFormat.getInstance();

    public Language(Connection connection) {
        this.connection = connection;
    }

    public void language() {
        System.out.println();
        String languageQuery = "SELECT cl.Language, " +
                "SUM(cl.Percentage * co.Population / 100) AS TotalSpeakers, " +
                "(SUM(cl.Percentage * co.Population) / (SELECT SUM(Population) FROM country)) AS PercentageOfWorldPopulation\n" +
                "FROM countrylanguage cl\n" +
                "JOIN country co ON cl.CountryCode = co.Code\n" +
                "WHERE cl.Language IN ('English', 'Hindi', 'Spanish', 'Arabic', 'Chinese')\n" +
                "GROUP BY cl.Language\n" +
                "ORDER BY TotalSpeakers DESC;";

        try (PreparedStatement statement = connection.prepareStatement(languageQuery);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Language\t\tNumber of Speakers\t\tPercentage of World Population");

            while (resultSet.next()) {
                String language = resultSet.getString("Language");
                double totalSpeakers = resultSet.getDouble("TotalSpeakers");
                double percentageOfWorldPopulation = resultSet.getDouble("PercentageOfWorldPopulation");

                System.out.printf("%-20s %-20s %-20s%n", language, numberFormat.format(totalSpeakers), String.format("%.2f", percentageOfWorldPopulation) + "%");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
