/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1client.controller;

import reto1client.application.ClientApplication;
import static org.testfx.matcher.base.NodeMatchers.*;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author Jaime San Sebastián
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class VSignInControllerTest extends ApplicationTest{

    private TextField txtLogin;
    private TextField txtPassword;
    /**
     * Set up Java FX fixture for test
     * @throws TimeoutException 
     */
    @BeforeClass
    public static void setInClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ClientApplication.class);
    }

    /**
     * Test of initial stage of login view
     */
    @Test
    public void test01_InitialState() {
        verifyThat ("#btnSignIn", isDisabled());
        verifyThat ("#btnExit", isEnabled());
        verifyThat ("#hyperSignUp", isEnabled());
    }

    /**
     * Test that the SignIn button is disabled if the username or password fields are empty
     * and the SignIn button is enabled if the username and password are full
     */
    @Test
    public void test02_SignInEnabledOrDisabled() throws InterruptedException {
        clickOn("#txtLogin");
        write("username");
        Thread.sleep(1500);
        verifyThat("#btnSignIn", isDisabled());
        txtLogin = lookup("#txtLogin").query();
        eraseText(txtLogin.getLength());
        verifyThat("#btnSignIn", isDisabled());
        
        clickOn("#txtPassword");
        write("password");
        Thread.sleep(1500);
        verifyThat("#btnSignIn", isDisabled());
        txtPassword = lookup("#txtPassword").query();
        eraseText(txtPassword.getLength());
        verifyThat("#btnSignIn", isDisabled()); 
    }
    
    /**
     * Test that if the username and password are incorrect, it does not enter the Welcome window
     */
    @Test
    public void test03_SignInIncorrect() throws InterruptedException {
        clickOn("#txtLogin");
        write("username");
        clickOn("#txtPassword");
        write("password");
        verifyThat("#btnSignIn", isEnabled());
        clickOn("#btnSignIn");
        Thread.sleep(1500);
        clickOn("Aceptar");
    }
    
     /**
     * Test that the application does not close if the exit and cancel button is pressed
     * 
     */
    @Test
    public void test04_ExitButton() throws InterruptedException {
        clickOn("#btnExit");
        Thread.sleep(1500);
        clickOn("Cancelar");
    }
    
    /**
     * Test that the sign up view is open when the signup hyperlink is pressed
     */
    @Test
    public void test05_SignUpButton() throws InterruptedException {
        clickOn("#hyperSignUp");
        verifyThat("#pSignUp", isVisible());
        Thread.sleep(1500);
        clickOn("#btBack");
        Thread.sleep(1500);
        clickOn("Aceptar");
        verifyThat("#pSignIn", isVisible());
    }
    
    /**
     * Test that if the username and password are correct, enter the Welcome window
     */
    @Test
    public void test06_SignInCorrect() throws InterruptedException {
        clickOn("#txtLogin");
        write("jaime");
        Thread.sleep(1500);
        clickOn("#txtPassword");
        write("Abcd*1234");
        verifyThat("#btnSignIn", isEnabled());
        Thread.sleep(1500);
        clickOn("#btnSignIn");
        verifyThat("#pFinal", isVisible());
        Thread.sleep(1500);
        clickOn("#hCerrarSesion");
        verifyThat("#pSignIn", isVisible());
    }
    
}