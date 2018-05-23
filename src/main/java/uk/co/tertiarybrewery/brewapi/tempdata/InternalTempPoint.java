package uk.co.tertiarybrewery.brewapi.tempdata;


import org.joda.time.Instant;

import java.sql.Timestamp;

public class InternalTempPoint {
    public InternalTempPoint() {

    }

    public InternalTempPoint(Timestamp measurementTime, float mash, float herms, float flow) {
        this.measurementTime = new Instant(measurementTime.getTime());
        this.mash = mash;
        this.herms = herms;
        this.flow = flow;
    }

    public float getHerms() {
        return herms;
    }

    public void setHerms(float herms) {
        this.herms = herms;
    }

    public float getMash() {
        return mash;
    }

    public void setMash(float mash) {
        this.mash = mash;
    }

    public float getFlow() {
        return flow;
    }

    public void setFlow(float flow) {
        this.flow = flow;
    }

    public Instant getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Instant measurementTime) {
        this.measurementTime = measurementTime;
    }

    public void setMeasurementTime(Timestamp measurementTime) {
        this.measurementTime = new Instant(measurementTime.getTime());
    }

    private Instant measurementTime;
    private float herms;
    private float mash;
    private float flow;
}
