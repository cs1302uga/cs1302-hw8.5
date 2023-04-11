package cs1302.reader;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * A JavaFX application that downloads a text file from a user specified URL and displays
 * the contents in a graphical window. This version does not create any additional threads
 * and all event handlers run on the JavaFX Application Thread. Students are responsible
 * for modifying the code to ensure the app remains responsive while text downloads.
 */
public class ReaderApp extends Application {

    private static String DELAY_URL = "https://app.requestly.io/delay/5000/";

    public static final String SHERLOCK =
        "https://www.gutenberg.org/files/1661/1661-0.txt";

    Stage stage;
    Scene scene;

    VBox root;

    HBox urlPane;
    TextField urlField;
    Button loadButton;

    ScrollPane textPane;
    TextFlow textFlow;

    /**
     * Initializes the GUI components for the reader app.
     */
    public ReaderApp() {
        this.stage = null;
        this.scene = null;
        // scene nodes
        this.root = new VBox();
        this.urlPane = new HBox();
        this.textPane = new ScrollPane();
        this.urlField = new TextField(SHERLOCK);
        this.loadButton = new Button("Load");
        this.textFlow = new TextFlow();
    } // ReaderApp

    @Override
    public void init() {
        // initialize url pane
        HBox.setHgrow(this.urlField, Priority.ALWAYS);
        this.urlPane.getChildren().addAll(this.urlField, this.loadButton);
        // initialize the text flow
        this.textFlow.getChildren().add(new Text("Click \"Load\" to load the .txt file..."));
        this.textFlow.setMaxWidth(630);
        this.textPane.setPrefHeight(480);
        this.textPane.setContent(this.textFlow);
        // initialize the root node
        this.root.getChildren().addAll(this.urlPane, this.textPane);
        this.loadButton.setOnAction(event -> this.loadPage());
    } // init

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(root, 640, 480);
        this.stage.setScene(this.scene);
        this.stage.sizeToScene();
        this.stage.setTitle("ExampleApp!!");
        this.stage.show();
    } // start

    /**
     * Students will add an appropriate Javadoc comment for this method.
     */
    private void loadPage() {
        this.loadButton.setDisable(true);
        this.textFlow.getChildren().clear();
        try {
            URL url = new URL(DELAY_URL + this.urlField.getText());
            Scanner site = new Scanner(url.openStream());
            while (site.hasNextLine()) {
                String line = site.nextLine();
                Text lineText = new Text(line + "\n");
                this.textFlow.getChildren().add(lineText);
            } // while
        } catch (IOException ex) {
            Text errorText = new Text(ex.getMessage());
            this.textFlow.getChildren().add(errorText);
        } // try
        this.loadButton.setDisable(false);
    } // loadPage

} // ReaderApp
