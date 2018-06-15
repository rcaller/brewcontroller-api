package uk.co.tertiarybrewery.brewapi.pidintegration;


public class TargetTempResponse {
    public double getActive() {
        return active;
    }

    public void setActive(double active) {
        this.active = active;
    }

    public double getPre_warm() {
        return pre_warm;
    }

    public void setPre_warm(double pre_warm) {
        this.pre_warm = pre_warm;
    }

    double active;
    double pre_warm;
}
