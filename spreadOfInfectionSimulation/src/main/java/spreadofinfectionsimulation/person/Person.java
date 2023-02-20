package spreadofinfectionsimulation.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spreadofinfectionsimulation.state.IState;
import spreadofinfectionsimulation.vector.Vector2D;
import spreadofinfectionsimulation.state.*;

import java.util.Random;

@Setter
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class Person {
    private IState state;
    private Vector2D vector;
    private int id;
    private double speed;
    private double angleX, angleY;

    private final Random random = new Random();

    public Person getPerson() {
        return Person.of(state, vector, id, speed, angleX, angleY);
    }

    public Person(double x, double y, int id, double chanceOfInfection) {
        this.vector = new Vector2D(x, y);
        this.speed = initNewSpeed();
        this.angleX = random.nextDouble() * 360.;
        this.id = id;
        this.state = random.nextDouble() > chanceOfInfection ? (IState) new SensitiveHealthyPerson() : random.nextBoolean() ? (IState) new InfectedWithoutSymptompsPerson() : (IState) new InfectedWithSymptompsPerson();
    }

    public void setState(IState state) {
        this.state = state;
    }

    public double[] movePerson(int width, int height) {
        double[] cords = this.vector.getComponents();
        this.drawChancesOfChangingDirection();
        this.drawChancesOfChangingSpeed();

        if (cords[0] >= width || cords[0] <= 0.) {
            this.angleX *= -1.;
            if (random.nextBoolean()) this.resetPerson(width, height);
        }
        this.vector.setX(cords[0] + 2. * this.speed * Math.sin(Math.toRadians(this.angleX)));

        if (cords[1] >= height || cords[1] <= 0.) {
            this.angleY *= -1.;
            if (random.nextBoolean()) this.resetPerson(width, height);
        }
        this.vector.setY(cords[1] + 2. * this.speed * Math.sin(Math.toRadians(this.angleY)));

        return this.vector.getComponents();
    }

    private void drawChancesOfChangingDirection() {
        double chances = this.random.nextDouble();
        if (chances > 0.99) {
            this.angleX = this.random.nextDouble() * 360.;
            if (this.angleX >= 0. && this.angleX <= 90.) this.angleY = 90. - this.angleX;
            else if (this.angleX > 90. && this.angleX <= 180.) this.angleY = -90. + this.angleX;
            else if (this.angleX > 180. && this.angleX <= 270.) this.angleY = 180. - this.angleX;
            else this.angleY = 270. - this.angleX;
        }
    }

    private void drawChancesOfChangingSpeed() {
        double chances = this.random.nextDouble();
        if (chances > 0.98) this.speed = this.initNewSpeed();
    }

    private double initNewSpeed() {
        return this.random.nextDouble() * 0.2 + 0.05;
    }

    private void resetPerson(double width, double height) {
        this.state = random.nextDouble() > 0.1 ? (IState) new SensitiveHealthyPerson() : random.nextBoolean() ? (IState) new InfectedWithoutSymptompsPerson() : (IState) new InfectedWithSymptompsPerson();

        double rand = random.nextDouble();
        if (rand < 0.25) {
            this.vector.setX(0);
            this.vector.setY(random.nextDouble() * height);
            this.angleX = random.nextDouble() * 170. + 275.;
        } else if (rand < 0.5) {
            this.vector.setX(width);
            this.vector.setY(random.nextDouble() * height);
            this.angleX = random.nextDouble() * 170. + 95.;
        } else if (rand < 0.75) {
            this.vector.setX(random.nextDouble() * width);
            this.vector.setY(0.);
            this.angleX = random.nextDouble() * 170. + 185.;
        } else {
            this.vector.setX(random.nextDouble() * width);
            this.vector.setY(height);
            this.angleX = random.nextDouble() * 170. + 5.;
        }
    }

    public void handle(Person person, double distance) {
        this.state.handle(this, person, distance);
    }
}
