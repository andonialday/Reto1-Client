/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1client.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import reto1libraries.object.User;

/**
 * FXML VFinalController class Controller for the VFinal JavaFX scene
 *
 * @version 1.2
 * @author Andoni Alday
 */
public class VFinalController implements Initializable {

    @FXML
    private Button btClose;

    @FXML
    private Label lblMessage;

    @FXML
    private Hyperlink hlLogOut;

    private String msg;
    private User usr;

    /**
     * Constructor for the VFinal Controller
     */
    public VFinalController() {
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            
//receiveData();
        lblMessage.setText(msg);
        btClose.setMnemonicParsing(true);
        btClose.setText("_Close");
        btClose.setOnAction(this::closeVFinal);
        hlLogOut.setOnAction(this::logOut);
    }

    /**
     * Method of the Close Button (btClose) that closes the scene and the
     * program completelly
     *
     * @param event the event linked to clicking on the button;
     */
    public void closeVFinal(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Está Cerrando el Programa");
        alert.setHeaderText("¿Seguro que desea cerrar el programa?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    /**
     * Method of the LogOut Hyperlink (hlLogOut) that nds thecurrent users
     * session on the application and returns to the SignIn window
     *
     * @param event the event linked to clicking on the button;
     */
    public void logOut(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cerrando Sesión");
        alert.setHeaderText("¿Seguro que desea cerrar sesión?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        }
    }

    /**
     *
     * @param event
     */
    private void receiveData(Event event) {
        Node node = (Node) event.getSource();
        this.usr = (User) node.getUserData();
        this.msg = "Bienvenido " + usr.getFullName();
    }

}
