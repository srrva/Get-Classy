package soClassy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Calendar {

    private final String ID;
    private final String firstName;
    private final String lastName;
    private final String title;
    static LocalDate birthdate;

    public Calendar(String ID, String firstName, String lastName, String title, LocalDate birthdate) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.birthdate = birthdate;
        }

        public Calendar(String ID, String firstName, String lastName, String title, String birthdateString) {
            this.ID = ID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.title = title;
            this.birthdate = LocalDate.parse(birthdateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public static int getAge() {
            LocalDate currentDate = LocalDate.now();
            return currentDate.getYear() - birthdate.getYear();
        }

        public int getAge(int year) {
            return year - birthdate.getYear();
        }
        public String toString() {
            return String.format("| %-15s | %-15s | %-10s | %-15s | %-4d |", ID, firstName, lastName, title, birthdate.getYear());
        }
    }

