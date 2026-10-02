abstract class Vehicle {
    private String id;
    private boolean available;

    public Vehicle(String id) {
        this.id = id;
        this.available = true;
    }

    public String getId() { return id; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public abstract double calculateRental(int days);
}

class Sedan extends Vehicle {
    public Sedan(String id) { super(id); }
    @Override public double calculateRental(int days) { return days * 50.0; }
}

class SUV extends Vehicle {
    public SUV(String id) { super(id); }
    @Override public double calculateRental(int days) { return days * 80.0; }
}

class Truck extends Vehicle {
    public Truck(String id) { super(id); }
    @Override public double calculateRental(int days) { return days * 100.0; }
}

class VehicleRental {
    public static boolean rent(Vehicle v, String customer, int days) {
        if (!v.isAvailable()) {
            System.out.printf("%s is currently unavailable.%n", v.getId());
            return false;
        }
        v.setAvailable(false);
        double charge = v.calculateRental(days);
        System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n", v.getId(), customer, charge);
        return true;
    }

    public static void returnVehicle(Vehicle v, String customer) {
        v.setAvailable(true);
        System.out.printf("%s returned by %s.%n", v.getId(), customer);
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        VehicleRental.rent(sedanA, "Customer 1", 3);
        VehicleRental.rent(sedanA, "Customer 2", 2);
        VehicleRental.returnVehicle(sedanA, "Customer 1");
        VehicleRental.rent(suvB, "Customer 3", 5);
    }
}
