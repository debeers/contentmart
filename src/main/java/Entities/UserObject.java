package Entities;

/**
 * Created by CMG_TEST on 13.10.2015.
 */
public class UserObject {


    private String nickname = "";
    private String firstname = "";
    private String lastname = "";
    private String phone = "";
    private String pan = "";

    private String state = "";
    private String city = "";
    private String address = "";
    private String zip = "";
    private String bio = "";


   public UserObject(){}


    public UserObject(String nickname, String firstname, String lastname, String phone,
                      String pan, String state, String city, String address, String zip, String bio) {

        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.pan = pan;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zip = zip;
        this.bio = bio;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
