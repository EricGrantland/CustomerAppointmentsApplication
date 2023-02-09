package sample;

/** Contact */
public class Contact {

    /** id of contact */
    private int contactId;
    /** name of contact */
    private String contactName;
    /** email address of contact */
    private String contactEmail;

    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /** gets contact id */
    public int getContactId() {
        return contactId;
    }

    /** sets contact id */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** gets contact name */
    public String getContactName() {
        return contactName;
    }

    /** sets contact name */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** gets contact email address */
    public String getContactEmail() {
        return contactEmail;
    }

    /** sets contact email address */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
