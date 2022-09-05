package com.bridgelabz.mentorsmodule.service;


import java.util.List;

import com.bridgelabz.mentorsmodule.dto.MentorsDTO;

import com.bridgelabz.mentorsmodule.model.MentorsModel;

public interface IMentorsService {
	
	MentorsModel addMentor(MentorsDTO mentorsDTO, String token);
	
	MentorsModel updateMentor(MentorsDTO mentorsDTO, Long id, String token);

	MentorsModel getMentorById(Long id, String token);

	List<MentorsModel> getAllMentors(String token);

	MentorsModel deleteMentor(Long id, String token);

	List<MentorsModel> getMentorsByRole(String role, String token);

	long mentorsRoleCount(String mentorRole, String token);
	
}
