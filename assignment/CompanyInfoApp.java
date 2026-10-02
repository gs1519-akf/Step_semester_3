class CompanyEmployee {
    String empName;
    double salary;
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public CompanyEmployee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class CompanyInfoApp {
    public static void main(String[] args) {
        new CompanyEmployee("Alice", 70000);
        new CompanyEmployee("Bob", 65000);
        new CompanyEmployee("Charlie", 80000);

        CompanyEmployee.printCompanyInfo();
    }
}
