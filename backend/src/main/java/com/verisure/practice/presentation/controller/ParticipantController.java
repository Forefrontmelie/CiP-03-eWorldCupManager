package com.verisure.practice.presentation.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.verisure.practice.application.participant.ParticipantService;
import com.verisure.practice.domain.participant.Participant;

@Component
public class ParticipantController {

	private final ParticipantService participantService;

	@Autowired
	public ParticipantController(ParticipantService participantService) {
		this.participantService = participantService;
	}

	public void createParticipant(String name) {
		participantService.createParticipant(name);
	}

	public void createParticipantsFromList(ArrayList<String> names) {
		participantService.createParticipantsFromList(names);
	}

	public ArrayList<Participant> getParticipants() {
		return participantService.getParticipants();
	}

	public ParticipantService getParticipantService() {
		return participantService;
	}

	public void removeParticipant(int index) {
		participantService.removeParticipant(index);
	}
}
