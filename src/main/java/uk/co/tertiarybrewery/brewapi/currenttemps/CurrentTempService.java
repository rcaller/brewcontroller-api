package uk.co.tertiarybrewery.brewapi.currenttemps;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrentTempService {

    @Autowired
    CurrentTempDao currentTempDao;

    public TempData getTemps() {
        TempData temps = currentTempDao.getTemps();
        return temps;
    }

    public int getSecondsElapsed() {
        return currentTempDao.getSecondsElapsed();
    }
}
