import java.util.Arrays;

class FineLibraryMember {
    protected String memberId;
    private int[] fineHistory;
    private int fineCount;

    public FineLibraryMember(String memberId) {
        this.memberId = memberId;
        this.fineHistory = new int[10];
        this.fineCount = 0;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    public int[] getFineHistory() {
        int[] copy = new int[fineCount];
        System.arraycopy(fineHistory, 0, copy, 0, fineCount);
        return copy;
    }

    public int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }
}

class DiscountStudentMember extends FineLibraryMember {
    public DiscountStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId);
    }

    @Override
    protected void chargeFine(int amount) {
        // Student receives 50% discount on fines
        super.chargeFine(amount / 2);
    }
}

public class StudentDiscountLedger {
    public static void main(String[] args) {
        DiscountStudentMember s = new DiscountStudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(s.getFineHistory()));
    }
}
