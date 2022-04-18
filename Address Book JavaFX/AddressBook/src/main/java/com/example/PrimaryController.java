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

import java.io.*;

import javafx.fxml.FXML;

public class PrimaryController extends addressBook {

    // no arg controller that will load up the file into the array list as tokens
    public PrimaryController() {
        super();
    };

    // button that will add an address to the current list of addresses
    @FXML
    private void btnAdd() throws IOException {
        addAddress();
    }

    // button that will show us the first entity in our address book
    @FXML
    private void btnFirst() {
        getFirstEntity();
    }

    // button that will show us the next entity from our current one
    @FXML
    private void btnNext() {
        getNextEntity();
    }

    // button that will show us the previous entity from our current one
    @FXML
    private void btnPrev() {
        getPrevEntity();
    }

    // button that will show us the last entity in the address book
    @FXML
    private void btnLast() {
        getLastEntity();
    }

    // button that updates single entity in the address book
    @FXML
    private void btnUpdate() throws IOException {
        updateAddress();
    }

    // button that clears all text and choice box fields/lists
    @FXML
    private void btnClear() {
        clearFields();
    }
}
