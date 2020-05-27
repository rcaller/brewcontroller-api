package uk.co.tertiarybrewery.brewapi.tempdata;


import java.util.ArrayList;
import java.util.List;

public class HltTempsData {
    List<TempPoint> target;
    List<TempPoint> hlt;

    public List<TempPoint> getTarget() {
        return target;
    }


    public List<TempPoint> getHlt() {
        return hlt;
    }


    public HltTempsData() {
       this.target = new ArrayList<>();

       this.hlt = new ArrayList<>();
    }


    public void addTargetTemp(TempPoint temp) {
        this.target.add(temp);
    }

    public void addHltTemp(TempPoint temp) {this.hlt.add(temp);
    }
}
