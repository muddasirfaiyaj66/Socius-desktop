import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main view (HomeView.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./view/RegisterView.fxml"));
        Parent root = loader.load();

       
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // Set up the scene
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("./styles/style.css")).toExternalForm());

        // Configure the stage
        primaryStage.setTitle("Socius");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("./assets/logo/logo.png"))));
        primaryStage.setScene(scene);

        // Enable full screen mode
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreenExitHint("Press ESC to exit full screen");
       

        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        // Handle cleanup if needed
        // DBConnection.close();
    }
}
