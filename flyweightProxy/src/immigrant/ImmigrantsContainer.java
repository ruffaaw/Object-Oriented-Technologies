package immigrant;

import flyweight.IFlyweight;
import immigrant.coordinates.Coordinates;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ImmigrantsContainer {
    private static ImmigrantsContainer immigrantsContainer;
    @Getter
    private List<Immigrant> immigrantList;

    public ImmigrantsContainer() {
        immigrantList = new ArrayList<>();
    }

    public static ImmigrantsContainer getImmigrantsContainer() {
        if (immigrantsContainer == null) immigrantsContainer = new ImmigrantsContainer();
        return immigrantsContainer;
    }

    public void addToContainer(Coordinates coordinates, IFlyweight nameFlyweight, ArrayList<IFlyweight> surnameFlyweight) {
        immigrantList.add(new Immigrant(coordinates, surnameFlyweight, nameFlyweight));
    }
}
