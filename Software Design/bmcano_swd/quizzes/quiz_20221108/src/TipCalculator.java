import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class TipCalculator extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TipCalculator.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
