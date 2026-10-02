public class ReverseCustomerName {

    /**
     * Reverses the given customer name while leaving original untouched.
     */
    public static String reverseCustomerName(String customerName) {
        if (customerName == null) return null;
        char[] chars = customerName.toCharArray();
        char[] reversed = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            reversed[i] = chars[chars.length - 1 - i];
        }
        return new String(reversed);
    }

    public static void main(String[] args) {
        String customer = "Sunil";
        String reversed = reverseCustomerName(customer);
        System.out.println("Original Name: " + customer);
        System.out.println("Reversed Name: " + reversed);
    }
}
