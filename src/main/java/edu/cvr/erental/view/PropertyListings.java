package edu.cvr.erental.view;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PropertyListings {

    private UUID id;
    private String userName;
    private String rentalTitle;
    private String description;
    private int monthlyPrice;
    private String hNo;
    private String street;
    private String area;
    private String city;
    private String state;
    private String landMark;
    private int pincode;
    private String imgBase64;
    private String imgName;
    
    //List<ImageListings> images;
    
}
