package uk.co.tertiarybrewery.brewapi.hlttemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class HltTempsDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void clear() {

        jdbcTemplate.update("DELETE FROM hltTemps");

    }

    public void addTempPoint(int pointTime, Float stepTemp) {
        jdbcTemplate.update("INSERT INTO hltTemps (secondsElapsed, temperature) VALUES (?, ?)", pointTime, stepTemp);
    }
    public Optional<TargetTempPoint> getHltTemp(int secondsElapsed) {
        Optional<TargetTempPoint> before = Optional.empty();
        try {
            Map<String, Object> row =jdbcTemplate.queryForMap("select secondsElapsed, temperature from hltTemps where secondsElapsed <= ? ORDER BY secondsElapsed DESC LIMIT 1;", secondsElapsed);
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

    public List<TargetTempPoint> getTemps() {
        List<TargetTempPoint> temps =  new ArrayList<TargetTempPoint>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT secondsElapsed, temperature FROM hltTemps ORDER BY secondsElapsed ASC");

        for(Map row: rows) {
            TargetTempPoint ttp = new TargetTempPoint();
            Integer elapsedInteger = (Integer)row.get("secondsElapsed");
            ttp.setSecondsElapsed(elapsedInteger.doubleValue());
            ttp.setTemp((Double)row.get("temperature"));

            temps.add(ttp);
        }

        return temps;
    }
}
