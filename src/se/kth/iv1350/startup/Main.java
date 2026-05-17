package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.RegistryCreator;
import se.kth.iv1350.view.View;

/**
 * Starts the application.
 */
public class Main {
    /**
     * The main method that starts the application.
     *
     * @param args The application does not take any command line arguments.
     */
    public static void main(String[] args) {
        RegistryCreator regCreator = RegistryCreator.getInstance();
        Controller contr = new Controller(regCreator);
        View view = new View(contr);
        view.runFakeExecution();
    }
}