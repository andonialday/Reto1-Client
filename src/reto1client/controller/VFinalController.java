/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1client.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import reto1libraries.object.User;

/**
 * FXML VFinalController class Controller for the VFinal JavaFX scene
 *
 * @version 1.2
 * @author Andoni Alday
 */
public class VFinalController {

    @FXML
    private Button btnClose;

    @FXML
    private Label lblMessage;

    @FXML
    private Hyperlink hlLogOut;

    private Stage stage;
    private String msg;
    private User usr;
    private Logger logger;

    /**
     *
     * @param root
     */
    public void initStage(Parent root) {
        logger.info("Initializing Post SignIn process ...");
        msg = "Bienvenido " + usr.getFullName();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("VFinal");
        stage.setResizable(false);
        lblMessage.setText(msg);
        btnClose.setMnemonicParsing(true);
        btnClose.setText("_Close");
        btnClose.setOnAction(this::closeVFinal);
        hlLogOut.setOnAction(this::logOut);
    }

    /**
     * Method of the Close Button (btClose) that closes the scene and the
     * program completelly
     *
     * @param event the event linked to clicking on the button;
     */
    public void closeVFinal(ActionEvent event) {
        logger.info("Requesting confirmation for application closing...");
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Está Cerrando el Programa");
        alert.setHeaderText("¿Seguro que desea cerrar el programa?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            logger.info("Closing the application");
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
        logger.info("Requesting confirmation for Signing Out...");
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cerrando Sesión");
        alert.setHeaderText("¿Seguro que desea cerrar sesión?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            logger.info("Signing Out");
        /*    FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("reto1client.view/VSignIn.fxml"));
            Parent root = (Parent) loader.load();
            VSignInController controller = ((VSignInController) loader.getController());
            controller.setStage(this.stage);
            controller.initStage(root);
            */
        }
    }

    // Setters
    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.usr = user;
    }

    /**
     *
     * @param primaryStage
     */
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

}
