package live_coding_session_3.assignment_problems;

public class EmployeeStaticDemo {

    String empName;
    double salary;

    static String companyName =
            "Bright Horizon Technologies";

    static int employeeCount = 0;

    EmployeeStaticDemo(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: "
                + employeeCount);
    }

    public static void main(String[] args) {

        EmployeeStaticDemo employee1 =
                new EmployeeStaticDemo("Ravi", 50000);

        EmployeeStaticDemo employee2 =
                new EmployeeStaticDemo("Priya", 60000);

        EmployeeStaticDemo employee3 =
                new EmployeeStaticDemo("Arjun", 45000);

        System.out.println("3 Employee objects created");

        EmployeeStaticDemo.printCompanyInfo();
    }
}