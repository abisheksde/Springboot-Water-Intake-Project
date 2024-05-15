package com.example.dwi.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dwi.Model.Water;


public interface WaterRepository extends JpaRepository<Water, Integer> {

	@Query("SELECT p FROM Water p WHERE p.email = :email")
	Iterable<Water> findByUserEmail(@Param("email") String userEmail);



}