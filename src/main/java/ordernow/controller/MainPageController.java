package ordernow.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> mainPageResponse(){
        String stringResponse = "Hello costumer";
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN)
                .body(stringResponse);
    }

}
