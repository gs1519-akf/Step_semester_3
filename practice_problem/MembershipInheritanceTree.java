class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + booksBorrowed;
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department + " | Books Borrowed: " + booksBorrowed;
    }
}

public class MembershipInheritanceTree {

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "General Member";
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int sum = 0;
        for (LibraryMember m : members) {
            if (m != null) {
                sum += m.getBooksBorrowed();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LibraryMember lm = new LibraryMember("STU1", 3);
        StudentMember sm = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember hm = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember fm = new FacultyMember("STU4", 5, "Physics");

        System.out.println(lm.displayInfo());
        System.out.println(sm.displayInfo());
        System.out.println(hm.displayInfo());
        System.out.println(fm.displayInfo());

        System.out.println(classifyGeneration(hm));
        System.out.println(classifyGeneration(fm));

        sm.borrowBook(); sm.borrowBook();
        hm.borrowBook();
        fm.borrowBook(); fm.borrowBook(); fm.borrowBook();

        LibraryMember[] list = {sm, hm, fm};
        System.out.println("Total books borrowed: " + getTotalBooksBorrowed(list));
    }
}
