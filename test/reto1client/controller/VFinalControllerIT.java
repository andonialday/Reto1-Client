/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1client.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import reto1client.application.NewMain;

/**
 *
 * @author Andoni Alday
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VFinalControllerIT extends ApplicationTest {

    private Parent root;

    public VFinalControllerIT() throws IOException {
        root = new FXMLLoader(getClass().getResource("/reto1client/view/VFinal.fxml")).load();
    }

    /**
     * Start method to setupa window for each test
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        //new ClientApplication().start(root);
        //
    }

    /**
     * BeforeClass method to setup a unique window for the tests
     *
     * @throws TimeoutException
     */
    @BeforeClass
    @Ignore
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(NewMain.class);
    }

    /**
     * Test designed to verify the correct initialization of the window
     */
    @Test
    public void test0_initialState() {
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
    public void test1_btnCloseNO() {
        clickOn("#btnClose");
        clickOn("Cancelar");
        verifyThat("#pFinal", isVisible());
    }

    /**
     * Test designed to verify the btnClose works as intended: Triggers an
     * AlerType in which clicking on "Aceptar" triggers the program ending
     * sequence
     */
    @Test
    @Ignore
    public void test3_btnCloseOK() {
        clickOn("#btnClose");
        clickOn("Aceptar");
        verifyThat("#pFinal", isInvisible());
    }

    /**
     * Test designed to verify the hlSignOut works as intended: Triggers an
     * AlerType in which clicking on "Cancelar" aborts the program ending
     * sequence
     */
    @Test
    public void test2_hlSignOutNO() {
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
    @Ignore
    public void test4_hlSignOutOK() {
        clickOn("#hlLogOut");
        clickOn("Aceptar");
        verifyThat("#pFinal", isInvisible());
    }

}
