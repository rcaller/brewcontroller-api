package uk.co.tertiarybrewery.brewapi.tempdata;


import org.joda.time.Instant;

import java.sql.Timestamp;

public class InternalTempPoint {
    public InternalTempPoint() {

    }

    public InternalTempPoint(Timestamp measurementTime, double mash, double herms, double flow) {
        this.measurementTime = new Instant(measurementTime.getTime());
        this.mash = mash;
        this.herms = herms;
        this.flow = flow;
    }

    public double getHerms() {
        return herms;
    }

    public void setHerms(double herms) {
        this.herms = herms;
    }

    public double getMash() {
        return mash;
    }

    public void setMash(double mash) {
        this.mash = mash;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
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
    private double herms;
    private double mash;
    private double flow;
}
