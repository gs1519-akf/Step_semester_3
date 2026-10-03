import java.util.*;

class BookEntry {
    String isbn;
    String title;

    public BookEntry(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
}

public class LibraryCatalogLookup {

    /**
     * Problem 1: Library Catalog Lookup
     * Performs Binary Search on an ISBN-sorted catalog of books.
     * 
     * Time Complexity: O(log n)
     * Additional Space Complexity: O(1)
     */
    public static String findBook(List<BookEntry> catalog, String targetIsbn) {
        if (catalog == null || targetIsbn == null || catalog.isEmpty()) {
            return "Not Found";
        }

        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            BookEntry entry = catalog.get(mid);
            int cmp = entry.isbn.compareTo(targetIsbn);

            if (cmp == 0) {
                return entry.title;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        List<BookEntry> catalog = Arrays.asList(
            new BookEntry("0001112223", "Introduction to Algebra"),
            new BookEntry("0002223334", "Beginning Python"),
            new BookEntry("0003334445", "Classic Mythology"),
            new BookEntry("0004445556", "Data and Society"),
            new BookEntry("0005556667", "European History")
        );

        String target1 = "0003334445";
        System.out.println("targetIsbn = \"" + target1 + "\"");
        System.out.println("Expected Output: \"Classic Mythology\"");
        System.out.println("Actual Output:   \"" + findBook(catalog, target1) + "\"");

        System.out.println();
        String target2 = "0009998887";
        System.out.println("targetIsbn = \"" + target2 + "\"");
        System.out.println("Expected Output: \"Not Found\"");
        System.out.println("Actual Output:   \"" + findBook(catalog, target2) + "\"");
    }
}
