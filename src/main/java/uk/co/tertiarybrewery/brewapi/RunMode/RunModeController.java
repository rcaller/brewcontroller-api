package uk.co.tertiarybrewery.brewapi.RunMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class RunModeController {

    private RunModeDao runModeDao;
    @Autowired
    public RunModeController(RunModeDao runModeDao) {
        this.runModeDao = runModeDao;
    }

    @CrossOrigin(origins = {"*"})
    @GetMapping(value = "/run_mode",produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public ResponseEntity<?> getRunMode() {
        String running = runModeDao.getRunMode();
        HashMap<String, String> runMode = new HashMap<String, String>();
        runMode.put("mode", running);
        return new ResponseEntity<>(runMode, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"*"})
    @PostMapping(value = "/run_mode",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> updateRunStatus(@RequestBody RunMode runMode) {
        runModeDao.setRunMode(runMode.runMode());
        return getRunMode();

    }



}
