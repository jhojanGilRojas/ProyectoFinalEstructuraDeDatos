import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/principal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1360, 760);
        stage.setTitle("App");
        stage.setScene(scene);

        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
