/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1client.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import reto1libraries.exception.ClientServerConnectionException;
import reto1libraries.exception.DBConnectionException;
import reto1libraries.exception.LoginOnUseException;
import reto1libraries.logic.Signable;
import reto1libraries.object.User;

/**
 * FXML Controller class Controlador de la ventana VSignUp que ejecuta todos los
 * metodos para una correcta ejecucion de la misma
 *
 *
 * @author Aitor Perez Andoni Alday
 */
public class VSignUpController {

    @FXML
    private TextField txtName;
    @FXML
    private Label lblName;
    @FXML
    private TextField txtLogin;
    @FXML
    private Label lblLogin;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblPassword;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private Label lblCPassword;
    @FXML
    private Button btSignUp;
    @FXML
    private Button btBack;
    private Boolean bName = false, bLogin = false,
            bEmail = false, bPassword = false,
            bCPassword = false, bWarning = false;

    @FXML
    private Label label;
    //Patron para el email permite Mayusculas,Minusculas,numeros y es obligatorio usar este formato 
    //(prueba@prueba.com)
    private final Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    //Patron para el Login que solo permite Mayusculas,Minusculas,numeros y no admite espacios.
    private final Pattern log = Pattern
            .compile("^[A-Za-z0-9-]*$");
    //Patron para la Contraseña requiere y permite  Numeros,Mayusculas,Minusculas,Simbolos especiales y no admite espacios
    private final Pattern pass = Pattern
            .compile("^.*(?=.{6,25})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+*=]).*$");
    //Patron para el nombre requiere y permite Mayusculas,Minusculas,Numeros y espacios.
    private final Pattern namePat = Pattern
            .compile("^[A-Za-z0-9]*[A-Za-z0-9 ][A-Za-z0-9 ]*$");

    private String name, login, email, password, cpassword, warning;

    private Stage stage;

    private static Logger LOGGER = Logger.getLogger("package.class");

    /**
     * Initializes the controller class.
     *
     * @param root Elemento recibido desde la ventana VSignIn que permite que la
     * ventana muestre sus elementos hijos (Cuadros de texto,Botones...)
     */
    public void initStage(Parent root) {

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/reto1client/view/javaFXUIStyles.css");
        stage.setTitle("VSignUp");
        stage.setResizable(false);

        stage.setOnCloseRequest(this::closeVSignUpX);
        stage.setScene(scene);
        txtName.requestFocus();

        toolTips();
        lblVisible();
        btSignUp.setOnAction(this::signUp);
        btSignUp.setDisable(true);
        btBack.setOnAction(this::backSingIn);
        txtName.textProperty().addListener(this::txtNameVal);
        txtLogin.textProperty().addListener(this::txtLoginVal);
        txtEmail.textProperty().addListener(this::txtEmailVal);
        txtPassword.textProperty().addListener(this::txtPasswordVal);
        txtConfirmPassword.textProperty().addListener(this::txtConfirmPasswordVal);

        stage.show();

    }

    /**
     * Recoge todos los tooltips de cada text area
     */
    @FXML
    public void toolTips() {
        txtName.getTooltip();
        txtLogin.getTooltip();
        txtEmail.getTooltip();
        txtPassword.getTooltip();
        txtConfirmPassword.getTooltip();
    }

    /**
     * Oculta todas las lavels de error al iniciar la ventana
     */
    public void lblVisible() {
        lblName.setVisible(false);

        lblLogin.setVisible(false);
        lblEmail.setVisible(false);
        lblPassword.setVisible(false);
        lblCPassword.setVisible(false);
    }

    /**
     * Metodo para validar que el texto del campo Name una vez eliminados los
     * espacios del principio y del final no es nulo
     *
     * @param observableValue Campo a validar campo texto Name
     * @param oldValue Valor previo del texto
     * @param newValue Valor que se va a validar
     */
    @FXML
    public void txtNameVal(ObservableValue observableValue,
            String oldValue,
            String newValue) {
        if (!newValue.trim().equals("")) {
            bName = true;

        } else {

            bName = false;

        }

        buttonActivation();
    }

    /**
     * Metodo para validar que el texto del campo Login una vez eliminados los
     * espacios del principio y del final no es nulo
     *
     * @param observableValue Campo a validar campo texto Login
     * @param oldValue Valor previo del texto
     * @param newValue Valor que se va a validar
     */
    public void txtLoginVal(ObservableValue observableValue,
            String oldValue,
            String newValue) {
        if (!newValue.trim().equals("")) {
            bLogin = true;

        } else {

            bLogin = false;

        }

        buttonActivation();
    }

