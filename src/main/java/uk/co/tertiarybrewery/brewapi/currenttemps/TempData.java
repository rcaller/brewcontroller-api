package uk.co.tertiarybrewery.brewapi.currenttemps;

import org.springframework.stereotype.Component;

@Component
public class TempData {
    private float mash;
    private float herms;
    private float flow;

    public float getMash() {
        return mash;
    }

    public void setMash(float mash) {
        this.mash = mash;
    }

    public float getHerms() {
        return herms;
    }

    public void setHerms(float herms) {
        this.herms = herms;
    }

    public float getFlow() {
        return flow;
    }

    public void setFlow(float flow) {
        this.flow = flow;
    }
    public TempData() {

    }

    public TempData(float mash, float herms, float flow) {
        this.mash = mash;
        this.herms = herms;
        this.flow = flow;
    }
}
