package com.songstone.coronaline.domain;

import com.songstone.coronaline.constant.PlaceType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Place {
    private Long id;

    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;
    private Integer capacity;
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
