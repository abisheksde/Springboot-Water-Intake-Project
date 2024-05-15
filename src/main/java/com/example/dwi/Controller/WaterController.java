package com.example.dwi.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dwi.Model.Water;
import com.example.dwi.Repository.WaterRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class WaterController {
	
	@GetMapping("/create") //create
	public String createAction(Model model) {
	    model.addAttribute("message", "Enter the Quantity of Water taken by You");
	    return "create";
	}
	
	@Autowired
	private WaterRepository waterRepository;

	@PostMapping("/create") //create
	public String createActionProcess(Water waterData, Model model) {
	    Water water = new Water();
	    water.setAmountOfWater(waterData.getAmountOfWater());
	    water.setDate(LocalDate.now());
	 // Retrieve the currently authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // Set the email for the product
        water.setEmail(userEmail);

        // Save the product to the database

	    waterRepository.save(water);

	    model.addAttribute("message", "The Water Details has been created successfully");
	    return "create";
	}
	
	@GetMapping("/")
	public String getAllProducts(Model model) {
        // Retrieve the currently authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // Retrieve product list based on the user's email
	    Iterable<Water> waters = waterRepository.findByUserEmail(userEmail);
	    model.addAttribute("waters", waters);
	    return "waters";
	}
	
	@GetMapping("/update/{id}")
	public String updateProduct(@PathVariable Integer id, Model model) {    
	    Optional<Water> optionalWaterDetails = waterRepository.findById(id);
	    Water waterDetails = optionalWaterDetails.get();

	    model.addAttribute("id", id);
	    model.addAttribute("waterDetails", waterDetails);
	    return "update";
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable Integer id, Integer amountOfWater, Model model) {
	    Optional<Water> optionalWaterDetails = waterRepository.findById(id);
	    Water waterDetails = optionalWaterDetails.get();
	    waterDetails.setAmountOfWater(amountOfWater);
	    waterDetails.setDate(LocalDate.now());
	    waterRepository.save(waterDetails);
	    
	    return "redirect:/all";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Integer id, Model model) {  
	    Optional<Water> optionalWaterDetails = waterRepository.findById(id);
	    Water waterDetails = optionalWaterDetails.get();
	    model.addAttribute("id", id);
	    model.addAttribute("waterDetails", waterDetails);
	    return "delete";
	}

	@PostMapping("/delete/{id}")
	public String deleteProducts(@PathVariable Integer id, Model model) {    
	    if (id != null) {
	    	waterRepository.deleteById(id);
	        return "redirect:/all";
	    }
	    return "delete";
	}
}
