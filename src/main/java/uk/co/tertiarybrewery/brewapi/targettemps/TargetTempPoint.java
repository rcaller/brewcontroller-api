package uk.co.tertiarybrewery.brewapi.targettemps;


public class TargetTempPoint {

    public float getSecondsElapsed() {
        return secondsElapsed;
    }

    public float getTemp() {
        return temp;
    }

    private float secondsElapsed;
    private float temp;
    
    public void setSecondsElapsed(float secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }
}
