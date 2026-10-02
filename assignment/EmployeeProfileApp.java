class Employee {
    String empId;
    String empName;
    double salary;
    boolean isIntern;

    // Constructor for permanent employees
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    // Constructor for interns chaining to 3-argument constructor
    public Employee(String empId, String empName) {
        this(empId, empName, 0.0);
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.printf("%s | %s | Rs %.1f | Intern: %b%n", empId, empName, salary, isIntern);
    }
}

public class EmployeeProfileApp {
    public static void main(String[] args) {
        Employee permanent = new Employee("E-101", "Divya", 65000);
        Employee intern = new Employee("E-102", "Arjun");

        permanent.printProfile();
        intern.printProfile();
    }
}
