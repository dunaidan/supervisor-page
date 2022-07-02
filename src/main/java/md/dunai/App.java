package md.dunai;

import com.google.gson.Gson;
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
    public static void main( String[] args )
    {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("employee.json"));
            employeeList = gson.fromJson(reader, new TypeToken<List<Employee>>() {}.getType());
            for(Employee em : employeeList) {
                System.out.println(em.getFirstName() + " " + em.getLastName());
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("The file is empty");
        }
    }
}
