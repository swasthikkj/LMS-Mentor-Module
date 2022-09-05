package com.bridgelabz.mentorsmodule.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
@Data
public class MentorsDTO {
	@Pattern(regexp = "^[a-zA-Z0-9\\s]{2,}$", message = "Employee id is Invalid")
	private String employeeId;
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "First name Invalid")
	private String firstName;
	@Pattern(regexp = "^[a-z]{1}[a-zA-Z\\s]{2,}$", message = "last name Invalid")
	private String lastName;
	@Pattern(regexp = "^[a-zA-Z\\s]{2,}$", message = "Mentor type is Invalid")
	private String mentorType;
	@Pattern(regexp = "^[a-zA-Z\\s]{4,}$", message = "Mentor role is Invalid")
	private String mentorRole;
	@Pattern(regexp = "^[9,8,7,6]{1}[0-9]{9}$", message = "mobile number invalid")
	private String mobileNumber;
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "email is invalid")
	private String email;
	@Pattern(regexp = "^[0-9]{1,2}$", message = "enter valid detalis")
	private String experienceYears;
	@NotBlank(message = "preffered time cannot be empty")
	private String preferredTime;
	@NotBlank(message = "start date cannot be empty")
	private LocalDate startDate;
	@Pattern(regexp = "^[a-zA-Z\\s]{1,}$", message = "Status is Invalid")
	private String status;
	@Pattern(regexp = "^[a-zA-Z\\s]{1,}$", message = "Mentor description is Invalid")
	private String mentorDescription;
	private String profileImageURL;
	@Pattern(regexp = "^[0-9]{1,}$", message = "enter valid creater user")
	private int creatorUser;
	@Pattern(regexp = "^[0-9]{1,}$", message = "Supervisor id is invalid")
	private long supervisorId;
	private LocalDateTime createdTimeStamp;
	private LocalDateTime updatedTimeStamp;
}
