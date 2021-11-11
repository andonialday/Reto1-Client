/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1client.controller;

import java.awt.Button;
import java.awt.TextField;
import java.util.concurrent.TimeoutException;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import static org.testfx.api.FxAssert.*;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.*;
import reto1client.application.ClientApplication;

/**
 * Test class for the VFinalController, testing the functionality of the VFinal.
 *
 * Note: the test0_initVFinal should never be ignored, as it is the one
 * responsible of running the application from the launching point to the
 * windowtobe tested itself
 *
 * @author Andoni Alday
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VFinalControllerIT extends ApplicationTest {

    private TextField txtLogin, txtPassword;
    private Button btnSignIn;

    /**
     * BeforeClass method to setup a unique window for the tests
     *
     * @throws TimeoutException
     */
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ClientApplication.class);
    }

    /**
     * Test designed to launch VFinal from the ClientApplication Main through
     * VSignIn after a successfull SignIn.
     *
     * It will only work if the Server application is running, the connection
     * between CLientand Serverworks and the DB is onlyne with the
     * login-password credentials to be used on it. If this test fails, the
     * others will automatically do so, as the window will not load
     *
     */
    @Test
    public void test0_initVFinal() {
        clickOn("#txtLogin");
        write("pepepa");
        clickOn("#txtPassword");
        write("Pepepa*2021");
        clickOn("Sign In");
        verifyThat("#pFinal", isVisible());
    }

    /**
     * Test designed to verify the correct initialization of the window
     */
    @Test
    public void test1_initialState() {
        verifyThat("#btnClose", isEnabled());
        verifyThat("#hlLogOut", isEnabled());
        verifyThat("#lblMessage", isVisible());
    }

    /**
     * Test designed to verify the btnClose works as intended: Triggers an
     * AlerType in which clicking on "Cancelar" aborts the program ending
     * sequence
     */
    @Test
    public void test2_btnCloseNO() {
        clickOn("#btnClose");
        clickOn("Cancelar");
        verifyThat("#pFinal", isVisible());
    }

    /**
     * Test designed to verify the hlSignOut works as intended: Triggers an
     * AlerType in which clicking on "Cancelar" aborts the program ending
     * sequence
     */
    @Test
    public void test3_hlSignOutNO() {
        clickOn("#hlLogOut");
        clickOn("Cancelar");
        verifyThat("#pFinal", isVisible());
    }

    /**
     * Test designed to verify the hlSignOut works as intended: Triggers an
     * AlerType in which clicking on "Aceptar" triggers the program ending
     * sequence
     */
    @Test
    //@Ignore
    public void test4_hlSignOutOK() {
        clickOn("#hlLogOut");
        clickOn("Aceptar");
        verifyThat("#pSignIn", isVisible());
    }

    /**
     * Test designed to verify the btnClose works as intended: Triggers an
     * AlerType in which clicking on "Aceptar" triggers the program ending
     * sequence
     */
    @Test
    @Ignore
    public void test4_btnCloseOK() {
        clickOn("#btnClose");
        clickOn("Aceptar");
        verifyThat("#pFinal", isInvisible());
    }
}
