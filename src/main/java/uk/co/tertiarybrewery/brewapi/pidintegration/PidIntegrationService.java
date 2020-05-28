package uk.co.tertiarybrewery.brewapi.pidintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.RunMode.RunModeDao;
import uk.co.tertiarybrewery.brewapi.currenttemps.CurrentTempService;
import uk.co.tertiarybrewery.brewapi.hlttemps.HltTempsService;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempException;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempsService;
import uk.co.tertiarybrewery.brewapi.tempdata.TempsDataService;

@Component
public class PidIntegrationService {

    private final TargetTempsService targetTempsService;
    private CurrentTempService currentTempService;
    private RunModeDao runModeDao;
    private TempsDataService tempsDataService;
    private HltTempsService hltTempService;
    private static final Logger logger = LoggerFactory.getLogger(PidIntegrationService.class);
    @Autowired
    public PidIntegrationService(CurrentTempService currentTempService,
                                 TargetTempsService targetTempsService,
                                 RunModeDao runModeDao,
                                 TempsDataService tempsDataService,
                                 HltTempsService hltTempService) {
        this.currentTempService = currentTempService;
        this.targetTempsService = targetTempsService;
        this.runModeDao=runModeDao;
        this.tempsDataService = tempsDataService;
        this.hltTempService = hltTempService;
    }



    public TargetTempResponse getTargetTemp() throws TargetTempException {
      int secondsElapsed = currentTempService.getSecondsElapsed();
      double targetTemp = 65;
      double hltTemp = 75;
      try {
          targetTemp = targetTempsService.getTargetTemp(secondsElapsed);
      }
      catch (TargetTempException e) {
          logger.warn("No target temp available");
      }
      try {
            hltTemp = hltTempService.getTargetTemp(secondsElapsed);
        }
        catch (TargetTempException e) {
            logger.warn("No target temp available");
        }
      TargetTempResponse ttr = new TargetTempResponse();
      if (runModeDao.getRunMode().contentEquals("MASH")) {
          ttr.setActive(targetTemp);
      }
      else {
          ttr.setPre_warm(targetTemp);
      }
      ttr.setHlt(hltTemp);
      return ttr;
    }

    public void recordTemps(TempReport tempReport) {
        if (!runModeDao.getRunMode().contentEquals("MASH")) {
            tempsDataService.clear();
        }
        tempsDataService.recordTemps(tempReport);

    }
}
