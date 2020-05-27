package uk.co.tertiarybrewery.brewapi.RunMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RunModeDao {

    JdbcTemplate jdbcTemplate;
    @Autowired
    public RunModeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getRunMode() {
        String runMode = jdbcTemplate.queryForObject( "SELECT runMode from runMode", String.class);
        return runMode;
    }

    public void setRunMode(String runMode) {
        jdbcTemplate.update("UPDATE runMode SET runMode=?", runMode);
        
    }
}
