package com.robotstore.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.robotstore.model.Robot;
import com.robotstore.repo.RobotRepository;
import com.robotstore.service.RobotService;

@RestController
public class RobotController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RobotRepository robotRepo;
	//private RobotService robotService;
	
	/**
	 * @author Daniel
	 * method that return the list of robots
	 * @return ResponseEntity
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/robots", method = RequestMethod.GET)
	public ResponseEntity<List<Robot>> getRobots() {
		logger.debug("getRobots: " + LocalDateTime.now());
		System.out.println("getRobots");
		if (robotRepo.findAll().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Robot>>(robotRepo.findAll(), HttpStatus.OK);	
		}
	}

	/**
	 * @author Daniel
	 * method that return the robot coresponding to the robotId
	 * @return ResponseEntity
	 */
	@CrossOrigin(origins = "http://localhost:4200")	
	@RequestMapping(value = "/robot/{robotId}", method = RequestMethod.GET)
	public ResponseEntity<?> getRobot(@PathVariable Integer robotId) {
		logger.debug("getRobot(robotId): " + LocalDateTime.now());
		System.out.println("getRobot robotId: " + robotId);
		Optional<Robot> optRobot = robotRepo.findById(robotId);
		System.out.println("optRobot: " + optRobot);
		Robot robot = null;
		if(optRobot.isPresent()) {
			robot = optRobot.get();
			return new ResponseEntity<Robot>(robot ,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @author Daniel
	 * method that delete a robot corresponding to the robotId
	 * @return ResponseEntity
	 */
	@CrossOrigin(origins = "http://localhost:4200")	
	@RequestMapping(value = "/robot/{robotId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRobot(@PathVariable Integer robotId) {
		logger.debug("deleteRobot(robotId): " + LocalDateTime.now());
		System.out.println("deleteRobot");
		Optional<Robot> optRobot = robotRepo.findById(robotId);
		Robot robot = null;
		if(optRobot.isPresent()) {
			robot = optRobot.get();
			robotRepo.delete(robot);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @author Daniel
	 * method that add a robot
	 * @return ResponseEntity
	 */
	@CrossOrigin(origins = "http://localhost:4200")	
	@RequestMapping(value = "/robot", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> addRobot(@RequestBody Robot robot) {
		logger.debug("addRobot(robot): " + LocalDateTime.now());
		System.out.println("addRobot");
		System.out.println("robot: " + robot.toString());
		robot.setIdRobot(null);
		robotRepo.save(robot);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * @author Daniel
	 * method that update a robot
	 * @return ResponseEntity
	 */
	@CrossOrigin(origins = "http://localhost:4200")	
	@RequestMapping(value = "/robot/{robotId}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> updateRobot(@PathVariable Integer idRobot, @RequestBody Robot robot) {
		logger.debug("updateRobot(robot): " + LocalDateTime.now());
		System.out.println("updateRobot");
		System.out.println("robot: " + robot.toString());
		Optional<Robot> optRobot = robotRepo.findById(idRobot);
		if(optRobot.isPresent()) {
			robot.setIdRobot(idRobot);
			robotRepo.save(robot);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
