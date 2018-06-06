package uk.co.tertiarybrewery.brewapi.runstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class RunStatusController {

    private RunStatusDao runStatusDao;
    @Autowired
    public RunStatusController(RunStatusDao runStatusDao) {
        this.runStatusDao = runStatusDao;
    }

    @CrossOrigin(origins = {"*"})
    @GetMapping(value = "/running",produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public ResponseEntity<?> getRunStatus() {
        boolean running = runStatusDao.getRunStatus();
        HashMap<String, Boolean> runStatus = new HashMap<String, Boolean>();

        runStatus.put("running", running);
        return new ResponseEntity<>(runStatus, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"})
    @PostMapping(value = "/running",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> updateRunStatus(@RequestBody RunStatus runStatus) {
        runStatusDao.setRunStatus(runStatus.isRunning());
        return getRunStatus();

    }



}
