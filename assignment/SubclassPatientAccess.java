public class SubclassPatientAccess {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (accessorContext) {
            case "SAME_CLASS":
                return "ALLOWED";
            case "SAME_PACKAGE":
                return "private".equals(fieldModifier) ? "DENIED" : "ALLOWED";
            case "DIFFERENT_PACKAGE":
                return "public".equals(fieldModifier) ? "ALLOWED" : "DENIED";
            case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                // Protected fields are accessible in subclasses through references of the subclass's own type
                return ("public".equals(fieldModifier) || "protected".equals(fieldModifier)) ? "ALLOWED" : "DENIED";
            case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                // Accessing protected member across packages via parent-type variable is denied by Java compiler
                return "public".equals(fieldModifier) ? "ALLOWED" : "DENIED";
            default:
                return "DENIED";
        }
    }

    public static void main(String[] args) {
        System.out.println("protected in SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE -> " +
                classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println("protected in SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE -> " +
                classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
