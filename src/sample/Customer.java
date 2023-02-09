package sample;

/** Customer */
public class Customer {

    /** id of customer */
    private int customerId;
    /** name of customer */
    private String customerName;
    /** address of customer */
    private String address;
    /** postal code of customer */
    private String postalCode;
    /** phone number of customer */
    private String phoneNumber;
    /** division id of customer */
    private int divisionId;

    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
    }

    /** gets customer id */
    public int getCustomerId() {
        return customerId;
    }

    /** sets customer id */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** gets customer name */
    public String getCustomerName() {
        return customerName;
    }

    /** sets customer id */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** gets customer address */
    public String getAddress() {
        return address;
    }

    /** sets customer id */
    public void setAddress(String address) {
        this.address = address;
    }

    /** gets customer postal code */
    public String getPostalCode() {
        return postalCode;
    }

    /** sets customer postal code */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** gets customer phone number */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** sets customer phone number */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** gets customer division id */
    public int getDivisionId() {
        return divisionId;
    }

    /** sets customer division id */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}
