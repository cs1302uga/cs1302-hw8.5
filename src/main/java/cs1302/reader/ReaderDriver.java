package cs1302.reader;

import javafx.application.Application;

/**
 * Driver for image application.
 */
public class ReaderDriver {

    public static void main(String[] args) {
        try {
            Application.launch(ReaderApp.class, args);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            System.err.println("Likely due to X11 timeout. Logout and log back in...");
            System.exit(1);
        } // try
    } // main

} // ReaderDriver
