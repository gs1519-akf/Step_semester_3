class PatientRecord {
    private String ssn;             // private: accessible only within SAME_CLASS
    String wardId;                  // default: accessible within SAME_PACKAGE
    protected String diagnosisCode; // protected: package + subclasses
    public String patientName;      // public: everywhere

    public PatientRecord(String ssn, String wardId, String diagnosisCode, String patientName) {
        this.ssn = ssn;
        this.wardId = wardId;
        this.diagnosisCode = diagnosisCode;
        this.patientName = patientName;
    }
}

public class PatientRecordVisibilityChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("SAME_CLASS".equals(accessorContext)) {
            return "ALLOWED";
        } else if ("SAME_PACKAGE".equals(accessorContext)) {
            return "private".equals(fieldModifier) ? "DENIED" : "ALLOWED";
        } else if ("DIFFERENT_PACKAGE".equals(accessorContext)) {
            return "public".equals(fieldModifier) ? "ALLOWED" : "DENIED";
        }
        return "DENIED";
    }

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;
        for (String[] attempt : attempts) {
            String result = classifyAccess(attempt[0], attempt[1]);
            if ("ALLOWED".equals(result)) {
                allowed++;
            } else {
                denied++;
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println("private in SAME_CLASS -> " + classifyAccess("private", "SAME_CLASS"));
        System.out.println("protected in DIFFERENT_PACKAGE -> " + classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] batch = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println("Batch summary -> " + summarizeBatch(batch));
    }
}
