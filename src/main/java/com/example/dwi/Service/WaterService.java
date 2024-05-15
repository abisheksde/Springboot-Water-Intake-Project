package com.example.dwi.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.dwi.Model.Water;
import com.example.dwi.Repository.WaterRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class WaterService {
	
	@Autowired
    private static WaterRepository waterRepository;

    public static void addProduct(Water waterData) {
    	
    	Water water = new Water();
	    water.setAmountOfWater(waterData.getAmountOfWater());
	    water.setDate(LocalDate.now());
        // Retrieve the currently authenticated user's email
        String userEmail = getUserEmail();

        if(waterRepository ==  null) {
        	water.setEmail(userEmail);
        	waterRepository.save(water);
        }else if(!isProductEntryExistsForUserAndDate(userEmail, LocalDate.now())) {
            // If no entry exists, set the user email and save the product
        	water.setEmail(userEmail);
        	waterRepository.save(water);
        } else {
            // If an entry already exists, throw an exception or handle accordingly
            throw new RuntimeException("You have already added a product today.");
        }
    }

    private static String getUserEmail() {
        // Logic to retrieve currently authenticated user's email
   	 // Retrieve the currently authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return userEmail; // Replace with actual logic to retrieve user email
    }

    private static boolean isProductEntryExistsForUserAndDate(String userEmail, LocalDate date) {
        // Query the database to check if a product entry exists for the user and date
        List<Water> waters = waterRepository.findByUserEmailAndDate(userEmail, date);
        return !waters.isEmpty();
    }

}
