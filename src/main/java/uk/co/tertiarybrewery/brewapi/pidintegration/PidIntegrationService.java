package uk.co.tertiarybrewery.brewapi.pidintegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.currenttemps.CurrentTempService;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempsService;

@Component
public class PidIntegrationService {

    private final TargetTempsService targetTempsService;
    private CurrentTempService currentTempService;
    @Autowired
    public PidIntegrationService(CurrentTempService currentTempService, TargetTempsService targetTempsService) {
        this.currentTempService = currentTempService;
        this.targetTempsService = targetTempsService;
    }



    public float getTargetTemp() {
      int secondsElapsed = currentTempService.getSecondsElapsed();
      float targetTemp = targetTempsService.getTargetTemp(secondsElapsed);
      return targetTemp;
    }
}
