package com.teasting.microfinance;

import java.util.ArrayList;

public class UserDatabase {

    private static ArrayList<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    public static boolean checkUserExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUserEmailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }




    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public static boolean updateMyProfile(String username, String name, String birthdate, String contact, String fbaccount, String address, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setName(name);
                user.setAddress(address);
                user.setBirthdate(birthdate);
                user.setContact(contact);
                user.setFbaccount(fbaccount);
                user.setPassword(password);
                return true;
            }
        }
        return false;
    }

    public static boolean updateBasic(String username, String address) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setAddress(address);
                return true;
            }
        }
        return false;
    }

    public static boolean updateIdentification(String username, String name, String birthdate, String contact) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setName(name);
                user.setBirthdate(birthdate);
                user.setContact(contact);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    public static boolean updateVerification(String username, Boolean verify) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setVerification(verify);
                return true;
            }
        }
        return false;
    }


    public static boolean updateloanPending(String username, Boolean verify) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setLoanPending(verify);
                return true;
            }
        }
        return false;
    }




}
