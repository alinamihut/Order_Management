package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
/**
 * @author Alina
 * Main class
 **/
    public class Main extends Application {
    /**
     *
     * @param primaryStage
     * @throws IOException
     * contains the methods which initialize the primary stage
     */
    @Override
        public void start(Stage primaryStage) throws IOException {

            URL url= new File("src/main/java/sample.fxml").toURI().toURL();
            Parent root= FXMLLoader.load(url);
            primaryStage.setTitle("Orders management system");
            primaryStage.setScene(new Scene(root, 650, 400));
            primaryStage.show();
        }

    /**
     *
     * @param args
     * lauches the application
     */
        public static void main(String[] args) {
            launch();
        }
    }

