package com.robotstore.robot;

import static junit.framework.Assert.assertNull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.robotstore.model.Robot;
import com.robotstore.repo.RobotRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotstoreApplicationTests {

    @Autowired
    private TestEntityManager entityManager;
    
	@Autowired
    private MockMvc mvc;
 
	@Autowired
	private RobotRepository robotRepo;
 
    // write test cases here	
	@Test
	public void whenFindByIdRobot_thenReturnRobot() {
	    // given
	    Robot robot = new Robot();
	    robot.setIdRobot(1);
	    robot.setRobotName("toto");
	    
	    entityManager.persist(robot);
	    entityManager.flush();
	 
	    // when
	    Optional<Robot> optFound = robotRepo.findById(robot.getIdRobot());
	 
	    if(optFound.isPresent()) {
	    	assertThat(robot.getRobotName()).isEqualTo("toto");	
	    }

	    
	}	

	@Test
	public void givenRobots_whenGetRobots_thenReturnJsonArray()
	  throws Exception {
	     
	    Robot robot = new Robot();
	    robot.setRobotName("toto");
	 
	    List<Robot> allRobots = Arrays.asList(robot);
	 
	    when(robotRepo.findAll()).thenReturn(allRobots);
	 
	    mvc.perform(get("/api/employees")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      //.andExpect(jsonPath("$", hasSize(1)))
	      .andExpect(jsonPath("$[0].RobotName", is(robot.getRobotName())));
	}	
	

}


