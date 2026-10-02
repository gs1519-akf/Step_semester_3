import java.util.Arrays;

class StaffAccount {
    protected String staffId;
    private int[] allowanceHistory;
    private int allowanceCount;

    public StaffAccount(String staffId) {
        this.staffId = staffId;
        this.allowanceHistory = new int[10];
        this.allowanceCount = 0;
    }

    protected void creditAllowance(int amount) {
        if (allowanceCount < allowanceHistory.length) {
            allowanceHistory[allowanceCount++] = amount;
        }
    }

    public int[] getAllowanceHistory() {
        int[] copy = new int[allowanceCount];
        System.arraycopy(allowanceHistory, 0, copy, 0, allowanceCount);
        return copy;
    }

    public int getTotalAllowance() {
        int sum = 0;
        for (int i = 0; i < allowanceCount; i++) {
            sum += allowanceHistory[i];
        }
        return sum;
    }
}

class FacultyAccount extends StaffAccount {
    public FacultyAccount(String staffId) {
        super(staffId);
    }

    @Override
    protected void creditAllowance(int amount) {
        // Faculty receives doubled research allowance
        super.creditAllowance(amount * 2);
    }
}

public class StaffBenefitLedger {
    public static void main(String[] args) {
        FacultyAccount fa = new FacultyAccount("FAC-501");
        fa.creditAllowance(100);
        System.out.println("Total Allowance (after override): " + fa.getTotalAllowance());

        int[] history = fa.getAllowanceHistory();
        history[0] = 999;
        System.out.println("Defensive copy verified: " + Arrays.toString(fa.getAllowanceHistory()));
    }
}
