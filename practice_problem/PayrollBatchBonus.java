class PayrollEmployee {
    String empId;
    double salary;

    public PayrollEmployee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary += salary;
    }
}

public class PayrollBatchBonus {
    public static void main(String[] args) {
        PayrollEmployee[] employees = {
            new PayrollEmployee("E-101", 40000),
            new PayrollEmployee("E-102", 55000),
            new PayrollEmployee("E-103", 62000),
            new PayrollEmployee("E-104", 48000)
        };

        for (PayrollEmployee emp : employees) {
            emp.raiseSalary(5000);
            System.out.printf("%s | Final Salary: Rs %.1f%n", emp.empId, emp.salary);
        }
    }
}
