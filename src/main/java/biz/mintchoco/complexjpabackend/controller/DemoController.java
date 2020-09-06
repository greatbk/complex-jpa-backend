package biz.mintchoco.complexjpabackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("demo")
    public ResponseEntity<String> demo(){
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
