package com.bridgelabz.mentorsmodule.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.mentorsmodule.dto.MentorsDTO;
import com.bridgelabz.mentorsmodule.exception.CustomNotFoundException;
import com.bridgelabz.mentorsmodule.model.MentorsModel;
import com.bridgelabz.mentorsmodule.repository.MentorsRepository;
import com.bridgelabz.mentorsmodule.util.TokenUtil;

@Service
public class MentorsService implements IMentorsService {
	@Autowired
	MentorsRepository mentorsRepository;

	//	@Autowired
	//	TokenUtil tokenUtil;

	@Autowired
	MailService mailService;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public MentorsModel addMentor(MentorsDTO mentorsDTO, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8072/mentorsmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			MentorsModel model = new MentorsModel(mentorsDTO);
			mentorsRepository.save(model);
			String body = "Mentor added successfully with Mentor Id" + model.getId();
			String subject = "Mentor added Successfully";
			mailService.send(model.getEmail(), subject, body);
			return model;
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public MentorsModel updateMentor(MentorsDTO mentorsDTO, Long id, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8072/mentorsmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<MentorsModel>isMentorPresent = mentorsRepository.findById(id);
			if(isMentorPresent.isPresent()) {
				isMentorPresent.get().setEmployeeId(mentorsDTO.getEmployeeId());
				isMentorPresent.get().setFirstName(mentorsDTO.getFirstName());
				isMentorPresent.get().setLastName(mentorsDTO.getLastName());
				isMentorPresent.get().setMentorType(mentorsDTO.getMentorType());
				isMentorPresent.get().setMentorRole(mentorsDTO.getMentorRole());
				isMentorPresent.get().setMobileNumber(mentorsDTO.getMobileNumber());
				isMentorPresent.get().setEmail(mentorsDTO.getEmail());
				isMentorPresent.get().setExperienceYears(mentorsDTO.getExperienceYears());
				isMentorPresent.get().setPreferredTime(mentorsDTO.getPreferredTime());
				isMentorPresent.get().setStartDate(mentorsDTO.getStartDate());
				isMentorPresent.get().setStatus(mentorsDTO.getStatus());
				isMentorPresent.get().setMentorDescription(mentorsDTO.getMentorDescription());
				isMentorPresent.get().setProfileImageURL(mentorsDTO.getProfileImageURL());
				isMentorPresent.get().setCreatorUser(mentorsDTO.getCreatorUser());
				isMentorPresent.get().setSupervisorId(mentorsDTO.getSupervisorId());
				isMentorPresent.get().setUpdatedTimeStamp(mentorsDTO.getUpdatedTimeStamp().now());
				mentorsRepository.save(isMentorPresent.get());
				String body = "Mentor updated successfully with Mentor Id" + isMentorPresent.get().getId();
				String subject = "Mentor updated Successfully";
				mailService.send(isMentorPresent.get().getEmail(), subject, body);
				return isMentorPresent.get();
			}
			throw new CustomNotFoundException(400,"Mentor not present");
		}
		throw new CustomNotFoundException(400,"Token Invalid");
	}

	@Override
	public MentorsModel getMentorById(Long id, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8072/mentorsmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			return mentorsRepository.findById(id, token);
		}
		throw new CustomNotFoundException(400,"Token Invalid");
	}

	@Override
	public List<MentorsModel> getAllMentors(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8072/mentorsmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<MentorsModel> getAllMentors = mentorsRepository.findAll();
			if(getAllMentors.size()>0) {
				return getAllMentors;
			} else {
				throw new CustomNotFoundException(400, "Mentor not present");
			}
		}
		throw new CustomNotFoundException(400,"Token Invalid");
	}

	@Override
	public MentorsModel deleteMentor(Long id, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8072/mentorsmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<MentorsModel> isMentorPresent = mentorsRepository.findById(id);
			if(isMentorPresent.isPresent()) {
				mentorsRepository.delete(isMentorPresent.get());
				String body = "Mentor updated successfully with Mentor Id" + isMentorPresent.get().getId();
				String subject = "Mentor updated Successfully";
				mailService.send(isMentorPresent.get().getEmail(), subject, body);
				return isMentorPresent.get();
			}
			throw new CustomNotFoundException(400, "Mentor not found");
		}
		throw new CustomNotFoundException(400,"Token Invalid");
	}

	@Override
	public List<MentorsModel> getMentorsByRole(String role, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8072/mentorsmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<MentorsModel> isRolePresent = mentorsRepository.findByMentorRole(role);
			if (isRolePresent.size() > 0) {
				return isRolePresent;
			}
			throw new CustomNotFoundException(400, "Mentor not found");
		}
		throw new CustomNotFoundException(400,"Token Invalid");
	}

	@Override
	public long mentorsRoleCount(String mentorRole, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8072/mentorsmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<MentorsModel> isMentorRolePresent = mentorsRepository.findByMentorRole(mentorRole);
			if(isMentorRolePresent.size() > 0) {
				return isMentorRolePresent.stream().count();
			}
			throw new CustomNotFoundException(400, "no mentor present with that role");	
		}
		throw new CustomNotFoundException(400,"Token Invalid");
	}
}
