package ordernow.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {

    @RequestMapping(value = "/")
    public ResponseEntity<?> mainPageResponse(){
        String stringResponse = "Hello, please use correct source path to get corresponding services.";
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN)
                .body(stringResponse);
    }

}
