package DevOps_Team8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

/**
 * Displaying additional information: population of the world, continent, etc
 */
public class Additional {
    private Connection connection;
    NumberFormat numberFormat = NumberFormat.getInstance();

    public Additional(Connection connection) {
        this.connection = connection;
    }

    public void worldPopulation() {
        System.out.println();
        String worldPopulationQuery = "SELECT SUM(Population) AS world_population FROM country";

        try (PreparedStatement statement = connection.prepareStatement(worldPopulationQuery);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                long worldPopulation = resultSet.getLong("world_population");
                System.out.println("World Population: " + numberFormat.format(worldPopulation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void continentPopulation() {
        System.out.println();
        String asiaPopulationQuery = "SELECT SUM(Population) AS asiaPopulation FROM country " +
                "WHERE Continent = 'Asia'; ";

        try (PreparedStatement statement = connection.prepareStatement(asiaPopulationQuery);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                double asiaPopulation = resultSet.getDouble("asiaPopulation");
                System.out.println("Asia Population: " + numberFormat.format(asiaPopulation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void regionPopulation() {
        System.out.println();
        String southeastAsiaPopulationQuery = "SELECT SUM(Population) AS southeast_asiaPopulation FROM country " +
                "WHERE Region = 'Southeast Asia'; ";

        try (PreparedStatement statement = connection.prepareStatement(southeastAsiaPopulationQuery);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                long southeastAsiaPopulation = resultSet.getLong("southeast_asiaPopulation");
                System.out.println("SouthEast Asia Population: " + numberFormat.format(southeastAsiaPopulation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void districtPopulation() {
        System.out.println();
        String mandalayPopulationQuery = "SELECT SUM(Population) AS mandalayPopulation FROM city " +
                "WHERE District = 'Mandalay'; ";

        try (PreparedStatement statement = connection.prepareStatement(mandalayPopulationQuery);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                long mandalayPopulation = resultSet.getLong("mandalayPopulation");
                System.out.println("Mandalay Population: " + numberFormat.format(mandalayPopulation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void countryPopulation() {
        System.out.println();
        String myanmarPopulationQuery = "SELECT population " +
                "FROM country " +
                "WHERE Name = 'Myanmar';";

        try (PreparedStatement statement = connection.prepareStatement(myanmarPopulationQuery);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                long myanmarPopulation = resultSet.getLong("population");
                System.out.println("Myanmar Population: " + numberFormat.format(myanmarPopulation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cityPopulation() {
        System.out.println();
        String mandalayPopulationQuery = "SELECT population " +
                "FROM city " +
                "WHERE Name = 'Mandalay';";

        try (PreparedStatement statement = connection.prepareStatement(mandalayPopulationQuery);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                long mandalayPopulation = resultSet.getLong("population");
                System.out.println("Mandalay Population: " + numberFormat.format(mandalayPopulation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
