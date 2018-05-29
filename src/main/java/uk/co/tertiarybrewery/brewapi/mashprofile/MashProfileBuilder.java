package uk.co.tertiarybrewery.brewapi.mashprofile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempsDao;

@Component
public class MashProfileBuilder {
    TargetTempsDao targetTempsDao;
    @Autowired
    public MashProfileBuilder(TargetTempsDao targetTempsDao) {
        this.targetTempsDao = targetTempsDao;

    }

    public void clearProfile() {
        targetTempsDao.clear();

    }

    public void addStep(Float step_time, Float step_temp, Float ramp_time) {
        int lastTempPointTime = targetTempsDao.getLastTempPointTime();
        int firstPointTime = lastTempPointTime + Math.round(ramp_time*60);
        int secondPointTime = firstPointTime + Math.round(step_time*60);
        targetTempsDao.addTempPoint(firstPointTime, step_temp);
        targetTempsDao.addTempPoint(secondPointTime, step_temp);
    }
}
