package uk.co.tertiarybrewery.brewapi.mashprofile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.hlttemps.HltTempsDao;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempsDao;

@Component
public class MashProfileBuilder {
    private final HltTempsDao hltTempsDao;
    TargetTempsDao targetTempsDao;
    @Autowired
    public MashProfileBuilder(TargetTempsDao targetTempsDao, HltTempsDao hltTempsDao) {
        this.targetTempsDao = targetTempsDao;
        this.hltTempsDao = hltTempsDao;
    }

    public void clearProfile() {
        targetTempsDao.clear();
        hltTempsDao.clear();
    }

    public void addStep(Float step_time, Float step_temp, Float ramp_time, Float infusion_temp) {
        int lastStepEndTime = targetTempsDao.getLastStepEndTime();
        int lastStepStartTime = targetTempsDao.getLastStepStartTime();
        int hltStepTime = lastStepStartTime + ((lastStepEndTime - lastStepStartTime)/3);
        int firstPointTime = lastStepEndTime;
        if (lastStepEndTime !=0) {
            firstPointTime += Math.round(ramp_time * 60);
        }
        int secondPointTime = firstPointTime + Math.round(step_time*60);
        targetTempsDao.addTempPoint(firstPointTime, step_temp);
        targetTempsDao.addTempPoint(secondPointTime, step_temp);
        hltTempsDao.addTempPoint(hltStepTime, infusion_temp);
    }

    public void addHltSpargeStep(Float spargeTemp ) {
        int lastStepEndTime = targetTempsDao.getLastStepEndTime();
        int lastStepStartTime = targetTempsDao.getLastStepStartTime();
        int hltStepTime = lastStepStartTime + ((lastStepEndTime - lastStepStartTime)/3);
        hltTempsDao.addTempPoint(hltStepTime, spargeTemp);
        hltTempsDao.addTempPoint(lastStepEndTime+3600, spargeTemp);
    }
}
