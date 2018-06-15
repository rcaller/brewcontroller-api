package uk.co.tertiarybrewery.brewapi.currenttemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class CurrentTempDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public TempData getTemps() {
        TempData temps = null;
        try {
            temps = (TempData) jdbcTemplate.queryForObject("SELECT mash, herms, flow FROM mashData ORDER BY measurementTime DESC LIMIT 1", new Object[]{},
                    new BeanPropertyRowMapper(TempData.class));
        }
        catch (EmptyResultDataAccessException e){

        }
        return temps;
    }

    public int getSecondsElapsed() {
//        Integer secondsElapsed = jdbcTemplate.queryForObject( "SELECT IFNULL ((SELECT TIME_TO_SEC(TIMEDIFF(MAX(measurementTime), MIN(measurementTime))) FROM mashData), 0)", Integer.class);
        Integer secondsElapsed = jdbcTemplate.queryForObject( "SELECT IFNULL((SELECT (EXTRACT(EPOCH FROM (MAX(measurementTime)))) -  (EXTRACT(EPOCH FROM (MIN(measurementTime)))) as tmediff FROM mashData), 0)", Integer.class);

        return secondsElapsed.intValue();
    }
}
