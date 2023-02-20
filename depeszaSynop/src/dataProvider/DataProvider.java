package dataProvider;

import dataCollection.DataCollection;
import dataCollection.IDataCollection;
import synop.ISynop;
import synop.Synop;

public class DataProvider implements IDataProvider {

    @Override
    public void provide(String input, IDataCollection output) {
        String[] table = inputToTable(input);
        tableToList(table, output);
    }

    private void tableToList(String[] input, IDataCollection output) {
        for (String s : input) {
            String[] table = inputToTableBySpace(s);
            table[table.length - 1] = deletingCharactersEquals(table[table.length - 1]);
            ISynop tSynop = this.elementToISynop(table);
            output.getList().add(tSynop);
        }
    }

    private String[] inputToTableBySpace(String input) {
        return input.split(" ");
    }

    private ISynop elementToISynop(String[] input) {
        ISynop synop = new Synop();
        for (int i = 0; i < input.length; i++) {
            if (i == 0) {
                String result = takeTypeOfDepesza(input[0]);
                synop.setType(result);
            } else if (i == 1) {
                synop.setDateAndTime(input[1]);
            } else if (i == 2) {
                synop.setID(input[2]);
            } else if (i == 3) {
                synop.setStructure(input[3]);
            } else if (i == 4) {
                synop.setCloudy(input[4]);
            } else {
                char groupDesignation = input[i].charAt(0);
                switch (groupDesignation) {
                    case '1' -> synop.setTemperature(input[i]);
                    case '2' -> synop.setHumidity(input[i]);
                    case '3' -> {
                        if (input[i].length() == 3)
                            return synop;
                        synop.setPressure(input[i]);
                    }
                    case '4' -> {
                        if (input[i].length() == 3)
                            return synop;
                        synop.setPressureReducedToSeaLevel(input[i]);
                    }
                    case '5' -> {
                        if (input[i].length() == 3)
                            return synop;
                        synop.setPressureTendency(input[i]);
                    }
                    case '6' -> synop.setRainfall(input[i]);
                    case '7' -> synop.setCurrentAndPreviousWeatherCondition(input[i]);
                    case '8' -> synop.setSkyCondition(input[i]);
                    case '9' -> synop.setExactTimeOfObservation(input[i]);
                    default -> {
                        System.err.println("Incorrect data");
                        System.exit(0);
                    }
                }
            }
        }
        return synop;
    }

    private String[] inputToTable(String input) {
        return input.split("\n");
    }

    private String takeTypeOfDepesza(String input) {
        String[] result = input.split(",");
        return result[6];
    }

    private String deletingCharactersEquals(String input) {
        return input.replace("=", "");
    }

}
