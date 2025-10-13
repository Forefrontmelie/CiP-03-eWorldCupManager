package com.verisure.practice.presentation.controller;

import java.util.ArrayList;

import com.verisure.practice.application.participant.ParticipantService;
import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.infrastructure.factory.HumanParticipantFactory;

public class ParticipantController {

	private ParticipantService participantService;


	public ParticipantController() {
		this.participantService = new ParticipantService(new HumanParticipantFactory());
	}

	public void createParticipants(ArrayList<String> names) {
		participantService.createParticipantsFromList(names);
	}

	public ArrayList<Participant> getParticipants() {
		return participantService.getParticipants();
	}




}
