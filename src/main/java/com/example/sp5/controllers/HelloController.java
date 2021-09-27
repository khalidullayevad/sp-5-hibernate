package com.example.sp5.controllers;

import com.example.sp5.services.AddTwoHoursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
 public class HelloController {


    private final AddTwoHoursService addTwoHoursService;

    public HelloController(AddTwoHoursService addTwoHoursService) {
        this.addTwoHoursService = addTwoHoursService;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<?> getAllAuthors(){
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping(value="/sum")
    public ResponseEntity<?> findSum(@RequestParam int num1, @RequestParam int num2){
        int sum = num1 + num2;
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    @GetMapping("/addTwoHours")
    public String addTwoHours(@RequestParam String date) throws ParseException {

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date parseDate = formatter.parse(date);
        String newDate = addTwoHoursService.getDateTwoHorusLate(parseDate).toString();

        return  newDate ;
    }


}

