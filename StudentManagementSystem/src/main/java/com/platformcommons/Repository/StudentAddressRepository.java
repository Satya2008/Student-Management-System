package com.platformcommons.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platformcommons.Entities.StudentAddress;

public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long> {
    
}