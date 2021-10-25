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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import reto1libraries.object.User;

/**
 * FXML VFinal Controller class Controller for the VFinal JavaFX scene
 *
 * @author Andoni Alday
 */
public class VFinalController implements Initializable {

    private String msg = "Bienvenido ";
    private User usr;

    public VFinalController(User usr) {
        this.usr = usr;
    }

    @FXML
    private Button btClose;

    @FXML
    private Label lblMessage;

    @FXML
    private Hyperlink hlLogOut;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        msg += usr.getFullName();
        btClose.addEventHandler(ActionEvent.ACTION, this::closeVFinal);
    }

    public void closeVFinal() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Está Cerrando el Programa");
        alert.setHeaderText("¿Seguro que desea cerrar el programa?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        } 
    }

}
