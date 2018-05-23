package uk.co.tertiarybrewery.brewapi.targettemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TargetTempsService {
    @Autowired
    private TargetTempsDao targetTempsDao;

    public List<TargetTempPoint> getTemps() {
        return this.targetTempsDao.getTemps();
    }
}
