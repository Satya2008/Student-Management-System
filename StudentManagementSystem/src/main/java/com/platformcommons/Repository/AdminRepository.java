package com.platformcommons.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platformcommons.Entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Optional<Admin> findByEmail(String Email);
    
}