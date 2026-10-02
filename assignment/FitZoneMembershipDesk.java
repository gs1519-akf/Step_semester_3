abstract class MembershipPlan {
    private String name;
    private int durationMonths;

    public MembershipPlan(String name, int durationMonths) {
        this.name = name;
        this.durationMonths = durationMonths;
    }

    public String getName() { return name; }
    public int getDurationMonths() { return durationMonths; }
    public abstract double calculateFee(double baseRatePerMonth);
}

class MonthlyPlan extends MembershipPlan {
    public MonthlyPlan() { super("Monthly", 1); }
    @Override public double calculateFee(double baseRate) { return baseRate * 1; }
}

class QuarterlyPlan extends MembershipPlan {
    public QuarterlyPlan() { super("Quarterly", 3); }
    @Override public double calculateFee(double baseRate) { return (baseRate * 3) * 0.90; } // 10% off
}

class AnnualPlan extends MembershipPlan {
    public AnnualPlan() { super("Annual", 12); }
    @Override public double calculateFee(double baseRate) { return (baseRate * 12) * 0.75; } // 25% off
}

class GymMembership {
    private String memberName;
    private MembershipPlan plan;
    private double fee;
    private String status; // Active, Frozen, Expired

    public GymMembership(String memberName, MembershipPlan plan, double baseRate) {
        this.memberName = memberName;
        this.plan = plan;
        this.fee = plan.calculateFee(baseRate);
        this.status = "Active";
        System.out.printf("%s membership created for %s. Fee: ₹%,.2f. Status: %s.%n",
                plan.getName(), memberName, fee, status);
    }

    public void checkIn() {
        if ("Active".equals(status)) {
            System.out.printf("%s checked in successfully.%n", memberName);
        } else {
            System.out.printf("Check-in denied: %s's membership is %s.%n", memberName, status);
        }
    }

    public void freeze() {
        if ("Expired".equals(status)) {
            System.out.println("Cannot freeze an Expired membership.");
            return;
        }
        status = "Frozen";
        System.out.printf("%s's membership frozen. Status: %s.%n", memberName, status);
    }

    public void unfreeze() {
        if ("Expired".equals(status)) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return;
        }
        status = "Active";
        System.out.printf("%s's membership unfrozen. Status: %s.%n", memberName, status);
    }

    public void expire() {
        status = "Expired";
        System.out.printf("%s's membership expired. Status: %s.%n", memberName, status);
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        double baseRate = 1000.0;

        GymMembership asha = new GymMembership("Asha", new QuarterlyPlan(), baseRate);
        GymMembership ravi = new GymMembership("Ravi", new MonthlyPlan(), baseRate);

        asha.checkIn();
        asha.freeze();
        asha.checkIn();

        ravi.expire();
        ravi.freeze();
    }
}
