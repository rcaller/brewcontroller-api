package uk.co.tertiarybrewery.brewapi.pidintegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.currenttemps.CurrentTempService;
import uk.co.tertiarybrewery.brewapi.runstatus.RunStatusDao;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempException;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempsService;
import uk.co.tertiarybrewery.brewapi.tempdata.TempsDataService;

@Component
public class PidIntegrationService {

    private final TargetTempsService targetTempsService;
    private CurrentTempService currentTempService;
    private RunStatusDao runStatusDao;
    private TempsDataService tempsDataService;

    @Autowired
    public PidIntegrationService(CurrentTempService currentTempService,
                                 TargetTempsService targetTempsService,
                                 RunStatusDao runStatusDao,
                                 TempsDataService tempsDataService) {
        this.currentTempService = currentTempService;
        this.targetTempsService = targetTempsService;
        this.runStatusDao=runStatusDao;
        this.tempsDataService = tempsDataService;
    }



    public TargetTempResponse getTargetTemp() throws TargetTempException {
      int secondsElapsed = currentTempService.getSecondsElapsed();
      float targetTemp = targetTempsService.getTargetTemp(secondsElapsed);
      TargetTempResponse ttr = new TargetTempResponse();
      if (runStatusDao.getRunStatus()) {
          ttr.setActive(targetTemp);
      }
      else {
          ttr.setPre_warm(targetTemp);
      }
      return ttr;
    }

    public void recordTemps(TempReport tempReport) {
        if (!runStatusDao.getRunStatus()) {
            tempsDataService.clear();
        }
        tempsDataService.recordTemps(tempReport);

    }
}
