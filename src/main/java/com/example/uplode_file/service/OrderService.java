package com.example.uplode_file.service;

import com.example.uplode_file.entity.OrderTechnicalServiceEntity;
import com.example.uplode_file.reposetory.OrderRep;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final DatabaseReader dbReader;
    private final OrderRep repository;



    public boolean add(HttpServletRequest request, Integer userId) {


        String userLocation = getUserLocation(request);
        OrderTechnicalServiceEntity order = new OrderTechnicalServiceEntity();

        order.setUserId(userId);
        order.setLocation(userLocation);

        repository.save(order);
        return true;
    }

    public String getUserLocation(HttpServletRequest request) {
        try {
            String localIPAddress = "178.218.201.17";

            //* kelgan requesdan ip manzilni olish kerak lekin postmandan tekshirilganda ip address 127.0.0.1 bolib keladi */
//            String localIPAddress = request.getHeader("X-Real-IP");
//            if (localIPAddress == null || localIPAddress.isEmpty()) {
//                localIPAddress = request.getRemoteAddr();
//            }


            InetAddress ip = InetAddress.getByName(localIPAddress);

            CityResponse response = dbReader.city(ip);

            String country = response.getCountry().getName();
            String state = response.getLeastSpecificSubdivision().getName();
            String city = response.getCity().getName();
            String postal = response.getPostal().getCode();

            Location location = response.getLocation();

            System.out.println("Latitude: " + location.getLatitude());
            System.out.println("Longitude: " + location.getLongitude());

            return country +", "+city+", "+state+", "+postal;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return null;
    }

    public OrderTechnicalServiceEntity getUserId(Integer id) {
        return repository.findById(id).orElseThrow();
    }
}
