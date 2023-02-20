package view;

import immigrant.Immigrant;
import immigrant.ImmigrantsContainer;
import proxy.Proxy;

import java.io.*;

import static immigrant.coordinates.Util.readNumber;

public class View {
    public static Proxy proxy;

    public View() {
        proxy = new Proxy();
    }

    public void menu() {
        double choice;
        while (true) {
            System.out.println("1 - Add new immigrant\n" + "2 - Display all legitimized immigrants.\n" + "3 - Exit.");
            choice = readNumber();
            switch ((int) choice) {
                case 1 -> proxy.readData();
                case 2 -> display();
                case 3 -> System.exit(0);
                default -> System.out.println("Wrong command!");
            }
        }
    }

    public void display() {
        try {
            for (Immigrant immigrant : ImmigrantsContainer.getImmigrantsContainer().getImmigrantList()) {
                System.out.println(immigrant.toString());
            }
        } catch (NullPointerException e) {
            System.out.println("You didn't add any immigrant");
        }
    }

}
