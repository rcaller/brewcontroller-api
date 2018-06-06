package uk.co.tertiarybrewery.brewapi.pidintegration;


public class TargetTempResponse {
    public float getActive() {
        return active;
    }

    public void setActive(float active) {
        this.active = active;
    }

    public float getPre_warm() {
        return pre_warm;
    }

    public void setPre_warm(float pre_warm) {
        this.pre_warm = pre_warm;
    }

    float active;
    float pre_warm;
}
