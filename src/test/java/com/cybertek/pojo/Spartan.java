package com.cybertek.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@JsonIgnoreProperties(value = "id",allowSetters = true) // In this way, id=0 request will not send. (according to Spartan API, ID is generated auto)
public class Spartan {

    // getter, setter, toString
    private  int id;
    private  String name;
    private String gender;
    private long phone;



}


    /*
    {
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
}
     */