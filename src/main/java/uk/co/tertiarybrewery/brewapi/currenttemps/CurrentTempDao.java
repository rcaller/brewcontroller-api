package uk.co.tertiarybrewery.brewapi.currenttemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class CurrentTempDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public TempData getTemps() {
        TempData temps = (TempData)jdbcTemplate.queryForObject("SELECT mash, herms, flow FROM mashData ORDER BY measurementTime DESC LIMIT 1",new Object[] { },
                new BeanPropertyRowMapper(TempData.class));
        return temps;
    }

    public int getSecondsElapsed() {
        Integer secondsElapsed = jdbcTemplate.queryForObject( "SELECT TIME_TO_SEC(TIMEDIFF(MAX(measurementTime), MIN(measurementTime))) FROM mashData", Integer.class);
        return secondsElapsed.intValue();
    }
}
