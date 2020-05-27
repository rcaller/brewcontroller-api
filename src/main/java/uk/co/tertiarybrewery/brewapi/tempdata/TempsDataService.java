package uk.co.tertiarybrewery.brewapi.tempdata;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.hlttemps.HltTempsService;
import uk.co.tertiarybrewery.brewapi.pidintegration.TempReport;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempPoint;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempsService;

import java.util.List;

@Component
public class TempsDataService {
    @Autowired
    TempsDataDao tempsDataDao;

    @Autowired
    TargetTempsService targetTempsService;

    @Autowired
    HltTempsService hltTempsService;

    public TempsData getTemps() {
        TempsData tempsData = new TempsData();
        List<InternalTempPoint> internalTemps = tempsDataDao.getTemps();
        Instant startTime = getStartTime();
        for (InternalTempPoint itp: internalTemps) {
            tempsData.addHermsTemp(new TempPoint(itp.getMeasurementTime(), itp.getHerms()));
            tempsData.addMashTemp(new TempPoint(itp.getMeasurementTime(), itp.getMash()));
            tempsData.addFlowTemp(new TempPoint(itp.getMeasurementTime(), itp.getFlow()));
            tempsData.addHltTemp(new TempPoint(itp.getMeasurementTime(), itp.getHlt()));
        }
        List<TargetTempPoint> targetTemps = targetTempsService.getTemps();
        for (TargetTempPoint ttp: targetTemps) {
            tempsData.addTargetTemp(new TempPoint(startTime.plus((long)(ttp.getSecondsElapsed()*1000)), ttp.getTemp()));
        }
        return tempsData;
    }

    public HltTempsData getHltTemps() {
        HltTempsData tempsData = new HltTempsData();
        List<InternalTempPoint> internalTemps = tempsDataDao.getTemps();
        Instant startTime = getStartTime();
        for (InternalTempPoint itp: internalTemps) {
            tempsData.addHltTemp(new TempPoint(itp.getMeasurementTime(), itp.getHlt()));

        }
        List<TargetTempPoint> targetTemps = hltTempsService.getTemps();
        Double lastTargetTemp = 0.0;
        for (TargetTempPoint ttp: targetTemps) {
            if (lastTargetTemp != 0.0) {
                tempsData.addTargetTemp(new TempPoint(startTime.plus((long)(ttp.getSecondsElapsed()*1000)), lastTargetTemp));
            }
            tempsData.addTargetTemp(new TempPoint(startTime.plus((long)(ttp.getSecondsElapsed()*1000)), ttp.getTemp()));
            lastTargetTemp = ttp.getTemp();
        }
        return tempsData;
    }

    public Instant getStartTime() {
        return tempsDataDao.getStartTime();

    }


    public void clear() {
        tempsDataDao.clear();
    }

    public void recordTemps(TempReport tempReport) {
        tempsDataDao.recordTemps(tempReport);
    }
}
