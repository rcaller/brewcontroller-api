package uk.co.tertiarybrewery.brewapi.tempdata;


import org.joda.time.Instant;

public class TempPoint {
    private Instant timeStamp;
    private float temperature;

    public TempPoint(Instant measurementTime, float temperature) {
        this.timeStamp = measurementTime;
        this.temperature = temperature;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }


}
