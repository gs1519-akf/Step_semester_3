public class AtmPinLengthValidator {

    /**
     * Checks that an entered PIN string is exactly 4 characters long.
     * 
     * @param pin The PIN string to validate.
     */
    public static void checkPinLength(String pin) {
        if (pin != null && pin.length() == 4) {
            System.out.println("PIN length OK.");
        } else {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Input: \"482\"");
        checkPinLength("482");

        System.out.println("\nInput: \"4820\"");
        checkPinLength("4820");
    }
}
