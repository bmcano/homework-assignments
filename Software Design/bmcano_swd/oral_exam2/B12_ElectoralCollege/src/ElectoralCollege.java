import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Brandon Cano
 */
public class ElectoralCollege extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL fxml = getClass().getResource("ElectoralCollege.fxml");
        if (fxml == null) {
            System.out.println("Something went wrong.");
            return;
        }
        Parent root = FXMLLoader.load(fxml);

        Scene scene = new Scene(root);
        stage.setTitle("Electoral College");
        stage.setScene(scene);
        stage.show();
    }
}
