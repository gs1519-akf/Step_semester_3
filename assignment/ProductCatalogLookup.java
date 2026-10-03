import java.util.*;

class ProductRecord {
    String sku;
    String name;

    public ProductRecord(String sku, String name) {
        this.sku = sku;
        this.name = name;
    }
}

public class ProductCatalogLookup {

    /**
     * Efficiently searches a sorted catalog of products by SKU using Binary Search.
     * Time Complexity: O(log n)
     * Auxiliary Space Complexity: O(1)
     */
    public static String findProduct(List<ProductRecord> catalog, String targetSku) {
        if (catalog == null || targetSku == null || catalog.isEmpty()) {
            return "Not Found";
        }

        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            ProductRecord current = catalog.get(mid);
            int cmp = current.sku.compareTo(targetSku);

            if (cmp == 0) {
                return current.name;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        List<ProductRecord> catalog = Arrays.asList(
            new ProductRecord("SKU-1001", "Mechanical Keyboard"),
            new ProductRecord("SKU-1002", "Ergonomic Mouse"),
            new ProductRecord("SKU-1003", "4K Ultra-HD Monitor"),
            new ProductRecord("SKU-1004", "USB-C Multi-Port Hub"),
            new ProductRecord("SKU-1005", "Noise Cancelling Headphones")
        );

        System.out.println("Looking up SKU-1003: " + findProduct(catalog, "SKU-1003"));
        System.out.println("Looking up SKU-9999: " + findProduct(catalog, "SKU-9999"));
    }
}
