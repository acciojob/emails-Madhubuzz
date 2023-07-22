package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId) {
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(password) && isPasswordValid(newPassword)) {
            this.password = newPassword;
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed. Make sure the old password is correct and the new password meets all requirements.");
        }
    }

    private boolean isPasswordValid(String password) {
        // Password must contain at least 8 characters, one uppercase letter, one lowercase letter, one digit, and one special character.
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(pattern);
    }
}