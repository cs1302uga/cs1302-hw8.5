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

public class ReaderApp extends Application {

    private static String DELAY_URL = "https://app.requestly.io/delay/5000/";

    public static final String SHERLOCK =
        "https://www.gutenberg.org/files/1661/1661-0.txt";

    protected Stage stage;
    protected VBox mainPane;
    protected HBox urlPane;
    protected ScrollPane textPane;

    protected TextField urlField;
    protected Button urlButton;
    protected TextFlow textFlow;

    public ReaderApp() {
        mainPane = new VBox();
        urlPane = new HBox();
        textPane = new ScrollPane();

        urlField = new TextField(SHERLOCK);
        urlButton = new Button("Load");
        textFlow = new TextFlow();
    } // ReaderApp

    @Override
    public void init() {
        HBox.setHgrow(urlField, Priority.ALWAYS);
        urlButton.setOnAction(e -> loadPage());
        urlPane.getChildren().addAll(urlField, urlButton);

        textFlow.getChildren().add(new Text("Click \"Load\" to load the .txt file..."));
        textFlow.setMaxWidth(630);
        textPane.setPrefHeight(480);
        textPane.setContent(textFlow);

        mainPane.getChildren().addAll(urlPane, textPane);
    } // init

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        stage.setScene(new Scene(mainPane, 640, 480));
        stage.sizeToScene();
        stage.setTitle("ExampleApp!");
        stage.show();
    } // start

    private void loadPage() {
        urlButton.setDisable(true);
        textFlow.getChildren().clear();
        try {
            URL url = new URL(DELAY_URL + urlField.getText());
            Scanner site = new Scanner(url.openStream());
            while (site.hasNextLine()) {
                String line = site.nextLine();
                textFlow.getChildren().add(new Text(line + "\n"));
            } // while
        } catch (IOException ex) {
            textFlow.getChildren().add(new Text(ex.getMessage()));
        } // try
        urlButton.setDisable(false);
    } // loadPage

} // ReaderApp
