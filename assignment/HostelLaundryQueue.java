abstract class WashType {
    private String name;
    private int durationMinutes;
    private double charge;

    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }

    public String getName() { return name; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getCharge() { return charge; }
}

class QuickWash extends WashType {
    public QuickWash() { super("Quick", 30, 20.0); }
}

class NormalWash extends WashType {
    public NormalWash() { super("Normal", 45, 30.0); }
}

class HeavyWash extends WashType {
    public HeavyWash() { super("Heavy", 60, 45.0); }
}

class WashingMachine {
    private String machineId;
    private boolean busy;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
    }

    public String getMachineId() { return machineId; }
    public boolean isBusy() { return busy; }

    public boolean startWash(String student, WashType washType) {
        if (busy) {
            System.out.printf("Machine %s is currently busy.%n", machineId);
            return false;
        }
        busy = true;
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(), machineId, student, washType.getDurationMinutes(), washType.getCharge());
        return true;
    }

    public void completeCycle() {
        if (busy) {
            busy = false;
            System.out.printf("%s cycle completed. %s is now free.%n", machineId, machineId);
        }
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashType quick = new QuickWash();
        WashType normal = new NormalWash();
        WashType heavy = new HeavyWash();

        m1.startWash("Asha", quick);
        m1.startWash("Ravi", heavy);
        m2.startWash("Ravi", heavy);
        m1.completeCycle();
        m1.startWash("Neha", normal);
    }
}
