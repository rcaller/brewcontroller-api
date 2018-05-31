package uk.co.tertiarybrewery.brewapi.targettemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TargetTempsService {

    private TargetTempsDao targetTempsDao;
    @Autowired
    public TargetTempsService(TargetTempsDao targetTempsDao) {
        this.targetTempsDao = targetTempsDao;
    }

    public List<TargetTempPoint> getTemps() {
        return targetTempsDao.getTemps();
    }

    public float getTargetTemp(int secondsElapsed) {
        TargetTempPoint before = targetTempsDao.getTargetTempPointBefore(secondsElapsed);
        TargetTempPoint after = targetTempsDao.getTargetTempPointAfter(secondsElapsed);
        int timeIntoStep = (int) (secondsElapsed - before.getSecondsElapsed());
        int stepLength = (int) (after.getSecondsElapsed() - before.getSecondsElapsed());
        float tempDiff = after.getTemp()-before.getTemp();
        float beforeTemp = before.getTemp();
        float target = beforeTemp + (tempDiff * ((float)timeIntoStep/stepLength));
        return target;
    }


}
