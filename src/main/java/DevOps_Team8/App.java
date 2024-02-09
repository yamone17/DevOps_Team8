package DevOps_Team8;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

/**
 * Class for connect database, load country data, functions and disconnect from database
 */
public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        Connection con;
        if(args.length < 1){
            con = a.connect("localhost:33061", 0);
        }else{
            con = a.connect(args[0], Integer.parseInt(args[1]));
//            con = a.connect("db:3306", 20000);
        }
        System.out.println(con);
        //Load country data
        CountryLoader dataLoader = new CountryLoader();
        List<Country> countries = dataLoader.loadCountryData(con);

        // Create a Country Report
        CountryReport report = new CountryReport(countries);

        // Sort countries by population
        report.sortByPopulation();

        // Sort countries by population in each continent
        report.sortByPopulationContinent();

        // Sort countries by population in each region
        report.sortByPopulationRegion();

        // Display top 5 countries by population
        report.getTopNPopulatedCountriesInWorld(5);

        //Display top 5 countries in asia by population
        report.getTopNPopulatedCountriesInContinent("Asia", 5);

        //Display top 5 countries in southeast asia by population
        report.getTopNPopulatedCountriesInRegion("Southeast Asia", 5);

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public Connection connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
                System.out.println(sqle.getMessage());
                return null;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
                return null;
            }
        }
        return con;
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

}