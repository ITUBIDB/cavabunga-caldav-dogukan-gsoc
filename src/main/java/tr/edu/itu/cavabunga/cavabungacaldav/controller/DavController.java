package tr.edu.itu.cavabunga.cavabungacaldav.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DavController {
    @GetMapping
    public String test(){
        return "test";
    }
}
