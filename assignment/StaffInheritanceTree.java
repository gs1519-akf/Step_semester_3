class StaffBase {
    protected String id;
    protected double pay;

    public StaffBase(String id, double pay) {
        this.id = id;
        this.pay = pay;
    }

    public String displayInfo() {
        return "General Staff | Base Pay: " + pay;
    }

    public double getPay() {
        return pay;
    }
}

class LecturerStaff extends StaffBase {
    protected String subject;

    public LecturerStaff(String id, double pay, String subject) {
        super(id, pay);
        this.subject = subject;
    }

    @Override
    public String displayInfo() {
        return "Lecturer | Subject: " + subject + " | Pay: " + pay;
    }
}

class SeniorProfessorStaff extends LecturerStaff {
    private int researchGrant;

    public SeniorProfessorStaff(String id, double pay, String subject, int researchGrant) {
        super(id, pay, subject);
        this.researchGrant = researchGrant;
    }

    @Override
    public String displayInfo() {
        return "Senior Professor | Subject: " + subject + " | Grant: " + researchGrant + " | Pay: " + pay;
    }
}

class AdminOfficerStaff extends StaffBase {
    private String officeWing;

    public AdminOfficerStaff(String id, double pay, String officeWing) {
        super(id, pay);
        this.officeWing = officeWing;
    }

    @Override
    public String displayInfo() {
        return "Admin Officer | Wing: " + officeWing + " | Pay: " + pay;
    }
}

public class StaffInheritanceTree {

    public static String classifyStaffHierarchy(StaffBase staff) {
        if (staff instanceof SeniorProfessorStaff) {
            return "Multilevel descendant (3 generations deep)";
        } else if (staff instanceof AdminOfficerStaff) {
            return "Hierarchical sibling (independent branch)";
        }
        return "General Hierarchy Node";
    }

    public static double calculateTotalPayroll(StaffBase[] staffList) {
        double total = 0;
        for (StaffBase s : staffList) {
            if (s != null) {
                total += s.getPay(); // Polymorphic method call
            }
        }
        return total;
    }

    public static void main(String[] args) {
        SeniorProfessorStaff prof = new SeniorProfessorStaff("P-101", 120000, "AI", 50000);
        AdminOfficerStaff admin = new AdminOfficerStaff("A-201", 60000, "North Wing");

        System.out.println(classifyStaffHierarchy(prof));
        System.out.println(classifyStaffHierarchy(admin));

        StaffBase[] team = {prof, admin};
        System.out.println("Total Payroll: " + calculateTotalPayroll(team));
    }
}
