package md.dunai.summary;

public class EmployeeSummary implements SummaryResult{
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%s,%s\n", this.firstName, this.lastName);
    }

    @Override
    public String header() {
        return null;
    }

    public String toJson() {
        return "";
    }
}
