package uk.co.tertiarybrewery.brewapi.tempdata;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempPoint;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempsService;

import java.util.List;

@Component
public class TempsDataService {
    @Autowired
    TempsDataDao tempsDataDao;

    @Autowired
    TargetTempsService targetTempsService;

    public TempsData getTemps() {
        TempsData tempsData = new TempsData();
        List<InternalTempPoint> internalTemps = tempsDataDao.getTemps();
        Instant startTime = getStartTime();
        for (InternalTempPoint itp: internalTemps) {
            tempsData.addHermsTemp(new TempPoint(itp.getMeasurementTime(), itp.getHerms()));
            tempsData.addMashTemp(new TempPoint(itp.getMeasurementTime(), itp.getMash()));
            tempsData.addFlowTemp(new TempPoint(itp.getMeasurementTime(), itp.getFlow()));
        }
        List<TargetTempPoint> targetTemps = targetTempsService.getTemps();
        for (TargetTempPoint ttp: targetTemps) {
            tempsData.addTargetTemp(new TempPoint(startTime.plus((long)(ttp.getSecondsElapsed()*1000)), ttp.getTemp()));
        }
        return tempsData;
    }

    public Instant getStartTime() {
        return tempsDataDao.getStartTime();

    }
}
