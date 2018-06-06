package uk.co.tertiarybrewery.brewapi.tempdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempsDataController {

    @Autowired
    TempsDataService tempsDataService;

    @CrossOrigin(origins = {"*"})
    @GetMapping(value = "/tempsdata",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(tempsDataService.getTemps(), HttpStatus.OK);
    }



}
