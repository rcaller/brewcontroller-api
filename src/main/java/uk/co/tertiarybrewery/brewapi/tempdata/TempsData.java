package uk.co.tertiarybrewery.brewapi.tempdata;


import java.util.ArrayList;
import java.util.List;

public class TempsData {
    List<TempPoint> target;
    List<TempPoint> mash;
    List<TempPoint> herms;
    List<TempPoint> flow;
    List<TempPoint> hlt;

    public List<TempPoint> getTarget() {
        return target;
    }

    public List<TempPoint> getMash() {
        return mash;
    }

    public List<TempPoint> getHerms() {
        return herms;
    }

    public List<TempPoint> getFlow() {
        return flow;
    }
    public List<TempPoint> getHlt() {
        return hlt;
    }


    public TempsData() {
       this.target = new ArrayList<>();
       this.mash = new ArrayList<>();
       this.herms = new ArrayList<>();
       this.flow = new ArrayList<>();
       this.hlt = new ArrayList<>();
    }

    public void addMashTemp(TempPoint temp) {
        this.mash.add(temp);
    }
    public void addHermsTemp(TempPoint temp) {
        this.herms.add(temp);
    }
    public void addTargetTemp(TempPoint temp) {
        this.target.add(temp);
    }
    public void addFlowTemp(TempPoint temp) {
        this.flow.add(temp);
    }

    public void addHltTemp(TempPoint temp) {this.hlt.add(temp);
    }
}
