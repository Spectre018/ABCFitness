import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class ClassManagement {
    private List<Class> classes = new ArrayList<>();

    public String createClass(String name, String startDateStr, String endDateStr, String startTimeStr, int duration, int capacity) {
        try {
            LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
            LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);
            LocalTime startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ISO_TIME);

            if (capacity < 1) {
                return "Capacity must be at least 1";
            }
            if (endDate.isBefore(LocalDate.now())) {
                return "End date must be in the future";
            }

            Class newClass = new Class(name, startDate, endDate, startTime, duration, capacity);
            classes.add(newClass);

            return "Class created successfully";

        } catch (Exception e) {
            return "Invalid input format: " + e.getMessage();
        }
    }

    public List<Class> getClasses() {
        return classes;
    }
}