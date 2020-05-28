package uk.co.tertiarybrewery.brewapi.hlttemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempException;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempPoint;

import java.util.List;
import java.util.Optional;

@Component
public class HltTempsService {
    @Autowired
    HltTempsDao hltTempsDao;

    public List<TargetTempPoint> getTemps() {
        return hltTempsDao.getTemps();
    }

    public double getTargetTemp(int secondsElapsed) throws TargetTempException {
        Optional<TargetTempPoint> targetTemp = hltTempsDao.getHltTemp(secondsElapsed);
        if (targetTemp.isPresent()) {
            return targetTemp.get().getTemp();
        }
        else {
            throw new TargetTempException("No hlt points found at or before current time");
        }
    }
}
