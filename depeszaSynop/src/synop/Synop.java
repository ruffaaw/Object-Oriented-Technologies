package synop;

public class Synop implements ISynop {
    String type;
    String dateAndTime;
    String ID;
    String structure;
    String cloudy;
    String temperature;
    String humidity;
    String pressure;
    String pressureReducedToSeaLevel;
    String pressureTendency;
    String rainfall;
    String currentAndPreviousWeatherCondition;
    String skyCondition;
    String exactTimeOfObservation;

    public Synop() {
        type = "";
        dateAndTime = "";
        ID = "";
        structure = "";
        cloudy = "";
        temperature = "";
        humidity = "";
        pressure = "";
        pressureReducedToSeaLevel = "";
        pressureTendency = "";
        rainfall = "";
        currentAndPreviousWeatherCondition = "";
        skyCondition = "";
        exactTimeOfObservation = "";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getCloudy() {
        return cloudy;
    }

    public void setCloudy(String cloudy) {
        this.cloudy = cloudy;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getPressureReducedToSeaLevel() {
        return pressureReducedToSeaLevel;
    }

    public void setPressureReducedToSeaLevel(String pressureReducedToSeaLevel) {
        this.pressureReducedToSeaLevel = pressureReducedToSeaLevel;
    }

    public String getPressureTendency() {
        return pressureTendency;
    }

    public void setPressureTendency(String pressureTendency) {
        this.pressureTendency = pressureTendency;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public String getCurrentAndPreviousWeatherCondition() {
        return currentAndPreviousWeatherCondition;
    }

    public void setCurrentAndPreviousWeatherCondition(String currentAndPreviousWeatherCondition) {
        this.currentAndPreviousWeatherCondition = currentAndPreviousWeatherCondition;
    }

    public String getSkyCondition() {
        return skyCondition;
    }

    public void setSkyCondition(String skyCondition) {
        this.skyCondition = skyCondition;
    }

    public String getExactTimeOfObservation() {
        return exactTimeOfObservation;
    }

    public void setExactTimeOfObservation(String exactTimeOfObservation) {
        this.exactTimeOfObservation = exactTimeOfObservation;
    }
}
