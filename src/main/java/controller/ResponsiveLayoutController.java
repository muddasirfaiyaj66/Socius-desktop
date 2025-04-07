package controller;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResponsiveLayoutController {

    @FXML private BorderPane mainBorderPane;
    @FXML private VBox leftSidebar;
    @FXML private VBox rightSidebar;
    @FXML private ScrollPane leftSidebarScroll;
    @FXML private ScrollPane rightSidebarScroll;
    @FXML private VBox contentVBox;
    @FXML private ImageView postImage;
    @FXML private HBox postActionsBox;
    @FXML private ImageView logoImage;

    private boolean isSmallScreen = false;
    private boolean isMediumScreen = false;

    @FXML
    public void initialize() {
        // Add listeners to handle responsive layout
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> updateLayout();

        // Add listeners to the scene width and height
        mainBorderPane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.widthProperty().addListener(stageSizeListener);
                newScene.heightProperty().addListener(stageSizeListener);

                // Initial update
                updateLayout();
            }
        });

        // Make post image responsive
        if (postImage != null && contentVBox != null) {
            postImage.fitWidthProperty().bind(contentVBox.widthProperty().subtract(30));
        }
    }

    private void updateLayout() {
        if (mainBorderPane.getScene() == null) return;

        double width = mainBorderPane.getScene().getWidth();

        // Small screen (mobile)
        if (width < 768) {
            if (!isSmallScreen) {
                mainBorderPane.setLeft(null);
                mainBorderPane.setRight(null);

                if (postActionsBox != null) {
                    postActionsBox.setSpacing(5);
                }

                isSmallScreen = true;
                isMediumScreen = false;
            }
        }
        // Medium screen (tablet)
        else if (width < 1024) {
            if (!isMediumScreen) {
                mainBorderPane.setLeft(leftSidebarScroll);
                mainBorderPane.setRight(null);

                if (leftSidebar != null) {
                    leftSidebar.setPrefWidth(200);
                }

                if (postActionsBox != null) {
                    postActionsBox.setSpacing(10);
                }

                isSmallScreen = false;
                isMediumScreen = true;
            }
        }
        // Large screen (desktop)
        else {
            if (isSmallScreen || isMediumScreen) {
                mainBorderPane.setLeft(leftSidebarScroll);
                mainBorderPane.setRight(rightSidebarScroll);

                if (leftSidebar != null) {
                    leftSidebar.setPrefWidth(250);
                }

                if (rightSidebar != null) {
                    rightSidebar.setPrefWidth(250);
                }

                if (postActionsBox != null) {
                    postActionsBox.setSpacing(20);
                }

                isSmallScreen = false;
                isMediumScreen = false;
            }
        }
    }
}
