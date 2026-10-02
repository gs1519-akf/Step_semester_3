import java.util.*;

abstract class HotelRoom {
    private String roomNumber;

    public HotelRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }
    public abstract String getCategoryName();
    public abstract double getPricePerNight();
}

class StandardRoom extends HotelRoom {
    public StandardRoom(String roomNumber) { super(roomNumber); }
    @Override public String getCategoryName() { return "Standard Room"; }
    @Override public double getPricePerNight() { return 100.0; }
}

class DeluxeRoom extends HotelRoom {
    public DeluxeRoom(String roomNumber) { super(roomNumber); }
    @Override public String getCategoryName() { return "Deluxe Room"; }
    @Override public double getPricePerNight() { return 180.0; }
}

class HotelReservation {
    private String customer;
    private HotelRoom room;
    private String dateRange;
    private int nights;

    public HotelReservation(String customer, HotelRoom room, String dateRange, int nights) {
        this.customer = customer;
        this.room = room;
        this.dateRange = dateRange;
        this.nights = nights;
    }

    public String getCustomer() { return customer; }
    public HotelRoom getRoom() { return room; }
    public String getDateRange() { return dateRange; }
    public double getTotalPrice() { return room.getPricePerNight() * nights; }
}

class HotelManager {
    private Set<String> activeRoomDates = new HashSet<>();

    public void checkAvailability(HotelRoom room, String dateRange) {
        String key = room.getRoomNumber() + "_" + dateRange;
        if (!activeRoomDates.contains(key)) {
            System.out.printf("%s %s is available from %s.%n", room.getCategoryName(), room.getRoomNumber(), dateRange);
        } else {
            System.out.printf("%s %s is not available from %s.%n", room.getCategoryName(), room.getRoomNumber(), dateRange);
        }
    }

    public HotelReservation book(String customer, HotelRoom room, String dateRange, int nights) {
        String key = room.getRoomNumber() + "_" + dateRange;
        if (activeRoomDates.contains(key)) {
            System.out.printf("%s %s is not available from %s.%n", room.getCategoryName(), room.getRoomNumber(), dateRange);
            return null;
        }

        activeRoomDates.add(key);
        HotelReservation res = new HotelReservation(customer, room, dateRange, nights);
        System.out.printf("Reservation confirmed for %s, %s %s (%s). Price: $%.2f.%n",
                customer, room.getCategoryName(), room.getRoomNumber(), dateRange, res.getTotalPrice());
        return res;
    }

    public void cancel(HotelReservation res) {
        if (res == null) return;
        String key = res.getRoom().getRoomNumber() + "_" + res.getDateRange();
        activeRoomDates.remove(key);
        System.out.printf("Reservation for %s, %s %s (%s) cancelled successfully.%n",
                res.getCustomer(), res.getRoom().getCategoryName(), res.getRoom().getRoomNumber(), res.getDateRange());
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        HotelManager manager = new HotelManager();
        HotelRoom room101 = new StandardRoom("101");
        HotelRoom room201 = new DeluxeRoom("201");

        manager.checkAvailability(room101, "Jan 1 to Jan 5");
        HotelReservation resA = manager.book("Customer A", room101, "Jan 1-5", 4);

        // Overlapping attempt
        manager.book("Customer B", room101, "Jan 1-5", 4);

        manager.cancel(resA);

        manager.book("Customer C", room201, "Feb 10-12", 2);
    }
}
