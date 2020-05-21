package uk.co.tertiarybrewery.brewapi.tempdata;


import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.pidintegration.TempReport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TempsDataDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<InternalTempPoint> getTemps() {

        List<InternalTempPoint> temps =  new ArrayList<InternalTempPoint>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT measurementTime, mash, herms, flow, hlt FROM mashData ORDER BY measurementTime DESC");

        for(Map row: rows) {
            InternalTempPoint itp = new InternalTempPoint();
            itp.setMeasurementTime((Timestamp) row.get("measurementTime"));
            itp.setFlow((Double)row.get("flow"));
            itp.setMash((Double)row.get("mash"));
            itp.setHerms((Double)row.get("herms"));
            itp.setHlt((Double)row.get("hlt"));
            temps.add(itp);
        }

        return temps;
    }

    public Instant getStartTime() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT IFNULL((SELECT measurementTime FROM mashData ORDER BY measurementTime ASC LIMIT 1), NOW()) AS measurementTime");

        return new Instant(rows.get(0).get("measurementTime"));
    }

    public void clear() {
        jdbcTemplate.execute("DELETE FROM mashData");
    }

    public void recordTemps(TempReport tempReport) {
        jdbcTemplate.update("INSERT INTO mashData (measurementTime, mash, herms, flow, hlt) VALUES (now(), ?, ?, ?, ?)",
                tempReport.getMash(),
                tempReport.getHerms(),
                tempReport.getFlow(),
                tempReport.getHlt());
    }
}