    /**
     * Metodo para validar que el texto del campo Email una vez eliminados los
     * espacios del principio y del final no es nulo
     *
     * @param observableValue Campo a validar campo texto Email
     * @param oldValue Valor previo del texto
     * @param newValue Valor que se va a validar
     */
    public void txtEmailVal(ObservableValue observableValue,
            String oldValue,
            String newValue) {
        if (!newValue.trim().equals("")) {
            bEmail = true;

        } else {

            bEmail = false;

        }

        buttonActivation();
    }

    /**
     * Metodo para validar que el texto del campo Name una vez eliminados los
     * espacios del principio y del final no es nulo
     *
     * @param observableValue Campo a validar campo texto Password
     * @param oldValue Valor previo del texto
     * @param newValue Valor que se va a validar
     */
    public void txtPasswordVal(ObservableValue observableValue,
            String oldValue,
            String newValue) {
        if (!newValue.trim().equals("")) {
            bPassword = true;

        } else {

            bPassword = false;

        }

        buttonActivation();
    }

    /**
     * Metodo para validar que el texto del campo Confirm Password una vez
     * eliminados los espacios del principio y del final no es nulo
     *
     * @param observableValue Campo a validar campo texto Confirm Password
     * @param oldValue Valor previo del texto
     * @param newValue Valor que se va a validar
     */
    public void txtConfirmPasswordVal(ObservableValue observableValue,
            String oldValue,
            String newValue) {
        if (!newValue.trim().equals("")) {
            bCPassword = true;

        } else {

            bCPassword = false;

        }

        buttonActivation();
    }

    /**
     * Metodo para que ejecuta la validacion del contenido de los campos
     * ejecutando un metodo de registro si los campos son validos y sino
     * ejecutando mensaje de error y mostrando las indicaciones de error para
     * cada campo(Label y tooltip).
     *
     * @param event Metodo se ejecuta al pulsar el boton SignUp
     */
    public void signUp(ActionEvent event) {
        warning = "Los sigientes campos son erroneos: ";
        login = txtLogin.getText().trim();
        name = txtName.getText().trim();
        email = txtEmail.getText().trim();
        password = txtPassword.getText().trim();
        cpassword = txtConfirmPassword.getText().trim();

        //Comprobacion si el nombre cumple los requisitos
        // El Name tendrá máximo 25 carácteres alfanuméricos
        //(se admiten espacios, pero si comienza o termina con espacios, estos serán ignorados)
        Matcher matchname = namePat.matcher(name);
        if (name.length() <= 25 && matchname.matches()) {

            lblName.setVisible(false);
        } else {

            warning += txtName.getPromptText();
            bWarning = true;

            lblName.setVisible(true);
            bName = false;

            txtName.setText("");

        }
        //Comprobacion si el login cumple los requisitos
        // El Login debe ser mínimo 5 carácteres, máximo 25, alfanuméricos (no se admiten espacios)
        Matcher matchlog = log.matcher(login);
        if (login.length() <= 25 && login.length() >= 5 && matchlog.matches()) {
            lblLogin.setVisible(false);

        } else {
            if (!bWarning) {
                warning += txtLogin.getPromptText();
                bWarning = true;
            } else {
                warning += ", " + txtLogin.getPromptText();
            }
            lblLogin.setVisible(true);

            bLogin = false;

            txtLogin.setText("");

        }
        //Comprobacion si el email cumple con los parametros
        // El Email deberá cumplir comprobación de ser email (patrón) y máximo 50 carácteres (no se admiten espacios)
        Matcher matcher = pattern.matcher(email);
        if (email.length() <= 50 && matcher.matches()) {

            lblEmail.setVisible(false);
        } else {
            if (!bWarning) {
                warning += txtEmail.getPromptText();
                bWarning = true;
            } else {
                warning += ", " + txtEmail.getPromptText();
            }

            lblEmail.setVisible(true);
            bEmail = false;

            txtEmail.setText("");

        }
        //Comprobacion si la contraseña cumple los parametros
        //La contraseña deberá ser de mínimo 6 carácteres, máximo 25, y DEBE
        //contener carácteres alfanuméricos (mayus, minus , numéricos y no se admiten espacios)
        //y mínimo un carácter especial (*, %, $, etc...)

        Matcher matchpass = pass.matcher(password);
        if (password.length() >= 6 && password.length() <= 25 && matchpass.matches()) {
            lblPassword.setVisible(false);
        } else {

            if (!bWarning) {
                warning += txtPassword.getPromptText();
                bWarning = true;
            } else {
                warning += ", " + txtPassword.getPromptText();
            }

            lblPassword.setVisible(true);
            bPassword = false;

            txtPassword.setText("");

        }
        //Si contraseña erronea se fuerza error en confirmar contraseña
        if (password.equalsIgnoreCase(cpassword) && bPassword) {
            lblCPassword.setVisible(false);
        } else {
            if (!bWarning) {
                warning += txtConfirmPassword.getPromptText();
                bWarning = true;
            } else {
                warning += ", " + txtConfirmPassword.getPromptText();
            }
            lblCPassword.setVisible(true);
            bCPassword = false;

            txtConfirmPassword.setText("");
        }
        //Secuencia para dar foco al primer campo erroneo
        if (!bName) {
            txtName.requestFocus();
        } else if (!bLogin) {
            txtLogin.requestFocus();
        } else if (!bEmail) {
            txtEmail.requestFocus();
        } else if (!bPassword) {
            txtPassword.requestFocus();
        } else if (!bCPassword) {
            txtConfirmPassword.requestFocus();
        } else {
            signUp();
        }
        if (bWarning) {
            warning();
        }
    }

