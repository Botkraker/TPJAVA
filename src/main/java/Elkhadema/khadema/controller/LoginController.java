package Elkhadema.khadema.controller;

import java.io.IOException;

import Elkhadema.khadema.App;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");

        System.out.println("hello!");
    }
}