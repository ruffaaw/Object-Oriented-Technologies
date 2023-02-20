package flyweight;

import java.util.ArrayList;

public class SurnameBuilder {

    private final ArrayList<IFlyweight> fullSurname;

    public SurnameBuilder() {
        this.fullSurname = new ArrayList<>();
    }

    public void addSurnamePart(IFlyweight part) {
        fullSurname.add(0, part);
    }

    public ArrayList<IFlyweight> getData() {
        return this.fullSurname;
    }

}
