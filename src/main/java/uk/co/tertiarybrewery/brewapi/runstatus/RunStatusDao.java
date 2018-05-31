package uk.co.tertiarybrewery.brewapi.runstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RunStatusDao {

    JdbcTemplate jdbcTemplate;
    @Autowired
    public RunStatusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean getRunStatus() {
        Boolean runStatus = jdbcTemplate.queryForObject( "SELECT running from runStatus", Boolean.class);
        return runStatus.booleanValue();
    }

    public void setRunStatus(boolean running) {
        jdbcTemplate.update("UPDATE runStatus SET running=?", running);
        
    }
}
