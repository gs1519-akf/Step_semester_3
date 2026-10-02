interface Ringable {
    String ring();
}

class AlarmClock implements Ringable {
    private String time;

    public AlarmClock(String time) {
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}

class Doorbell implements Ringable {
    private String location;

    public Doorbell(String location) {
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}

public class MorningWakeUpCircuit {

    public static void ringAll(Ringable[] devices) {
        if (devices == null) return;
        for (Ringable device : devices) {
            if (device != null) {
                System.out.println(device.ring());
            }
        }
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        Doorbell d = new Doorbell("Front Door");

        System.out.println(a.ring());
        System.out.println(d.ring());

        System.out.println("\n--- ringAll ---");
        ringAll(new Ringable[]{a, d});
    }
}
