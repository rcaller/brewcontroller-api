package uk.co.tertiarybrewery.brewapi.targettemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public double getTargetTemp(int secondsElapsed) throws TargetTempException {
        Optional<TargetTempPoint> beforeOption = targetTempsDao.getTargetTempPointBefore(secondsElapsed);
        Optional<TargetTempPoint> afterOption = targetTempsDao.getTargetTempPointAfter(secondsElapsed);
        double target = 66.0;
        if (beforeOption.isPresent()) {
            TargetTempPoint before = beforeOption.get();
            target = before.getTemp();
            if (afterOption.isPresent()) {
                TargetTempPoint after = afterOption.get();
                int timeIntoStep = (int) (secondsElapsed - before.getSecondsElapsed());
                int stepLength = (int) (after.getSecondsElapsed() - before.getSecondsElapsed());
                if (stepLength > 0) {
                    double tempDiff = after.getTemp() - before.getTemp();
                    double beforeTemp = before.getTemp();
                    target = beforeTemp + (tempDiff * ((float) timeIntoStep / stepLength));
                }
            }
        }
        else {
            throw new TargetTempException("No temp points found before current time");
        }
        return target;
    }


}
