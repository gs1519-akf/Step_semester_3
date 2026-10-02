class StaffAuditSystemRecord {
    private static int counter = 100;
    private final String staffNumber;
    private int dutiesAssigned;

    public StaffAuditSystemRecord() {
        counter++;
        this.staffNumber = "STAFF-" + counter;
        this.dutiesAssigned = 0;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void assignDuty() {
        this.dutiesAssigned++;
    }

    public void assignDuty(String dutyName) {
        // delegates internally
        assignDuty();
    }

    public int getDutiesAssigned() {
        return dutiesAssigned;
    }

    public static int getStaffCount() {
        return counter - 100;
    }
}

class DepartmentHeadRecord extends StaffAuditSystemRecord {
    private String division;

    public DepartmentHeadRecord(String division) {
        super();
        this.division = division;
    }

    public String getDivision() {
        return division;
    }
}

public class StaffAuditSystem {

    public static boolean isValidStaffCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'S') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static String processStaffAudit(StaffAuditSystemRecord[] staffList) {
        int processed = 0;
        int nullSkipped = 0;
        int heads = 0;
        int regular = 0;

        for (StaffAuditSystemRecord s : staffList) {
            if (s == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (s instanceof DepartmentHeadRecord) {
                heads++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               heads + " head | " + regular + " regular";
    }

    public static void main(String[] args) {
        StaffAuditSystemRecord s1 = new StaffAuditSystemRecord();
        System.out.println("Staff Number: " + s1.getStaffNumber());

        System.out.println("S12A valid: " + isValidStaffCode("S12A"));
        System.out.println("S1A valid: " + isValidStaffCode("S1A"));
        System.out.println("X12A valid: " + isValidStaffCode("X12A"));

        s1.assignDuty();
        s1.assignDuty("Exam Supervision");
        System.out.println("Duties: " + s1.getDutiesAssigned());

        StaffAuditSystemRecord[] batch = {
            new DepartmentHeadRecord("Engineering"),
            null,
            new StaffAuditSystemRecord()
        };
        System.out.println(processStaffAudit(batch));
    }
}
