class StaffMember {
    protected String staffId;
    protected double basePay;

    public StaffMember(String staffId, double basePay) {
        if (staffId == null || staffId.trim().isEmpty() || staffId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid staff ID: must be at least 4 characters.");
        }
        this.staffId = staffId.trim();
        this.basePay = basePay;
    }

    public String getStaffId() {
        return staffId;
    }

    public double getBasePay() {
        return basePay;
    }
}

class TeachingStaff extends StaffMember {
    private String department;

    public TeachingStaff(String staffId, double basePay, String department) {
        super(staffId, basePay);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class StaffBatchEnrollment {

    public static String enrollStaffBatch(String[] staffIds, double basePay) {
        int enrolled = 0;
        int rejected = 0;

        for (String id : staffIds) {
            try {
                new StaffMember(id, basePay);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new StaffMember("ST1", 50000);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        String[] ids = {"STU10", "LB1", "STU20", " ", "STU30"};
        System.out.println(enrollStaffBatch(ids, 50000));
    }
}
