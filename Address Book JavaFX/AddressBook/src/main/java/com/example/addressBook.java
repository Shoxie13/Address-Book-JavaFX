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

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class addressBook extends Fields {
    // normal variables
    private List<String[]> tokens = new ArrayList<String[]>();
    private ArrayList<String> adr;

    // filename
    private final String filename = "src/main/java/docs/addressBook.txt";

    // variable that will iterate through the addresses
    private int currentIndex = 0;

    // no arg controller
    public addressBook() {
        // loads up the arrays with the info from txt file
        initialize();
    }

    // the function will write to the txt file when we click the update button
    // also it updates the array that will load the newly updated address
    void writeToFile(String filePath, String data) throws IOException {
        try {
            File newFile = new File(filename);
            newFile.delete();

            RandomAccessFile raf = new RandomAccessFile(newFile, "rw");
            raf.seek(0);
            raf.write(data.getBytes());

            initialize();

            raf.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    // append new address to the txt file
    // this function will also update the array so we can see the added address
    void appendToFile(String filePath, String data) throws IOException {
        try {
            RandomAccessFile raFile = new RandomAccessFile(filePath, "rw");

            // go at the end of the file and add the address
            raFile.seek(raFile.length());
            raFile.write(data.getBytes());

            initialize();

            raFile.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    // adds new address to the txt file
    // this function will also update the array so we can see the added address
    void addAddress() throws IOException {
        if (!validationCheck() && nameChecker()) {
            String fst = "", lst = "", city = "", pro = "", post = "";

            if (getFnameCheck()) {
                fst = getFirstName();
                if (getLnameCheck()) {
                    lst = getLastName();
                    if (getCityCheck()) {
                        city = getCity();
                        if (!getProvCheck()) {
                            pro = getProvince();
                            if (getPostCheck()) {
                                post = getPostal();

                                String str = ", " + fst + " " + lst + " " + city + " " + pro + " " + post;
                                appendToFile(filename, str);
                                showAlert(AlertType.INFORMATION, "Success!",
                                        "The address with name " + getFirstName() + " "
                                                + getLastName()
                                                + " was successfully added\nto the address book!",
                                        "Successful Entry!");
                                clearFields();
                            } else {
                                // this will color the text field red
                                colorField(getPostalField());

                                // it will pop up error alert when post code is not six characters
                                // also if it is not in the expected format
                                showAlert(AlertType.ERROR, "INVALID INPUT!",
                                        "The post code field has to be 6 characters and in format\n A1A2B2 or a1a2b2!",
                                        "Validation Error!");
                            }
                        } else {
                            // it will pop up error alert when select province is not changed
                            showAlert(AlertType.ERROR, "INVALID INPUT!",
                                    "The province field has to be different than the default value!",
                                    "Validation Error!");
                        }
                    } else {
                        // this will color the text field red
                        colorField(getCityField());

                        // checker to see which message should pop up
                        if (getFieldLength(getCityField()) <= 2) {
                            showAlert(AlertType.ERROR, "INVALID INPUT!",
                                    "The city field has to have more than 2 characters!", "Validation Error!");
                        } else {
                            showAlert(AlertType.ERROR, "INVALID INPUT!",
                                    "The city name field has to have less than 15 characters and/or\n without spaces! Dashes are allowed if needed AND first letter\nhas to be UPPERCASE.",
                                    "Validation Error!");
                        }
                    }
                } else {
                    // this will color the text field red
                    colorField(getLastField());

                    // checker to see which message should pop up
                    if (getFieldLength(getLastField()) <= 2) {
                        showAlert(AlertType.ERROR, "INVALID INPUT!",
                                "The last name field has to have more than 2 characters!", "Validation Error!");
                    } else {
                        showAlert(AlertType.ERROR, "INVALID INPUT!",
                                "The last name field has to have less than 20 characters!\nOnly >'< and >-< characthers allowed AND first letter\nhas to be UPPERCASE.",
                                "Validation Error!");
                    }
                }
            } else {
                // this will color the text field red
                colorField(getFirstField());
                // checker to see which message should pop up
                if (getFieldLength(getFirstField()) <= 2) {
                    showAlert(AlertType.ERROR, "INVALID INPUT!",
                            "The first name field has to have more than 2 characters!", "Validation Error!");
                } else {
                    showAlert(AlertType.ERROR, "INVALID INPUT!",
                            "The first name field has to have less than 20 characters!\n Only single names allowed and first letter\nhas to be UPPERCASE.",
                            "Validation Error!");
                }
            }
        } else {
            outlineFields();
        }
    }

    void updateAddress() throws IOException {
        if (!validationCheck()) {
            // Comparison for all the fields and matching for specific format using regex
            if (getFnameCheck()) {
                tokens.get(currentIndex)[0] = getFirstName();
                if (getLnameCheck()) {
                    tokens.get(currentIndex)[1] = getLastName();
                    if (getCityCheck()) {
                        tokens.get(currentIndex)[2] = getCity();
                        if (!getProvCheck()) {
                            tokens.get(currentIndex)[3] = getProvince();
                            if (getPostCheck()) {
                                tokens.get(currentIndex)[4] = getPostal();

                                alterString();

                                writeToFile(filename, alterString().substring(0, alterString().length() - 2));

                                showAlert(AlertType.INFORMATION, "Success!",
                                        "The address was successfully updated in the address book!",
                                        "Successful Update!");
                                clearFields();
                            } else {
                                // this will color the text field red
                                colorField(getPostalField());

                                // it will pop up error alert when post code is not six characters
                                // also if it is not in the expected format
                                showAlert(AlertType.ERROR, "INVALID INPUT!",
                                        "The post code field has to be 6 characters and in format\n A1A2B2 or a1a2b2!",
                                        "Validation Error!");
                            }
                        } else {
                            // it will pop up error alert when select province is not changed
                            showAlert(AlertType.ERROR, "INVALID INPUT!",
                                    "The province field has to be different than the default value!",
                                    "Validation Error!");
                        }
                    } else {
                        // this will color the text field red
                        colorField(getCityField());

                        // checker to see which message should pop up
                        if (getFieldLength(getCityField()) <= 2) {
                            showAlert(AlertType.ERROR, "INVALID INPUT!",
                                    "The city field has to have more than 2 characters!", "Validation Error!");
                        } else {
                            showAlert(AlertType.ERROR, "INVALID INPUT!",
                                    "The city name field has to have less than 15 characters and/or\n without spaces! Dashes are allowed if needed AND first letter\nhas to be UPPERCASE.",
                                    "Validation Error!");
                        }
                    }
                } else {
                    // this will color the text field red
                    colorField(getLastField());

                    // checker to see which message should pop up
                    if (getFieldLength(getLastField()) <= 2) {
                        showAlert(AlertType.ERROR, "INVALID INPUT!",
                                "The last name field has to have more than 2 characters!", "Validation Error!");
                    } else {
                        showAlert(AlertType.ERROR, "INVALID INPUT!",
                                "The last name field has to have less than 20 characters!\nOnly >'< and >-< characthers allowed AND first letter\nhas to be UPPERCASE.",
                                "Validation Error!");
                    }
                }
            } else {
                // this will color the text field red
                colorField(getFirstField());
                // checker to see which message should pop up
                if (getFieldLength(getFirstField()) <= 2) {
                    showAlert(AlertType.ERROR, "INVALID INPUT!",
                            "The first name field has to have more than 2 characters!", "Validation Error!");
                } else {
                    showAlert(AlertType.ERROR, "INVALID INPUT!",
                            "The first name field has to have less than 20 characters!\n Only single names allowed and first letter\nhas to be UPPERCASE.",
                            "Validation Error!");
                }
            }
        } else {
            outlineFields();
        }
    }

    // function that sets all values to the tokens in the array
    void setAllFields() {
        setFirstName(tokens.get(currentIndex)[0]);
        setLastName(tokens.get(currentIndex)[1]);
        setCity(tokens.get(currentIndex)[2]);
        setProvince(tokens.get(currentIndex)[3]);
        setPostal(tokens.get(currentIndex)[4]);
    }

    // this function will get the first address in the txt file
    void getFirstEntity() {
        currentIndex = 0;

        setAllFields();
    }

    // this function will get the next address in the txt file
    void getNextEntity() {
        try {
            if (currentIndex < tokens.size() - 1) {
                currentIndex++;
                setAllFields();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // this function will get the previous address in the txt file
    void getPrevEntity() {
        try {
            if (currentIndex > 0) {
                currentIndex--;
                setAllFields();
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // this function will get the last address in the txt file
    void getLastEntity() {
        currentIndex = tokens.size() - 1;
        setAllFields();
    }

    // function that will show alert depending on the passed alert type
    // also it accepts message and header of the alert
    void showAlert(Alert.AlertType alertType, String title, String message, String header) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }

    // this function will be used to clear all the fields from the clear button
    // or it will be used whenever we add or update address to reset the fields
    void clearFields() {
        setFirstName("");
        setLastName("");
        setCity("");
        setProvince("Select Province");
        setPostal("");
        //
        colorFieldReset(getFirstField());
        colorFieldReset(getLastField());
        colorFieldReset(getLastField());
        colorFieldReset(getCityField());
        colorFieldReset(getPostalField());
    }

    // validation check for the fields
    boolean validationCheck() {
        return getFirstName().isEmpty() || getLastName().isEmpty()
                || getCity().isEmpty() || getProvince().isEmpty()
                || getPostal().isEmpty();
    }

    // validation checker for the first and last name
    boolean nameChecker() {
        boolean validAddress = true;

        // checks if we have the same address with matching first name and last name
        for (String[] i : tokens) {
            for (String j : i) {
                if (getFirstName().matches(j)) {
                    for (String g : i) {
                        if (getLastName().matches(g)) {
                            showAlert(AlertType.ERROR, "INVALID INPUT!",
                                    "The address with the name " + getFirstName() + " "
                                            + getLastName()
                                            + " already exists\nin the book!",
                                    "Validation Error!");
                            validAddress = false;
                        }
                    }
                }
            }
        }
        return validAddress;
    }

    // initialize the file and local array for the addresses
    // or updates the current token array
    void initialize() {
        try {
            File file = new File(filename);
            // instantiating the RandomAccessFile
            RandomAccessFile raFile = new RandomAccessFile(file, "r");

            adr = new ArrayList<String>(Arrays.asList(raFile.readLine().split("\\, ")));

            tokens.clear();
            for (String i : adr) {
                tokens.add(i.split("\\s+"));
            }

            raFile.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // this function will outline the fields that respond to the conditions
    void outlineFields() {
        // outline the first name field if empty in red
        if (getFirstName().isEmpty()) {
            colorField(getFirstField());
        } else {
            colorFieldReset(getFirstField());
        }
        // outline the last name field if empty in red
        if (getLastName().isEmpty()) {
            colorField(getLastField());
        } else {
            colorFieldReset(getLastField());
        }
        // outline the city field if empty in red
        if (getCity().isEmpty()) {
            colorField(getCityField());
        } else {
            colorFieldReset(getCityField());
        }
        // outline the postal code field if empty in red
        if (getPostal().isEmpty()) {
            colorField(getPostalField());
        } else {
            colorFieldReset(getPostalField());
        }
    }

    // it will create a new string and alter it in the specified format
    // all that using regex pattern and matcher
    String alterString() {
        String newStr = "";

        final String regex = "((?:\\S+\\s){4}\\S+)\\s";
        final String subst = "$1, ";

        for (String[] str : tokens) {
            for (String i : str) {
                newStr += i + " ";
            }
        }

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(newStr);

        newStr = matcher.replaceAll(subst);

        return newStr;
    }

}
