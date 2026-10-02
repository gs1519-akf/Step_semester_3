class HospitalWard {
    private int bedsTotal;
    private int bedsAvailable;

    public HospitalWard(int bedsTotal) {
        if (bedsTotal <= 0) {
            System.out.println("construction rejected");
            this.bedsTotal = 0;
            this.bedsAvailable = 0;
        } else {
            this.bedsTotal = bedsTotal;
            this.bedsAvailable = bedsTotal;
        }
    }

    public void admitPatient() {
        if (bedsAvailable > 0) {
            bedsAvailable--;
        }
    }

    public void dischargePatient() {
        if (bedsAvailable < bedsTotal) {
            bedsAvailable++;
        }
    }

    public int getBedsAvailable() {
        return bedsAvailable;
    }
}

public class BedAllocationGuard {
    public static void main(String[] args) {
        System.out.print("new HospitalWard(0) -> ");
        new HospitalWard(0);

        System.out.println("\n--- Testing Over-admission ---");
        HospitalWard ward = new HospitalWard(2);
        ward.admitPatient();
        ward.admitPatient();
        ward.admitPatient(); // silently rejected
        System.out.println("Beds available: " + ward.getBedsAvailable());

        System.out.println("\n--- Testing Over-discharge ---");
        ward.dischargePatient();
        ward.dischargePatient();
        ward.dischargePatient(); // silently rejected
        System.out.println("Beds available: " + ward.getBedsAvailable());
    }
}
