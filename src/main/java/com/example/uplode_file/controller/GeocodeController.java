package com.example.uplode_file.controller;


import com.example.uplode_file.dto.Coordinates;
import com.example.uplode_file.dto.GeocodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/geocode")
@RequiredArgsConstructor
public class GeocodeController {
    private static final String GEOCODE_API_URL = "https://api.example.com/geocode";


    private final RestTemplate restTemplate;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeocodeResponse getGeocodeData(@RequestBody Coordinates coordinates) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(GEOCODE_API_URL)
                .queryParam("lat", coordinates.getLatitude())
                .queryParam("lon", coordinates.getLongitude())
                .build();

        RequestEntity<?> requestEntity = new RequestEntity<>(HttpMethod.GET, uriComponents.toUri());

        return restTemplate.exchange(requestEntity, GeocodeResponse.class).getBody();

    }
}
