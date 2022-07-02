package md.dunai;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static List<Employee> employeeList = new ArrayList<>();
    public static List<Match> matchList = new ArrayList<>();

    public static void main( String[] args ) {
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd.MM.yyyy")
                    .create();

            Reader reader = Files.newBufferedReader(Paths.get("employee.json"));
            employeeList = gson.fromJson(reader, new TypeToken<List<Employee>>() {}.getType());

            reader = Files.newBufferedReader(Paths.get("matches.json"));
            matchList = gson.fromJson(reader, new TypeToken<List<Match>>() {}.getType());

            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("The file is empty");
        }

        //Connection to the database and fill data
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:supervisor.db")) {
            Statement statement = conn.createStatement();
            for(Employee em : employeeList) {
                statement.execute("INSERT INTO Employee (firstName, secondName, age, email, address) " +
                        "VALUES ('"+ em.getFirstName() + "', '"+ em.getLastName() + "', "+ em.getAge() + ", '"+ em.getEmail() + "', '"+ em.getAddress() + "')");
            }

            for(Match match : matchList) {
                String[] names = match.getAnalyst().split(" ");
                String query = "SELECT * FROM Employee WHERE firstName = '"+ names[0] +"' AND secondName = '"+ names[1] +"'";
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    int idEmployee = resultSet.getInt("id_employee");
                    statement.execute("INSERT INTO Match (homeTeam, awayTeam, homeGoals, awayGoals, date, analyst, status) " +
                        "VALUES ('"+ match.getHomeTeam() + "', '"+ match.getAwayTeam() + "', '"+ match.getHomeGoals() + "', '"+ match.getAwayGoals() + "'," +
                            " '"+ match.getDate() + "', " + idEmployee + " , '" + match.getStatus() + "')");

                } else {
                    System.out.println(names[0] + " Not such entry in the database");
                }
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
