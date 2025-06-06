package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Sample.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setTitle("Software de Préstamo");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icono.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
