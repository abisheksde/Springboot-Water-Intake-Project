package com.example.dwi.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dwi.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String username);
}