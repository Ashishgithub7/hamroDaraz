package com.ecommerce.hamroDaraz.MessageConstant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class ErrorMessageConstant {

    public static final String USERNAME_ERROR="Invalid username";
    public static final String PASSWORD_ERROR="Invalid password";
    public static final String EMAIL_ERROR="Invalid email";
    public static final String PHONE_ERROR="Invalid phone number";
    public static final String FIRST_NAME_ERROR="Invalid first name";
    public static final String LAST_NAME_ERROR="Invalid last name";
    public static final String CONFIRM_PASSWORD_ERROR="Invalid confirm password";

    public static final String PASSWORD_DOES_NOT_MATCH_ERROR="Confirm password does not match";

    public static final String PHONE_NUMBER_ALREADY_EXISTS="User with this phone number already exists";
    public static final String EMAIL_ALREADY_EXISTS="User with this email already exists";
}
