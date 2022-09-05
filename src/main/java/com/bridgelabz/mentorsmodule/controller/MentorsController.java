package com.bridgelabz.mentorsmodule.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.mentorsmodule.dto.MentorsDTO;
import com.bridgelabz.mentorsmodule.model.MentorsModel;
import com.bridgelabz.mentorsmodule.service.IMentorsService;
import com.bridgelabz.mentorsmodule.util.Response;
@RestController
@RequestMapping("/mentorsModule")
public class MentorsController {
	@Autowired
	IMentorsService mentorsService;
	/**
	 * Purpose:add mentor
	 * @Param token 
	 */
	@PostMapping("/addMentor")
	public ResponseEntity<Response> addMentor(@Valid @RequestBody MentorsDTO mentorsDTO, @RequestHeader String token) {
		MentorsModel mentorsModel = mentorsService.addMentor(mentorsDTO, token);
		Response response = new Response(200, "mentor inserted successfully", mentorsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	/**
	 * Purpose:update mentor by id
	 * @Param token and id
	 */
	@PutMapping("updateMentor/{id}")
	public ResponseEntity<Response> updateMentor(@Valid @RequestBody MentorsDTO mentorsDTO, @PathVariable Long id, @RequestHeader String token) {
		MentorsModel mentorsModel = mentorsService.updateMentor(mentorsDTO, id, token);
		Response response = new Response(200, "mentor updated successfully", mentorsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Purpose:get mentor by id
	 * @Param token and id
	 */
	@GetMapping("/getMentorData/{id}")
    public ResponseEntity<Response> getMentorById(@PathVariable Long id, @RequestHeader String token) {
		MentorsModel mentorsModel = mentorsService.getMentorById(id, token);
		Response response = new Response(200, "mentor by id fetch successfully", mentorsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
	/**
	 * Purpose:get all mentors
	 * @Param token 
	 */
	@GetMapping("/getAllMentors")
	public ResponseEntity<Response> getAllMentors(@RequestHeader String token) {
		List<MentorsModel> mentorsModel = mentorsService.getAllMentors(token);
		Response response = new Response(200, "all mentors fetch successfully", mentorsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Purpose:delete mentor by id
	 * @Param token and id
	 */
	@DeleteMapping("/deleteMentor/{id}")
	public ResponseEntity<Response> deleteMentor(@PathVariable Long id, @RequestHeader String token) {
		MentorsModel mentorsModel = mentorsService.deleteMentor(id, token);
		Response response = new Response(200, "mentor deleted successfully", mentorsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Purpose:get mentor by role
	 * @Param token and mentor role
	 */
	@GetMapping("/getMentorByRole")
	public ResponseEntity<Response> getMentorsByRole(@RequestParam String role, @RequestHeader String token) {
		List<MentorsModel> mentorsModel = mentorsService.getMentorsByRole(role, token);
		Response response = new Response(200, "mentor fetch by role successfully", mentorsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	/**
	 * Purpose:get mentors count by role
	 * @Param token and mentor role
	 */
	@GetMapping("/getMentorsCount")
	public ResponseEntity<Response> mentorsRoleCount(@RequestParam String mentorRole, @RequestHeader String token) {
		List<MentorsModel> mentorsModel = mentorsService.getMentorsByRole(mentorRole, token);
		Response response = new Response(200, "mentor fetch by role count successfully", mentorsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
