package uk.co.tertiarybrewery.brewapi.pidintegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PidIntegrationController {

    @Autowired
    PidIntegrationService pidIntegrationService;

    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "http://localhost"})
    @PostMapping(value = "/in",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> in(@RequestBody TempReport tempReport) {
        pidIntegrationService.recordTemps(tempReport);
        return new ResponseEntity<>(pidIntegrationService.getTargetTemp(), HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "http://localhost"})
    @GetMapping(value = "/in",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> in() {
        return new ResponseEntity<>(pidIntegrationService.getTargetTemp(), HttpStatus.OK);
    }



}
