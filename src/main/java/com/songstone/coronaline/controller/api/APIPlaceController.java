package com.songstone.coronaline.controller.api;

import com.songstone.coronaline.constant.PlaceType;
import com.songstone.coronaline.dto.APIDataResponse;
import com.songstone.coronaline.dto.PlaceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public APIDataResponse<List<PlaceDTO>> getPlaces(){
        return APIDataResponse.of(List.of(PlaceDTO.of(
                PlaceType.COMMON,
                "송파배드민턴장",
                "송파구 충민로 155",
                "010-1111-2222",
                40,
                "신장개업"
        )));
    }

    @PostMapping("/places")
    public boolean createPlace(){
        return true;
    }

    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceDTO> getPlace(@PathVariable Integer placeId){
        if(placeId.equals(2)){
            return APIDataResponse.of(null);
        }

        return APIDataResponse.of(PlaceDTO.of(
                PlaceType.COMMON,
                "송파배드민턴장",
                "송파구 충민로 155",
                "010-1111-2222",
                40,
                "신장개업"
        ));
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId){
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean removePlace(@PathVariable Integer placeId){
        return true;
    }
}
