class StaffMemberRecord {
    protected String id;

    public StaffMemberRecord(String id) {
        this.id = id;
    }

    public String displayProfile() {
        return "Staff ID: " + id;
    }
}

class AcademicStaffRecord extends StaffMemberRecord {
    String department;

    public AcademicStaffRecord(String id, String department) {
        super(id);
        this.department = department;
    }

    @Override
    public String displayProfile() {
        return "Academic Staff | ID: " + id + " | Dept: " + department;
    }
}

public class StaffRosterReport {

    public static String generateRosterReport(StaffMemberRecord[] staffList) {
        StringBuilder sb = new StringBuilder();
        for (StaffMemberRecord s : staffList) {
            sb.append(s.displayProfile());
            if (s instanceof AcademicStaffRecord) {
                AcademicStaffRecord as = (AcademicStaffRecord) s;
                sb.append(" [Dept via downcast: ").append(as.department).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StaffMemberRecord[] staff = {
            new StaffMemberRecord("STF-1"),
            new AcademicStaffRecord("STF-2", "Data Science")
        };
        System.out.println(generateRosterReport(staff));
    }
}
