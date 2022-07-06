package md.dunai;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import md.dunai.db.DBConn;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App
{
    private static List<Employee> employeeList = new ArrayList<>();
    private static List<Match> matchList = new ArrayList<>();
    private static DBManger db;



    public static void main( String[] args ) throws SQLException {

        DBConn dbConn = new DBConn("jdbc:sqlite:supervisor.db");
        db = new DBManger(dbConn);

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

        for(Employee em : employeeList) {
            String query = "INSERT INTO Employee (firstName, secondName, age, email, address) " +
                    "VALUES ('" + em.getFirstName() + "', '" + em.getLastName() + "', " + em.getAge() + ", '" + em.getEmail() + "', '" + em.getAddress() + "')";
            db.insert(query);
        }

        for(Match match : matchList) {
                String[] names = match.getAnalyst().split(" ");
                String query = "SELECT * FROM Employee WHERE firstName = '"+ names[0] +"' AND secondName = '"+ names[1] +"'";
                ResultSet resultSet = db.select(query);
                if (resultSet != null && resultSet.next()) {
                    int idEmployee = resultSet.getInt("id_employee");
                    query = "INSERT INTO Match (homeTeam, awayTeam, homeGoals, awayGoals, date, analyst, status) " +
                            "VALUES ('"+ match.getHomeTeam() + "', '"+ match.getAwayTeam() + "', '"+ match.getHomeGoals() + "', '"+ match.getAwayGoals() + "'," +
                            " '"+ match.getDate() + "', " + idEmployee + " , '" + match.getStatus() + "')";
                    db.insert(query);

                } else {
                    System.out.println(names[0] + " employee is missing in the database");
                }
            }

        //get all employees from database
        Map<Integer, String> employees = new HashMap<>();
        String query = "SELECT id_employee, firstName, secondName FROM Employee";
        ResultSet resultSet = db.select(query);
        while(resultSet != null && resultSet.next()) {
            String name = resultSet.getString(2) + " " + resultSet.getString(3);
            employees.put(resultSet.getInt(1), name);
        }

        //save data into a csv file
        Map<Integer, String[]> dataToExport = new HashMap<>();
        for(int id : employees.keySet()) {
                String name = employees.get(id);
                int open = getNumMatches(id, Status.OPEN);
                int closed = getNumMatches(id, Status.CLOSED);
                int inProgress = getNumMatches(id, Status.IN_PROGRESS);
                int total = open + closed + inProgress;
                String[] data = {name, Integer.toString(total), Integer.toString(open), Integer.toString(closed), Integer.toString(inProgress)};
                dataToExport.put(id, data);
            }
        CsvWriter fileWriter = new CsvWriter();
        String path = "results.csv";
        String[] header = { "Name", "Total", "Open", "Closed", "In Progress" };
        fileWriter.writeToFile(path, header, dataToExport);

        //close database connections
        db.close();
    }

    public static int getNumMatches(int employeeID, Status status) throws SQLException {
        String query = "SELECT count(id_match) FROM Match WHERE analyst = " + employeeID + " AND Status = '" + status.toString() +"'";
        ResultSet resultSet = db.select(query);
        return resultSet.getInt(1);
    }
}
