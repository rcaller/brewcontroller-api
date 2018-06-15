package uk.co.tertiarybrewery.brewapi.tempdata;


import org.joda.time.Instant;

public class TempPoint {
    private Instant timeStamp;
    private double temperature;

    public TempPoint(Instant measurementTime, double temperature) {
        this.timeStamp = measurementTime;
        this.temperature = temperature;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }


}
