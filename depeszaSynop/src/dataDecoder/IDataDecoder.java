package dataDecoder;

public interface IDataDecoder {
    String informationAboutTypeOfDepesza(String input);
    String informationAboutDateAndTime(String input);
    String informationAboutID(String input);
    String informationAboutStructure(String input);
    String informationAboutCloudly(String input);
    String informationAboutTemperature(String input);
    String informationAboutHumidity(String input);
    String informationAboutPressure(String input);
    String informationAboutPressureReducedToSeaLevel(String input);
    String informationAboutPressureTendency(String input);
    String informationAboutRainfall(String input);
    String informationAboutCurrentAndPreviousWeatherCondition(String input);
    String informationAboutSkyCondition(String input);
    String informationAboutExactTimeOfObservation(String input);
}
