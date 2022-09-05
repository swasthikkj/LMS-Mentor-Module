package com.bridgelabz.mentorsmodule.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bridgelabz.mentorsmodule.dto.MentorsDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mentor")
@Data
@NoArgsConstructor
public class MentorsModel {
	@Id
	@GenericGenerator(name = "mentor", strategy = "increment")
	@GeneratedValue(generator = "mentor")
	private Long id;
	private String employeeId;
	private String firstName;
	private String lastName;
	private String mentorType;
	private String mentorRole;
	private String mobileNumber;
	private String email;
	private String experienceYears;
	private String preferredTime;
	private LocalDate startDate;
	private String status;
	private String mentorDescription;
	private String profileImageURL;
	private int creatorUser;
	private long supervisorId;
	private LocalDateTime createdTimeStamp;
	private LocalDateTime updatedTimeStamp;

	public MentorsModel(MentorsDTO mentorsDTO) {
		this.employeeId = mentorsDTO.getEmployeeId();
		this.firstName = mentorsDTO.getFirstName();
		this.lastName = mentorsDTO.getLastName();
		this.mentorType = mentorsDTO.getMentorType();
		this.mentorRole = mentorsDTO.getMentorRole();
		this.mobileNumber = mentorsDTO.getMobileNumber();
		this.email = mentorsDTO.getEmail();
		this.experienceYears = mentorsDTO.getExperienceYears();
		this.preferredTime = mentorsDTO.getPreferredTime();
		this.startDate = mentorsDTO.getStartDate();
		this.status = mentorsDTO.getStatus();
		this.mentorDescription = mentorsDTO.getMentorDescription();
		this.profileImageURL = mentorsDTO.getProfileImageURL();
		this.creatorUser = mentorsDTO.getCreatorUser();
		this.supervisorId = mentorsDTO.getSupervisorId();
		this.createdTimeStamp = mentorsDTO.getCreatedTimeStamp().now();
		this.updatedTimeStamp = mentorsDTO.getUpdatedTimeStamp().now();
	}
}
