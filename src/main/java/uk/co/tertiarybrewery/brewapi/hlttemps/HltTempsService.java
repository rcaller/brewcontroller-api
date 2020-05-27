package uk.co.tertiarybrewery.brewapi.hlttemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempPoint;

import java.util.List;

@Component
public class HltTempsService {
    @Autowired
    HltTempsDao hltTempsDao;

    public List<TargetTempPoint> getTemps() {
        return hltTempsDao.getTemps();
    }
}
