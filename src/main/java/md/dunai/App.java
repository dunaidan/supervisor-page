package md.dunai;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static List<Employee> employeeList = new ArrayList<>();
    public static List<Match> matchList = new ArrayList<>();

    public static void main( String[] args )
    {
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd.MM.yyyy")
                    .create();

            Reader reader = Files.newBufferedReader(Paths.get("employee.json"));
            employeeList = gson.fromJson(reader, new TypeToken<List<Employee>>() {}.getType());
            System.out.println("Employees: ");
            for(Employee em : employeeList) {
                System.out.println(em.toString());
            }


            reader = Files.newBufferedReader(Paths.get("matches.json"));
            matchList = gson.fromJson(reader, new TypeToken<List<Match>>() {}.getType());
            System.out.println("\nMatches: ");
            for(Match match : matchList) {
                System.out.println(match.toString());
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("The file is empty");
        }
    }
}
