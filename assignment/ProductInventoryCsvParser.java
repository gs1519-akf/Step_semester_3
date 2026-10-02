public class ProductInventoryCsvParser {

    /**
     * Parses a CSV line of product details and prints a formatted record.
     * 
     * @param csvLine CSV string in form "ProductName,SKU,Quantity".
     */
    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }

        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = fields[0].trim();
        String sku = fields[1].trim();
        String qty = fields[2].trim();

        System.out.printf("Product: %s | SKU: %s | Qty: %s%n", productName, sku, qty);
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1 ---");
        parseInventoryRecord("Wireless Mouse,WM-2201,150");

        System.out.println("\n--- Test 2 ---");
        parseInventoryRecord("Wireless Mouse,150");
    }
}
