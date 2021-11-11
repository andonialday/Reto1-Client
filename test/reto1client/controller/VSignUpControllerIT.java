/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1client.controller;

import java.util.concurrent.TimeoutException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.BeforeClass;
import org.testfx.api.FxToolkit;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import reto1client.application.PruebasBorrar;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;

/**
 * Clase para testear la vetana de SignUp el test TestA_Init() no se puede
 * ignorar
 *
 * @author Aitor Perez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VSignUpControllerIT extends ApplicationTest {

    private TextField txtName;
    private TextField txtLogin;
    private TextField txtEmail;
    private PasswordField txtPassword;
    private PasswordField txtConfirmPassword;
    private Hyperlink hyperSignUp;

    /**
     * Inicia el main del cliente para poder ejecutar los teses.
     *
     * @throws TimeoutException Excepcion en caso de que java no responda en un
     * tiempo determinado
     */
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ClientApplication.class);
    }

    /**
     * Test Principal para abrir la ventana de SignUp
     */
    @Test
    public void TestA_Init() {
        clickOn("#hyperSignUp");
        verifyThat("#txtName", isEnabled());
        verifyThat("#txtLogin", isEnabled());
        verifyThat("#txtEmail", isEnabled());
        verifyThat("#txtPassword", isEnabled());
        verifyThat("#txtConfirmPassword", isEnabled());
        verifyThat("#btBack", isEnabled());
        verifyThat("#btSignUp", isDisabled());
    }

    /**
     * Comprueba al escribir en el campo Name si el boton SignUp esta
     * desabilitado
     */
    @Test
    public void TestB_Name() {
        clickOn("#txtName");
        write("Paco piedra");
        verifyThat("#btSignUp", isDisabled());
    }

    /**
     * Comprueba si el boton SignUp esta desabilitado borrando el campo de texto
     * name anterior y rellenando el campo login
     */
    @Test
    public void TestC_Login() {
        clickOn("#txtName");
        txtName = lookup("#txtName").query();
        eraseText(txtName.getLength());
        clickOn("#txtLogin");
        write("PacoPicaPiedra777");
        verifyThat("#btSignUp", isDisabled());
    }

    /**
     * Comprueba si el boton SignUp esta desabilitado borrando el campo de texto
     * login anterior y rellenando el campo email
     */
    @Test
    public void TestD_Email() {
        clickOn("#txtLogin");
        txtLogin = lookup("#txtLogin").query();
        eraseText(txtLogin.getLength());
        doubleClickOn("#txtEmail");
        write("pacopicapiedra@picon.com");
        verifyThat("#btSignUp", isDisabled());
    }

    /**
     * Comprueba si el boton SignUp esta desabilitado borrando el campo de texto
     * email anterior y rellenando el campo Password
     */
    @Test
    public void TestE_Password() {
        doubleClickOn("#txtEmail");
        txtEmail = lookup("#txtEmail").query();
        eraseText(txtEmail.getLength());
        clickOn("#txtPassword");
        write("PacoPico*P13dr4");
        verifyThat("#btSignUp", isDisabled());
    }

    /**
     * Comprueba si el boton SignUp esta desabilitado borrando el campo de texto
     * email anterior y rellenando el campo CPassword
     */
    @Test
    public void TestF_CPassword() {
        clickOn("#txtPassword");
        txtPassword = lookup("#txtPassword").query();
        eraseText(txtPassword.getLength());
        clickOn("#txtConfirmPassword");
        write("PacoPico*P13dr4");
        verifyThat("#btSignUp", isDisabled());
    }

    /**
     * Comprueba si el boton SignUp esta habilitadp borrando el campo CPassword
     * y volviendo a rellenar todos los campos
     */
    @Test
    public void TestG_BtnSignUp() {
        clickOn("#txtConfirmPassword");
        txtConfirmPassword = lookup("#txtConfirmPassword").query();
        eraseText(txtConfirmPassword.getLength());
        clickOn("#txtName");
        write("Paco piedra");
        clickOn("#txtLogin");
        write("PacoPicaPiedra777");
        clickOn("#txtEmail");
        write("pacopicapiedra@picon.com");
        clickOn("#txtPassword");
        write("PacoPico*P13dr4");
        clickOn("#txtConfirmPassword");
        write("PacoPico*P13dr4");
        verifyThat("#btSignUp", isEnabled());
    }

    /**
     * Metodo usado por multiples test para vaciar campos
     */
    public void vaciarCampos() {
        doubleClickOn("#txtName");
        txtName = lookup("#txtName").query();
        eraseText(txtName.getLength());
        doubleClickOn("#txtLogin");
        txtLogin = lookup("#txtLogin").query();
        eraseText(txtLogin.getLength());
        doubleClickOn("#txtEmail");
        txtEmail = lookup("#txtEmail").query();
        eraseText(txtEmail.getLength());
        doubleClickOn("#txtPassword");
        txtPassword = lookup("#txtPassword").query();
        eraseText(txtPassword.getLength());
        doubleClickOn("#txtConfirmPassword");
        txtConfirmPassword = lookup("#txtConfirmPassword").query();
        eraseText(txtConfirmPassword.getLength());
    }

    /**
     * Comprueba si salta un alert warning que indica que campos no cumplen con
     * los parametros establecidos para el registro
     */
    @Test
    public void TestI_warningIncorrectParameters() {
        vaciarCampos();
        clickOn("#txtName");
        write("Paco piedra*");
        clickOn("#txtLogin");
        write("PacoPicaPiedra777");
        clickOn("#txtEmail");
        write("pacopicapiedra%picon.com");
        clickOn("#txtPassword");
        write("PacoPi co*P13dr4");
        clickOn("#txtConfirmPassword");
        write("PacoPico*P13dr4");
        clickOn("#btSignUp");
        clickOn("#btSignUp");
        verifyThat(".alert", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    /**
     * Comprueba si al no tener conexion con el servidor si se muestra una
     * Alerta de error de conexion con el servidor
     */
    @Test
    public void TestJ_ErrorConnectToTheServer() {
        vaciarCampos();
        clickOn("#txtName");
        write("Paco piedra");
        clickOn("#txtLogin");
        write("PacoPicaPiedra777");
        clickOn("#txtEmail");
        write("pacopicapiedra@picon.com");
        clickOn("#txtPassword");
        write("PacoPico*P13dr4");
        clickOn("#txtConfirmPassword");
        write("PacoPico*P13dr4");
        clickOn("#btSignUp");
        verifyThat(".alert", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    /**
     * Comprueba si al tener conexion con el servidor pero no teniendo la base
     * de datos si se muestra una Alerta de error de conexion con la base de
     * datos
     */
    @Test
    public void TestK_ErrorConnectToDB() {
        vaciarCampos();
        clickOn("#txtName");
        write("Paco piedra");
        clickOn("#txtLogin");
        write("PacoPicaPiedra777");
        clickOn("#txtEmail");
        write("pacopicapiedra@picon.com");
        clickOn("#txtPassword");
        write("PacoPico*P13dr4");
        clickOn("#txtConfirmPassword");
        write("PacoPico*P13dr4");
        clickOn("#btSignUp");
        verifyThat(".alert", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    /**
     * Comprueba si el usuario que estas registrando esta en uso
     */
    @Test
    public void TestL_WarningLoginOnUse() {
        vaciarCampos();
        clickOn("#txtName");
        write("Paco piedra");
        clickOn("#txtLogin");
        write("PacoPicaPiedra777");
        clickOn("#txtEmail");
        write("pacopicapiedra@picon.com");
        clickOn("#txtPassword");
        write("PacoPico*P13dr4");
        clickOn("#txtConfirmPassword");
        write("PacoPico*P13dr4");
        clickOn("#btSignUp");
        verifyThat(".alert", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    /**
     * Testea si el registro nuevo se hace correctamente
     */
    @Test
    public void TestM_InfoLogOk() {
        vaciarCampos();
        clickOn("#txtName");
        write("Prueba12");
        clickOn("#txtLogin");
        write("PedroPicaPiedra777");
        clickOn("#txtEmail");
        write("PedroPicaPiedra777@piedra.com");
        clickOn("#txtPassword");
        write("PacoPico*P13dr4");
        clickOn("#txtConfirmPassword");
        write("PacoPico*P13dr4");
        clickOn("#btSignUp");
        verifyThat(".alert", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

}
