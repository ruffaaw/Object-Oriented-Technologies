package immigrant;

import flyweight.IFlyweight;
import immigrant.coordinates.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;

public class Immigrant {
    private final Coordinates coordinates;
    private final ArrayList<IFlyweight> surname;
    private final IFlyweight name;

    public Immigrant(Coordinates coordinates, ArrayList<IFlyweight> surnameFlyweight, IFlyweight nameFlyweight) {
        this.coordinates = coordinates;
        this.surname = surnameFlyweight;
        this.name = nameFlyweight;
    }

    @Override
    public String toString() {
        return "Surname: " + surnameToString() + "\n" + "Name: " + name.getData() + "\n" + "Coordinates: " + Arrays.toString(coordinates.getCords()) + "\n";
    }

    public String surnameToString() {
        String fullSurname = "";
        for (IFlyweight flyweight : surname)
            fullSurname += flyweight.getData() + " ";

        return fullSurname;
    }
}
