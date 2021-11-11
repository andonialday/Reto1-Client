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
 * Test on the Sign In window
 * 
 * @author Jaime San Sebastián
 */

//Sort the tests by method order.
@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class VSignInControllerIT extends ApplicationTest{

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
     * Test of initial stage of login view.
     * SignIn button disabled, Exit button and hyperlink SignUp enabled.
     */
    @Test
    public void test01_InitialState() {
        verifyThat ("#btnSignIn", isDisabled());
        verifyThat ("#btnExit", isEnabled());
        verifyThat ("#hyperSignUp", isEnabled());
    }

    /**
     * Test that the SignIn button is disabled if the username or password fields are empty
     * and enabled if username and password are complete.
     * @throws java.lang.InterruptedException
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
     * Test that if the username and password are not correct, 
     * do not enter the welcome window and an alert panel appears.
     * @throws java.lang.InterruptedException
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
     * We press the Exit button and a confirmation panel will appear.
     * If we press the Cancel button we return to the previous window.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void test04_ExitButton() throws InterruptedException {
        clickOn("#btnExit");
        Thread.sleep(1500);
        clickOn("Cancelar");
    }
    
    /**
     * Test that the Sign Up view is open when the SignUp hyperlink is pressed.
     * If we press the Back button in the Sign Up window, 
     * we return to the Sing In window with the username and password fields empty.
     * @throws java.lang.InterruptedException
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
     * Test that if the username and password are correct, enter the Welcome window.
     * If we click the Close Session hyperlink button in the Final window, 
     * we return to the Sign In window with empty fields.
     * @throws java.lang.InterruptedException
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