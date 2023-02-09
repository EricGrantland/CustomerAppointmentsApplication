package sample;

import helper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.*;

public class Data {

    private static ObservableList<LocalTime> startTimes = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> endTimes = FXCollections.observableArrayList();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Country> countries = FXCollections.observableArrayList();
    private static ObservableList<FirstLvlDiv> divisions = FXCollections.observableArrayList();
    private static ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private static ObservableList<ApptMonthType> apptMonthTypes = FXCollections.observableArrayList();
//    public static ObservableList<LocalTime>

    public static ObservableList<Customer> getCustomers() throws SQLException {
        return CustomerQuery.select();
    }

    public static ObservableList<Appointment> getAppointments() throws SQLException {
        return AppointmentQuery.select();
    }

    public static ObservableList<User> getUsers() throws SQLException {
        allUsers.clear();
        UserQuery.select();
        return allUsers;
    }

    public static ObservableList<Country> getCountries() throws SQLException {
        countries.clear();
        CountryQuery.select();
        return countries;
    }

    public static ObservableList<FirstLvlDiv> getDivisions() throws SQLException {
        divisions.clear();
        FirstlvlDivQuery.select();
        return divisions;
    }


    public static ObservableList<Contact> getContacts() throws SQLException {
        contacts.clear();
        ContactQuery.select();
        return contacts;
    }

    public static ObservableList<ApptMonthType> getApptMonthType() throws SQLException {
        apptMonthTypes.clear();
        ApptMonthTypeQuery.select();
        return apptMonthTypes;
    }

    public static void addUser(User user) {
        allUsers.add(user);
    }

    public static void addCountry(Country country) {
        countries.add(country);
    }

    public static void addDivision(FirstLvlDiv division) {
        divisions.add(division);
    }

    public static void addContact(Contact contact) {
        contacts.add(contact);
    }

    public static void addApptMonthType(ApptMonthType appt) throws SQLException {

        for (ApptMonthType appt1 : apptMonthTypes) {

            if (appt.getMonth().toString().equals(appt1.getMonth().toString()) && appt.getType().equals(appt1.getType()) &&
                appt.getYear() == appt1.getYear()) {
                appt1.setCount(appt1.getCount() + 1);
                return;
            }

        }
        apptMonthTypes.add(appt);
    }

    public static int getDivIdByDiv(String div) throws SQLException {
        for (FirstLvlDiv firstLvlDiv : getDivisions()) {
            if (div.equals(firstLvlDiv.getDiv())) {
                return firstLvlDiv.getDivId();
            }
        }
        return -1;
    }


    public static Country getCountryByDiv(int divId) throws SQLException {
        for (FirstLvlDiv div : getDivisions()) {
            if (div.getDivId() == divId) {
                for (Country country : getCountries()) {
                    if (country.getCountryId() == div.getCountryId()) {
                        return country;
                    }
                }
            }
        }
        return null;
    }

    public static FirstLvlDiv getDiv(int divId) throws SQLException {
        for (FirstLvlDiv div : getDivisions()) {
            if (div.getDivId() == divId) {
                return div;
            }
        }
        return null;
    }

    public static ObservableList<String> getDivsForCountry(Country country) throws SQLException {
        ObservableList<String> divisions = FXCollections.observableArrayList();
        for (FirstLvlDiv div : getDivisions()) {
            if (div.getCountryId() == country.getCountryId()) {
                divisions.add(div.getDiv());
            }
        }
        return divisions;
    }

    public static ObservableList<String> getDivsForCountryName(String countryName) throws SQLException {
        ObservableList<String> divisions = FXCollections.observableArrayList();
        for (Country country : getCountries()) {
            if (country.getCountry().equals(countryName)) {
                for (FirstLvlDiv div : getDivisions()) {
                    if (div.getCountryId() == country.getCountryId()) {
                        divisions.add(div.getDiv());
                    }
                }
            }
        }

        return divisions;
    }

    public static int getContactIdForContactName(String contactName) throws SQLException {
        for (Contact contact : getContacts()) {
            if (contact.getContactName().equals(contactName)) {
                return contact.getContactId();
            }
        }
        return -1;
    }

    public static String getContactById(int contactId) throws SQLException {
        for (Contact contact : Data.getContacts()) {
            if (contact.getContactId() == contactId) {
                return contact.getContactName();
            }
        }
        return null;
    }

    public static Contact getContactByName(String name) throws SQLException {
        for (Contact contact : Data.getContacts()) {
            if (contact.getContactName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    public static Customer getCustomerByName(String name) throws SQLException {
        for (Customer customer : Data.getCustomers()) {
            if (customer.getCustomerName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public static Customer getCustomerById(int id) throws SQLException {
        for (Customer customer : Data.getCustomers()) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null;
    }

    private static void loadStartEndTimes() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8,0), ZoneId.of("America/New_York"));
        LocalDateTime localStart = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localEnd = localStart.plusHours(14);
        while(localStart.isBefore(localEnd)) {
            startTimes.add(localStart.toLocalTime());
            localStart = localStart.plusMinutes(30);
            endTimes.add(localStart.toLocalTime());
        }
    }

    public static ObservableList<LocalTime> getStartTimes() {
        if (startTimes.isEmpty()) {
            loadStartEndTimes();
        }
        return startTimes;
    }

    public static ObservableList<LocalTime> getEndTimes() {
        if (endTimes.isEmpty()) {
            loadStartEndTimes();
        }
        return endTimes;
    }


}
