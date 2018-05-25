package uk.co.tertiarybrewery.brewapi.currenttemps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentTempController {

    @Autowired
    CurrentTempService currentTempService;

    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "http://localhost"})
    @GetMapping(value = "/current",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(currentTempService.getTemps(), HttpStatus.OK);
    }



}
