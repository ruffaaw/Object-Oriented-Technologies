package spreadofinfectionsimulation.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewManager {
    private static int height = 300;
    private static int width = 300;
    private AnchorPane mainPane;
    private Stage mainStage;

    public ViewManager() {
        mainPane = new AnchorPane();
        Scene mainScene = new Scene(mainPane, width, height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        this.setBackground();
        this.createStartButton();

    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void setBackground() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        mainPane.setBackground(new Background(backgroundFill));
    }

    private void createStartButton() {
        Button button = new Button("Start Simulation");
        button.setLayoutX(width / 2. - 50.);
        button.setLayoutY(100.);
        button.setOnAction(e -> {
            SimulationViewManager manager = new SimulationViewManager(500, 1020, 720, 0.);
            manager.createNewSimulation(mainStage);
        });
        mainPane.getChildren().addAll(button);
    }
}
