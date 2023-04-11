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
            if (e.getMessage().contains("DISPLAY") && System.getenv("SSH_TTY") != null) {
                System.err.println("------------------------------------------");
                System.err.println("X11 not available or timed out.");
                System.err.println("Logout and log back in using 'ssh -XYC'...");
                System.err.println("------------------------------------------");
            } // if
            System.exit(1);
        } // try
    } // main

} // ReaderDriver
