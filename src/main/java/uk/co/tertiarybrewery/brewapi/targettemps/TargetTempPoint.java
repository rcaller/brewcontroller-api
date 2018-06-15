package uk.co.tertiarybrewery.brewapi.targettemps;


public class TargetTempPoint {

    public Double getSecondsElapsed() {
        return secondsElapsed;
    }

    public Double getTemp() {
        return temp;
    }

    private Double secondsElapsed;
    private Double temp;
    
    public void setSecondsElapsed(Double secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
