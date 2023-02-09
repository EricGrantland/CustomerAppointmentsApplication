package sample;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 * Appointment of given month and type
 */
public class ApptMonthType {
    /** type of Appointment */
    private String type;
    /** date of Appointment */
    private LocalDate date;
    /** Month of Appointment */
    private Month month;
    /** Year of Appointment */
    private int year;
    /** Count of Appointments of given month/type */
    private int count;


    public ApptMonthType(String type, LocalDate date) {
        this.type = type;
        this.date = date;
        this.month = date.getMonth();
        this.year = date.getYear();
        this.count = 1;

    }

    /** gets Appointment type */
    public String getType() {
        return type;
    }

    /** sets Appointment type */
    public void setType(String type) {
        this.type = type;
    }

    /** gets Appointment date */
    public LocalDate getDate() {
        return date;
    }

    /** sets Appointment date */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /** gets Appointment month */
    public Month getMonth() {
        return month;
    }

    /** gets Appointment year */
    public int getYear() {
        return year;
    }

    /** gets count of given Appt month/type */
    public int getCount() {
        return count;
    }

    /** sets count of given Appt month/type */
    public void setCount(int count) {
        this.count = count;
    }
}
