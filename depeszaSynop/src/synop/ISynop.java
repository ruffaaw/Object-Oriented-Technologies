package synop;

public interface ISynop {
    String getType();
    void setType(String type);
    String getDateAndTime();
    void setDateAndTime(String dateAndTime);
    String getID();
    void setID(String ID);
    String getStructure();
    void setStructure(String structure);
    String getCloudy();
    void setCloudy(String cloudy);
    String getTemperature();
    void setTemperature(String temperature);
    String getHumidity();
    void setHumidity(String humidity);
    String getPressure();
    void setPressure(String pressure);
    String getPressureReducedToSeaLevel();
    void setPressureReducedToSeaLevel(String pressureReducedToSeaLevel);
    String getPressureTendency();
    void setPressureTendency(String pressureTendency);
    String getRainfall();
    void setRainfall(String rainfall);
    String getCurrentAndPreviousWeatherCondition();
    void setCurrentAndPreviousWeatherCondition(String currentAndPreviousWeatherCondition);
    String getSkyCondition();
    void setSkyCondition(String skyCondition);
    String getExactTimeOfObservation();
    void setExactTimeOfObservation(String exactTimeOfObservation);
}
