package com.robotstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//	`idRobot` int (10) not null,
//	`category` varchar(50) not null,
//	`robotName` varchar(50) not null,
//	`photoUrls` varchar(100),
//	`tags` varchar(50),
//	`robotStatus` varchar(50) not null,
	
	@Entity
	@Table(name = "Robot")
	public class Robot {
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRobot")
	private Integer idRobot;
	  
	@Column(name = "category", columnDefinition="VARCHAR(50)", unique=true, nullable=false, length = 50)
	private String category;
	  
	@Column(name = "robotName", columnDefinition="VARCHAR(50)", unique=true, nullable=false, length = 50)
	private String robotName;

	@Column(name = "photoUrls", columnDefinition="VARCHAR(100)", unique=true, nullable=true, length = 100)
	private String photoUrls;

	@Column(name = "tags", columnDefinition="VARCHAR(50)", unique=true, nullable=true, length = 50)
	private String tags;

	@Column(name = "robotStatus", columnDefinition="VARCHAR(50)", unique=true, nullable=false, length = 50)
	private String robotStatus;
	  

	public Robot() {
	}

	public Robot(Robot robot) {
		this.category = robot.category;
		this.robotName = robot.robotName;
	    this.photoUrls = robot.photoUrls;
	    this.tags = robot.tags;
	    this.robotStatus = robot.robotStatus;
		}

	public Integer getIdRobot() {
		return idRobot;
	}

	public void setIdRobot(Integer idRobot) {
		this.idRobot = idRobot;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRobotName() {
		return robotName;
	}

	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}

	public String getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(String photoUrls) {
		this.photoUrls = photoUrls;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getRobotStatus() {
		return robotStatus;
	}

	public void setRobotStatus(String robotStatus) {
		this.robotStatus = robotStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((idRobot == null) ? 0 : idRobot.hashCode());
		result = prime * result + ((robotName == null) ? 0 : robotName.hashCode());
		result = prime * result + ((robotStatus == null) ? 0 : robotStatus.hashCode());
		result = prime * result + ((photoUrls == null) ? 0 : photoUrls.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Robot other = (Robot) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (idRobot == null) {
			if (other.idRobot != null)
				return false;
		} else if (!idRobot.equals(other.idRobot))
			return false;
		if (robotName == null) {
			if (other.robotName != null)
				return false;
		} else if (!robotName.equals(other.robotName))
			return false;
		if (robotStatus == null) {
			if (other.robotStatus != null)
				return false;
		} else if (!robotStatus.equals(other.robotStatus))
			return false;
		if (photoUrls == null) {
			if (other.photoUrls != null)
				return false;
		} else if (!photoUrls.equals(other.photoUrls))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Robot [idRobot=" + idRobot + ", category=" + category + ", robotName=" + robotName + ", photoUrls=" + photoUrls
				+ ", tags=" + tags + ", robotStatus=" + robotStatus + "]";
	}
	  
	public String toJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}	

}	
