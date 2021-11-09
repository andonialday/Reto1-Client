/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1client.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import reto1libraries.exception.ClientServerConnectionException;
import reto1libraries.exception.CredentialErrorException;
import reto1libraries.exception.DBConnectionException;
import reto1libraries.exception.LoginOnUseException;
import reto1libraries.logic.*;
import reto1libraries.object.*;

/**
 * Signable Implementation Class for the View Layer
 * @author Enaitz Izagirre
 */
public class ViewSignableImplementation implements Signable {

    //Cambiar ip cuando la sepamos
    private static final String SERVER = "127.0.0.1";
    private static final Integer PORT = 5000;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private static final Logger LOGGER = Logger.getLogger("package.class");

    /**
     * Method to Sign In the Client in to the Data Base
     * @param usr Asks a user to encapsulate in order to send it to the server
     * @return Returns a null or a complete user depending if it fails
     * @throws CredentialErrorException If the user is not correct
     * @throws DBConnectionException Error processing feedback from the Database
     * @throws ClientServerConnectionException If the Client cant Connect With
     * the server cause of the Server error
     */
    @Override
    public User signIn(User usr) throws CredentialErrorException, DBConnectionException, ClientServerConnectionException {

        Encapsulation enc = new Encapsulation();

        enc.setUser(usr);
        enc.setStatus(Status.PENDING);
        enc.setMethod(Method.SIGNIN);
        LOGGER.info("Sign In procedure initiated");
        Socket client;

        try {
            client = new Socket(SERVER, PORT);

            oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(enc);

            ois = new ObjectInputStream(client.getInputStream());
            enc = (Encapsulation) ois.readObject();

            if (null == enc.getStatus()) {
                usr = enc.getUser();
            } else {
                switch (enc.getStatus()) {
                    case FAIL:
                        throw new CredentialErrorException("Credential Error");
                    case ERROR:
                        throw new DBConnectionException("");
                    default:
                        usr = enc.getUser();
                        break;
                }
            }

        } catch (IOException ex) {
            throw new ClientServerConnectionException("Failed to connect to server");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewSignableImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usr;
    }

    /**
     * Method to Sign Up the Client in to the Data Base
     *
     * @param usr Asks a user to encapsulate in order to send it to the server
     * @return Returns a null or a complete user depending if it fails
     * @throws LoginOnUseException If the User Parameters are rown
     * @throws ClientServerConnectionException If the Client cant Connect With
     * the server cause of the Server error
     */
    @Override
    public User signUp(User usr) throws LoginOnUseException, ClientServerConnectionException {

        Encapsulation enc = new Encapsulation();

        enc.setUser(usr);
        enc.setStatus(Status.PENDING);
        enc.setMethod(Method.SIGNUP);

        LOGGER.info("Sign Up procedure initiated");
        Socket client;

        try {
            client = new Socket(SERVER, PORT);

            oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(enc);

            ois = new ObjectInputStream(client.getInputStream());
            enc = (Encapsulation) ois.readObject();

            usr = enc.getUser();

            if (enc.getStatus() == Status.FAIL) {
                throw new LoginOnUseException("");
            }

        } catch (IOException ex) {
            throw new ClientServerConnectionException("Failed to connect to server");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewSignableImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usr;
    }

}
