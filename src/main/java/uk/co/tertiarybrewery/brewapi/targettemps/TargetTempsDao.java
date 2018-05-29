package uk.co.tertiarybrewery.brewapi.targettemps;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TargetTempsDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<TargetTempPoint> getTemps() {

        List<TargetTempPoint> temps =  new ArrayList<TargetTempPoint>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT secondsElapsed, temperature FROM targetTemps ORDER BY secondsElapsed ASC");

        for(Map row: rows) {
            TargetTempPoint ttp = new TargetTempPoint();

            ttp.setSecondsElapsed((int)row.get("secondsElapsed"));
            ttp.setTemp((float)row.get("temperature"));

            temps.add(ttp);
        }

        return temps;
    }

    public void clear() {
        jdbcTemplate.update("DELETE FROM targetTemps");
    }

    public int getLastTempPointTime() {
        Integer lastTempPoint = jdbcTemplate.queryForObject("SELECT MAX(secondsElapsed) FROM targetTemps", Integer.class);
        if (lastTempPoint!=null) {
            return (int)lastTempPoint;
        }
        else {
            return 0;
        }
    }

    public void addTempPoint(int pointTime, Float stepTemp) {
        jdbcTemplate.update("INSERT INTO targetTemps (secondsElapsed, temperature) VALUES (?, ?)", pointTime, stepTemp);
    }
}
