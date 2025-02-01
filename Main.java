public class Main {
    public static void main(String[] args) {
        ClassManagement classManagement = new ClassManagement();
        BookingManagement bookingManagement = new BookingManagement(classManagement);

        // Create classes
        System.out.println(classManagement.createClass("Pilates", "2025-10-26", "2025-10-30", "14:00", 60, 10));
        System.out.println(classManagement.createClass("Yoga", "2025-10-26", "2025-10-28", "09:00", 90, 5));

        // Book classes
        System.out.println(bookingManagement.bookClass("Alice", "Pilates", "2025-10-27"));
        System.out.println(bookingManagement.bookClass("Bob", "Pilates", "2025-10-27"));
        System.out.println(bookingManagement.bookClass("Charlie", "Yoga", "2025-10-28"));
        System.out.println(bookingManagement.bookClass("David", "Pilates", "2025-10-27"));
        System.out.println(bookingManagement.bookClass("Eve", "Pilates", "2025-10-27"));
        System.out.println(bookingManagement.bookClass("Frank", "Pilates", "2025-10-27"));
        System.out.println(bookingManagement.bookClass("Grace", "Pilates", "2025-10-27"));
        System.out.println(bookingManagement.bookClass("Henry", "Pilates", "2025-10-27"));
        System.out.println(bookingManagement.bookClass("Ivy", "Pilates", "2025-10-27"));

        // Check bookings
        System.out.println(bookingManagement.searchBookings("Frank", null, null));
        System.out.println(bookingManagement.searchBookings("Charlie", null, null));
        System.out.println(bookingManagement.searchBookings(null, "2025-10-26", "2025-10-30"));
        System.out.println(bookingManagement.searchBookings(null, "2025-10-22", "2025-10-27"));
        System.out.println(bookingManagement.searchBookings(null, null, "2025-10-27"));
        System.out.println(bookingManagement.searchBookings(null, "2025-10-22", null));
    }
}


