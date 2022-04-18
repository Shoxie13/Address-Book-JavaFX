/**********************************************
Workshop 5
Course:JAV444 - Semester 4
Last Name: Abdi
First Name: Tareq
ID: 123809196
Section: 
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature - TA
Date: 09/03/2022
**********************************************/

package com.example;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;

public abstract class Fields {
    // variables from the program
    @FXML
    private TextField firstNameTxt, lastNameTxt, cityTxt, postalTxt;

    // variable from the choice box in the program
    @FXML
    private ChoiceBox<String> provinceTxt = new ChoiceBox<>();

    // no arg constuctor
    Fields() {
    };

    // getter functions for the fields
    String getFirstName() {
        return firstNameTxt.getText().trim();
    }

    String getLastName() {
        return lastNameTxt.getText().trim();
    }

    String getCity() {
        return cityTxt.getText().trim();
    }

    String getProvince() {
        return provinceTxt.getValue().toString().trim();
    }

    String getPostal() {
        return postalTxt.getText().trim().toUpperCase();
    }

    // setter functions for the fields
    void setFirstName(String str) {
        firstNameTxt.setText(str);
    }

    void setLastName(String str) {
        lastNameTxt.setText(str);
    }

    void setCity(String str) {
        cityTxt.setText(str);
    }

    void setProvince(String str) {
        provinceTxt.setValue(str);
    }

    void setPostal(String str) {
        postalTxt.setText(str);
    }

    // getter functions for the different fields
    TextField getFirstField() {
        return firstNameTxt;
    }

    TextField getLastField() {
        return lastNameTxt;
    }

    TextField getCityField() {
        return cityTxt;
    }

    TextField getPostalField() {
        return postalTxt;
    }

    // checker functions for the input in the fields
    boolean getFnameCheck() {
        return firstNameTxt.getText().trim().length() > 2
                && firstNameTxt.getText().trim().length() <= 20
                && firstNameTxt.getText().trim().matches("^[A-Z][a-z]+$");
    }

    boolean getLnameCheck() {
        return lastNameTxt.getText().trim().length() > 2 && lastNameTxt.getText().trim().length() <= 20
                && lastNameTxt.getText().trim().matches("^[A-Z][a-z]+(((\\'|\\-)?([A-Za-z])+))?$");
    }

    boolean getCityCheck() {
        return cityTxt.getText().trim().length() > 2 && cityTxt.getText().trim().length() <= 15
                && cityTxt.getText().trim().matches("^[A-Z][a-z]+(?:[\\-][a-zA-Z]+)*$");
    }

    boolean getProvCheck() {
        return provinceTxt.getValue().toString().matches("Select Province");
    }

    boolean getPostCheck() {
        return postalTxt.getText().trim().length() == 6
                && postalTxt.getText().matches("^[A-Za-z]\\d[A-Za-z]?\\d[A-Za-z]\\d$");
    }

    // function that sets the style of the fields to red
    void colorField(TextField cc) {
        cc.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
    }

    // function that resets the style of the fields
    void colorFieldReset(TextField cc) {
        cc.setStyle("");
    }

    // get the length of the text in the different fields
    int getFieldLength(TextField cc) {
        return cc.getText().trim().length();
    }
}
