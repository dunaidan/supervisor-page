package md.dunai;

import com.google.gson.annotations.SerializedName;

public class Employee {
    @SerializedName(value = "first_name")
    private final String firstName;
    @SerializedName(value = "last_name")
    private final String lastName;
    private int age;
    private String email;
    private String address;

    public Employee(String firstName, String lastName, int age, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
