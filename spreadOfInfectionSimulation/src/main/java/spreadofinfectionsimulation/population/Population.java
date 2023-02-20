package spreadofinfectionsimulation.population;

import javafx.scene.paint.Color;
import spreadofinfectionsimulation.person.Person;
import spreadofinfectionsimulation.state.ResistantPerson;
import spreadofinfectionsimulation.state.SensitiveHealthyPerson;
import spreadofinfectionsimulation.view.SimulationViewManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Population {
    public static ArrayList<Person> populationList = new ArrayList<>();
    private final int numberOfPopulation;
    public static int counter;

    private int objectRadius = 15;
    private double chancesOfInfection;

    private Random random = new Random();

    private final int width;
    private final int height;

    public Population(int width, int height, double chancesOfInfection, int numberOfPopulation) {
        this.width = width;
        this.height = height;
        this.chancesOfInfection = chancesOfInfection;
        this.numberOfPopulation = numberOfPopulation;
    }


    public void createPopulation() {
        for (int i = 0; i < this.numberOfPopulation; i++) {
            double[] cords = this.getCords();
            populationList.add(new Person(cords[0], cords[1], counter++, chancesOfInfection));
            SimulationViewManager.graphicsContext.setFill(Color.GREEN);
            SimulationViewManager.graphicsContext.fillOval(cords[0], cords[1], this.objectRadius, this.objectRadius);
        }
    }

    private double[] getCords() {
        double[] cords = new double[2];
        cords[0] = random.nextInt(width);
        cords[1] = random.nextInt(height);
        return cords;

    }

    public void movePeople() {
        SimulationViewManager.graphicsContext.setFill(Color.BLACK);
        SimulationViewManager.graphicsContext.fillRect(0., 0., width, height);

        for (Person person : populationList) {
            double[] newCords = person.movePerson(width, height);
            Color color = this.getColor(person);
            SimulationViewManager.graphicsContext.setFill(color);
            SimulationViewManager.graphicsContext.fillOval(newCords[0], newCords[1], this.objectRadius, this.objectRadius);
        }
    }

    private Color getColor(Person person) {
        return person.getState().equals(new SensitiveHealthyPerson()) ? Color.GREEN : person.getState().equals(new ResistantPerson()) ? Color.BLUE : Color.RED;
    }

    public void checkIfPeopleAreCloseToEachOther() {
        for (int i = 0; i < populationList.size(); ++i) {
            for (int j = 0; j < populationList.size(); ++j) {
                if (i != j) {
                    double distance = 0.;
                    if (populationList.get(i).getState().equals(new SensitiveHealthyPerson())) {
                        distance = calculateDistance(populationList.get(i).getVector().getComponents()[0], populationList.get(j).getVector().getComponents()[0], populationList.get(i).getVector().getComponents()[1], populationList.get(j).getVector().getComponents()[1]);
                    }
                    populationList.get(i).handle(populationList.get(j), distance);
                }
            }
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public ArrayList<Person> copyPopulation() {
        return (ArrayList<Person>) populationList.stream().map(Person::getPerson).collect(Collectors.toList());
    }

}
