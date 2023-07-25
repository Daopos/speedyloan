package com.teasting.microfinance;

public class User {

    private String username;
    private String email;
    private String password;

    private String name;
    private String birthdate;
    private String contact;
    private String fbaccount;
    private  String address;

    private Boolean verification = false;

    public Boolean signup = false;

    private Boolean loanPending = false;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, boolean signup) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.signup = signup;
    }

    public User( String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User( ) {

    }

    public Boolean getVerification() {return verification;};
    public Boolean getLoanPending() {return loanPending;};
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getContact() {
        return contact;
    }

    public String getFbaccount() {
        return fbaccount;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setFbaccount(String fbaccount) {
        this.fbaccount = fbaccount;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }

    public void setLoanPending(Boolean loanPending) {
        this.loanPending = loanPending;
    }
}
