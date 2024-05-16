package com.example.dwi.Repository;


import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.dwi.Model.Water;

@Repository
public interface WaterRepository extends JpaRepository<Water, Integer> {

	@Query("SELECT p FROM Water p WHERE p.email = :email")
	Iterable<Water> findByUserEmail(@Param("email") String userEmail);

	@Query(value = "SELECT * FROM Water p WHERE p.email = :email AND DATE(p.created_at) = :date", nativeQuery = true)
	List<Water> findByUserEmailAndDate(@Param("email") String userEmail, @Param("date") LocalDate date);

	/*
	@Query("SELECT p FROM Product p WHERE p.date BETWEEN :startDate AND :endDate AND p.userEmail = :email")
	Iterable<Water> findByDateRangeAndEmail(@Param("email") String email, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,);

	 */
	
	//Page findAll(Pageable pageable);

}