    /**
     * Controla cuando abilitamos el boton SignUp mediante Boolean
     */
    public void buttonActivation() {
        if (bName && bLogin && bEmail && bPassword && bCPassword) {
            btSignUp.setDisable(false);
            bWarning = false;
        } else {
            btSignUp.setDisable(true);

        }

    }

    /**
     * Metodo para registrar al usuario en la base de datos si todos los datos
     * estan correctos.
     */
    public void signUp() {
        LOGGER.info("Sign In procedure initiated");
        User user = new User();
        user.setFullName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        Signable sig = ViewSignableFactory.getView();
        User usr = null;
        try {
            usr = sig.signUp(user);

            Alert altInfoSignUp = new Alert(AlertType.INFORMATION);
            altInfoSignUp.setTitle("SignUp Completado");
            altInfoSignUp.setHeaderText(null);
            altInfoSignUp.setContentText("Registro Exitoso, redireccionando a ventana de SignIn");
            altInfoSignUp.showAndWait();

        } catch (ClientServerConnectionException e) {
            Alert altErrorSC = new Alert(AlertType.ERROR);
            altErrorSC.setTitle("System Error");
            altErrorSC.setHeaderText("Could not Connect to the Server");
            altErrorSC.setContentText("The application could not connect to the server"
                    + "\n The Server may be busy with too many incoming requests, "
                    + "try again later, if this error continues, contact support or check server availability");
            altErrorSC.showAndWait();
        } catch (DBConnectionException e) {
            Alert altErrorDB = new Alert(AlertType.ERROR);
            altErrorDB.setTitle("System Error");
            altErrorDB.setHeaderText("Could not Connect to the DataBase");
            altErrorDB.setContentText("The server could not register your info, try again later. "
                    + "If this error persists, contact support or check the DataBase availability");
            altErrorDB.showAndWait();

        } catch (LoginOnUseException e) {
            Alert altWarningLog = new Alert(AlertType.WARNING);
            altWarningLog.setTitle("Login on use");
            altWarningLog.setHeaderText("Login already on use.");
            altWarningLog.setContentText("The text value input for the login field is allready on use on the DataBase."
                    + "You may allready be registered on the database. If not another user is using that login, you"
                    + "will have to use another one.");
            altWarningLog.showAndWait();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reto1client/view/VSignIn.fxml"));
        try {
            Parent root = (Parent) loader.load();
            VSignInController controller = ((VSignInController) loader.getController());
            //controller.setStage(this.stage);
            // controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(VSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para asignarle el stage a la ventana
     *
     * @param primaryStage Espacio donde se va a mostrar la ventana
     */
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    /**
     * Metodo para lanzar una alerta de Warning
     */
    private void warning() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Campos introducidos no validos");
        alert.setContentText(warning);

        alert.showAndWait();
    }

    /**
     * Method to create a confirmation popup when user uses the UI's innate
     * close button (button X)
     *
     * @param event the event linked to clicking on the button;
     */
    public void closeVSignUpX(WindowEvent event) {
        LOGGER.info("Requesting confirmation for application closing...");
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Está Cerrando el Programa");
        alert.setHeaderText("¿Seguro que desea cerrar el programa?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            LOGGER.info("Closing the application");
            Platform.exit();
        }
    }

    /**
     * Method of the LogOut Hyperlink (hlLogOut) that nds thecurrent users
     * session on the application and returns to the SignIn window
     *
     * @param event the event linked to clicking on the button;
     */
    public void backSingIn(ActionEvent event) {
        LOGGER.info("Volviendo a ventana SignIn");
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Volver a SignIn");
        alert.setHeaderText("¿Seguro que desea volver a la ventana de SignIn?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            LOGGER.info("Cerrada ventana SignUp y volviendo a SignIn");
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reto1client/view/VSignIn.fxml"));
            try {
                Parent root = (Parent) loader.load();
                VSignInController controller = loader.getController();
                controller.setStage(this.stage);
                controller.initStage(root);
            } catch (IOException ex) {
                Logger.getLogger(VSignUpController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

        }
    }

}
