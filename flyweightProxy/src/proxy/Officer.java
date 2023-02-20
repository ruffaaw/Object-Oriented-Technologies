package proxy;

import flyweight.FlyweightNameFactory;
import flyweight.FlyweightSurnameFactory;
import flyweight.SurnameBuilder;
import immigrant.ImmigrantsContainer;
import immigrant.coordinates.Coordinates;
import immigrant.coordinates.Util;

public class Officer implements IProxy {
    ImmigrantsContainer immigrantsContainer = ImmigrantsContainer.getImmigrantsContainer();

    @Override
    public void readData() {
        System.out.println("Enter name: ");
        String name = convertLine(Util.scanner.nextLine());
        FlyweightNameFactory.getNameFlyweight(name);

        SurnameBuilder surnameCreator = new SurnameBuilder();
        System.out.println("Enter surname: ");
        String surname = Util.scanner.nextLine();
        FlyweightSurnameFactory.getSurnameFlyweight(surname, surnameCreator);

        Coordinates coordinates = Util.getCoordinates();

        immigrantsContainer.addToContainer(coordinates, FlyweightNameFactory.nameFactory.get(name), surnameCreator.getData());
    }

    public static String convertLine(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
