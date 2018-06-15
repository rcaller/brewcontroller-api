package uk.co.tertiarybrewery.brewapi.targettemps;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class TargetTempsDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<TargetTempPoint> getTemps() {

        List<TargetTempPoint> temps =  new ArrayList<TargetTempPoint>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT secondsElapsed, temperature FROM targetTemps ORDER BY secondsElapsed ASC");

        for(Map row: rows) {
            TargetTempPoint ttp = new TargetTempPoint();
            Integer elapsedInteger = (Integer)row.get("secondsElapsed");
            ttp.setSecondsElapsed(elapsedInteger.doubleValue());
            ttp.setTemp((Double)row.get("temperature"));

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

    public Optional<TargetTempPoint> getTargetTempPointBefore(int secondsElapsed) {
        Optional<TargetTempPoint> before = Optional.empty();
        try {
            Map<String, Object> row =jdbcTemplate.queryForMap("select secondsElapsed, temperature from targetTemps where secondsElapsed <= ? ORDER BY secondsElapsed DESC LIMIT 1;", secondsElapsed);
            TargetTempPoint ttp = new TargetTempPoint();
            Integer elapsedInteger = (Integer)row.get("secondsElapsed");
            ttp.setSecondsElapsed(elapsedInteger.doubleValue());
            ttp.setTemp((Double) row.get("temperature"));
            before = Optional.of(ttp);
        }
        catch (IncorrectResultSizeDataAccessException e) {

        }
        return before;
    }

    public Optional<TargetTempPoint> getTargetTempPointAfter(int secondsElapsed) {
        Optional<TargetTempPoint> after = Optional.empty();
        try {
            Map<String, Object> row =jdbcTemplate.queryForMap("select secondsElapsed, temperature from targetTemps where secondsElapsed >= ? ORDER BY secondsElapsed ASC LIMIT 1;", secondsElapsed);
            TargetTempPoint ttp = new TargetTempPoint();
            Integer elapsedInteger = (Integer)row.get("secondsElapsed");
            ttp.setSecondsElapsed(elapsedInteger.doubleValue());
            ttp.setTemp((Double) row.get("temperature"));
            after = Optional.of(ttp);
        }
        catch (IncorrectResultSizeDataAccessException e) {
        }
        return after;
    }
}
