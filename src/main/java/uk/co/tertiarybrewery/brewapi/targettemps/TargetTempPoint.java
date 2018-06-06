package uk.co.tertiarybrewery.brewapi.targettemps;


public class TargetTempPoint {

    public Float getSecondsElapsed() {
        return secondsElapsed;
    }

    public float getTemp() {
        return temp;
    }

    private Float secondsElapsed;
    private float temp;
    
    public void setSecondsElapsed(Float secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }
}
