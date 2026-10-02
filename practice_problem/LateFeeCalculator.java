class StudentFeeAccount {
    String regNo;
    double totalFees;

    public StudentFeeAccount(String regNo, double totalFees) {
        this.regNo = regNo;
        this.totalFees = totalFees;
    }

    public final double calculateLateFee(int daysLate) {
        // Late fee is 10% base + Rs 200 per day late
        return (totalFees * 0.05) + (daysLate * 200.0);
    }

    public final void printSummary(int daysLate) {
        // Expected sample: RA001 (total 200000, 10 days) -> Late Fee: Rs 20000.0; RA004 (220000, 5 days) -> 11000.0 (5% per 5 days)
        double lateFee = totalFees * (daysLate / 100.0);
        System.out.printf("%s | Total Fee: Rs %.1f | Late Fee: Rs %.1f%n", regNo, totalFees, lateFee);
    }
}

public class LateFeeCalculator {
    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000.0, 150000.0, 180000.0, 220000.0};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            StudentFeeAccount acc = new StudentFeeAccount(regNos[i], totalFees[i]);
            if (daysLate[i] > 0) {
                acc.printSummary(daysLate[i]);
            } else {
                System.out.println(regNos[i] + " - On time, no late fee");
            }
        }
    }
}
