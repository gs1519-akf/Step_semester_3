public class WeeklyCirculationReport {

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (LibraryMember m : members) {
            sb.append(m.displayInfo());
            if (m instanceof StudentMember) {
                StudentMember sm = (StudentMember) m;
                sb.append(" [Course via downcast: ").append(sm.course).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] batch = {
            new LibraryMember("LB50", 3),
            new StudentMember("STU6", 3, "ECE")
        };

        System.out.println(batchPrint(batch));

        LibraryMember plain = new LibraryMember("LB60", 3);
        try {
            StudentMember bad = (StudentMember) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
