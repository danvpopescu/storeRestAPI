package com.robotstore.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robotstore.model.Robot;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Integer> {
  
//	Pet findById(Integer id);	
  Optional<Robot> findById(Integer id); 
  
}
