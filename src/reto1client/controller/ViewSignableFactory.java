/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1client.controller;

import reto1client.view.ViewSignableImplementation;
import reto1libraries.logic.Signable;

/**
 * Class Factory to get View
 * @author Aitor Perez
 */
public class ViewSignableFactory {

    /**
     * Method to give a new View to the Controller
     * @return View to the Controller
     */
    public static Signable getView(){
        Signable view = new ViewSignableImplementation();
        return view;
    }

}