package sample;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/** Appointment */
public class Appointment {

    /** id of Appointment */
    private int appointmentId;
    /** title of Appointment */
    private String title;
    /** description of Appointment */
    private String description;
    /** location of Appointment */
    private String location;
    /** type of Appointment */
    private String type;
    /** start time of Appointment */
    private LocalDateTime start;
    /** end time of Appointment */
    private LocalDateTime end;
    /** customer id of Appointment */
    private int custId;
    /** user id of Appointment */
    private int userId;
    /** contact id of Appointment */
    private int contactId;

    public Appointment(int appointmentId, String title, String description, String location,
                       String type, LocalDateTime start, LocalDateTime end, int custId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.custId = custId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /** gets Appointment id */
    public int getAppointmentId() {
        return appointmentId;
    }

    /** sets Appointment id */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /** gets Appointment title */
    public String getTitle() {
        return title;
    }

    /** sets Appointment title */
    public void setTitle(String title) {
        this.title = title;
    }

    /** gets Appointment description */
    public String getDescription() {
        return description;
    }

    /** sets Appointment description */
    public void setDescription(String description) {
        this.description = description;
    }

    /** gets Appointment location */
    public String getLocation() {
        return location;
    }

    /** sets Appointment location */
    public void setLocation(String location) {
        this.location = location;
    }

    /** gets Appointment type */
    public String getType() {
        return type;
    }

    /** sets Appointment type */
    public void setType(String type) {
        this.type = type;
    }

    /** gets Appointment start time */
    public LocalDateTime getStart() {
        return start;
    }

    /** sets Appointment start time */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /** gets Appointment end time */
    public LocalDateTime getEnd() {
        return end;
    }

    /** sets Appointment end time */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /** gets Appointment customer id */
    public int getCustId() {
        return custId;
    }

    /** sets Appointment customer id */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /** gets Appointment user id */
    public int getUserId() {
        return userId;
    }

    /** sets Appointment user id */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** gets Appointment contact id */
    public int getContactId() {
        return contactId;
    }

    /** sets Appointment contact id */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
