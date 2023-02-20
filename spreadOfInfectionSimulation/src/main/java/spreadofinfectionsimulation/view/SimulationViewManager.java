package spreadofinfectionsimulation.view;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import spreadofinfectionsimulation.memento.History;
import spreadofinfectionsimulation.population.Population;

public class SimulationViewManager {
    private int height;
    private int width;
    private double chancesOfInfection;

    private AnchorPane simulationPane;
    private Stage simulationStage;

    private int numberOfPopulation;

    AnimationTimer simulationTimer;

    public static GraphicsContext graphicsContext;

    private History history;
    public static int keyCounter = 0;

    private Population population;

    public SimulationViewManager(int numberOfPopulation, int width, int height, double initialChancesOfInfection) {
        this.width = width;
        this.height = height;
        this.chancesOfInfection = initialChancesOfInfection;
        this.history = new History();
        this.numberOfPopulation = numberOfPopulation;
        population = new Population(this.width, this.height, chancesOfInfection, numberOfPopulation);

        initStage();
    }

    private void initStage() {
        initGraphicsContext();
        Scene simulationScene = new Scene(simulationPane, width, height);
        simulationStage = new Stage();
        simulationStage.setScene(simulationScene);
        addLoadAndSaveButtons();
        setBackground();
        initPopulation();
        createSimulationLoop();
    }

    private void initPopulation() {
        population = new Population(width, height, chancesOfInfection, numberOfPopulation);
        population.createPopulation();
    }

    private void initGraphicsContext() {
        Canvas can = new Canvas();
        can.setWidth(width);
        can.setHeight(height);
        graphicsContext = can.getGraphicsContext2D();
        simulationPane = new AnchorPane(can);
    }

    public void createNewSimulation(Stage stage) {
        stage.hide();
        simulationStage.show();
    }

    private void setBackground() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        simulationPane.setBackground(new Background(backgroundFill));
    }

    private void createSimulationLoop() {
        simulationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                population.checkIfPeopleAreCloseToEachOther();
                population.movePeople();
            }
        };
        simulationTimer.start();
    }

    private void addLoadAndSaveButtons() {
        Button saveButton = new Button("save");
        Button loadButton = new Button("load");
        saveButton.setLayoutY(10);
        loadButton.setLayoutY(10);
        saveButton.setLayoutX(820);
        loadButton.setLayoutX(880);

        saveButton.setOnAction(e -> history.addMemento(keyCounter++, population.copyPopulation()));
        loadButton.setOnAction(e -> {
            LoadPopulationViewManager manager = new LoadPopulationViewManager();
            manager.createLoadPopulationWindow(this.history);
        });

        simulationPane.getChildren().addAll(saveButton, loadButton);
    }
}
