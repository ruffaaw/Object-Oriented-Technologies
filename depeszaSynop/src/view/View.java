package view;

import dataCollection.IDataCollection;
import dataDecoder.DataDecoder;
import dataDecoder.IDataDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View implements IView {

    IDataCollection collection;
    IDataDecoder dataDecoder;

    public void setDataCollection(IDataCollection dataCollection) {
        this.collection = dataCollection;
    }

    private String DataSelection() {
        String result = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj dzien poczatku zakresu:\n");
        String startDate = sc.next();
        if (!isNumeric(startDate) || Integer.parseInt(startDate) <= 0 || Integer.parseInt(startDate) > 31) {
            System.err.println("Podaj prawdziwe dane");
            System.exit(0);
        }
        System.out.print("Podaj dzien konca zakresu:\n");
        String endDate = sc.next();
        if (!isNumeric(endDate) || Integer.parseInt(startDate) <= 0 || Integer.parseInt(startDate) > 31 || Integer.parseInt(endDate) < Integer.parseInt(startDate)) {
            System.err.println("Podaj prawdziwe dane");
            System.exit(0);
        }

        result = TakeDays(startDate, endDate);
        return result;
    }

    private String FindData(List<Integer> listDays) {
        dataDecoder = new DataDecoder();
        String result = "";
        int counter = 0;
        for (int i = 0; i < collection.getList().size(); i++) {
            int day = Integer.parseInt(String.valueOf(collection.getList().get(i).getDateAndTime().charAt(0)) + String.valueOf(collection.getList().get(i).getDateAndTime().charAt(1)));
            if (listDays.contains(day)) {
                counter = counter + 1;
                result += "Dane nr. " + counter + "\n"
                        + dataDecoder.informationAboutTypeOfDepesza(collection.getList().get(i).getType())
                        + dataDecoder.informationAboutDateAndTime(collection.getList().get(i).getDateAndTime())
                        + dataDecoder.informationAboutID(collection.getList().get(i).getID())
                        + dataDecoder.informationAboutStructure(collection.getList().get(i).getStructure())
                        + dataDecoder.informationAboutCloudly(collection.getList().get(i).getCloudy())
                        + dataDecoder.informationAboutTemperature(collection.getList().get(i).getTemperature())
                        + dataDecoder.informationAboutHumidity(collection.getList().get(i).getHumidity())
                        + dataDecoder.informationAboutPressure(collection.getList().get(i).getPressure())
                        + dataDecoder.informationAboutPressureReducedToSeaLevel(collection.getList().get(i).getPressureReducedToSeaLevel())
                        + dataDecoder.informationAboutPressureTendency(collection.getList().get(i).getPressureTendency())
                        + dataDecoder.informationAboutRainfall(collection.getList().get(i).getRainfall())
                        + dataDecoder.informationAboutCurrentAndPreviousWeatherCondition(collection.getList().get(i).getCurrentAndPreviousWeatherCondition())
                        + dataDecoder.informationAboutSkyCondition(collection.getList().get(i).getSkyCondition())
                        + dataDecoder.informationAboutExactTimeOfObservation(collection.getList().get(i).getExactTimeOfObservation()) + "\n";
            }
        }
        return result;
    }

    private String TakeDays(String startDate, String endDate) {
        int counter = 0;
        if (Integer.parseInt(startDate) == Integer.parseInt(endDate))
            counter = 1;
        else
            counter = Integer.parseInt(endDate) - Integer.parseInt(startDate) + 1;

        List<Integer> listDays = new ArrayList<Integer>();
        for (int i = 0; i < counter; i++) {
            listDays.add(Integer.parseInt(startDate) + i);
        }

        return FindData(listDays);
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void menu() {
        String result = "";
        result = DataSelection();
        System.out.printf(result);
    }
}
