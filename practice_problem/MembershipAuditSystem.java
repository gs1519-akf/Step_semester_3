class AuditedLibraryMember {
    private static int counter = 100;
    private final String memberNumber;
    private int borrowLimit;
    private int booksBorrowed;

    public AuditedLibraryMember(int borrowLimit) {
        counter++;
        this.memberNumber = "LIB-" + counter;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void borrowBook() {
        booksBorrowed++;
    }

    public void borrowBook(String genre) {
        // Records genre before delegating to no-argument version
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static int getMembersEnrolled() {
        return counter - 100;
    }
}

class AuditedFacultyMember extends AuditedLibraryMember {
    private String department;

    public AuditedFacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class MembershipAuditSystem {

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static String processNightlyAudit(AuditedLibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (AuditedLibraryMember m : members) {
            if (m == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (m instanceof AuditedFacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               faculty + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        AuditedLibraryMember m1 = new AuditedLibraryMember(3);
        System.out.println(m1.getMemberNumber());
        System.out.println(AuditedLibraryMember.getMembersEnrolled());

        System.out.println("isValidRenewalCode(\"R12A\") -> " + isValidRenewalCode("R12A"));
        System.out.println("isValidRenewalCode(\"R1A\") -> " + isValidRenewalCode("R1A"));
        System.out.println("isValidRenewalCode(\"X12A\") -> " + isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println("Books borrowed: " + m1.getBooksBorrowed());

        AuditedLibraryMember[] batch = {
            new AuditedFacultyMember(5, "Physics"),
            null,
            new AuditedLibraryMember(3)
        };
        System.out.println(processNightlyAudit(batch));
    }
}
