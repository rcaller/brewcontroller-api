package uk.co.tertiarybrewery.brewapi.tempdata;


import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT measurementTime, mash, herms, flow FROM mashData ORDER BY measurementTime DESC");

        for(Map row: rows) {
            InternalTempPoint itp = new InternalTempPoint();
            itp.setMeasurementTime((Timestamp) row.get("measurementTime"));
            itp.setFlow((Float)row.get("flow"));
            itp.setMash((Float)row.get("mash"));
            itp.setHerms((Float)row.get("herms"));
            temps.add(itp);
        }

        return temps;
    }

    public Instant getStartTime() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT measurementTime FROM mashData ORDER BY measurementTime ASC LIMIT 1");

        return new Instant(rows.get(0).get("measurementTime"));
    }
}
