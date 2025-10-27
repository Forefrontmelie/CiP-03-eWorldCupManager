package com.verisure.practice.presentation.controller;

import java.util.ArrayList;

import com.verisure.practice.application.participant.ParticipantService;
import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.infrastructure.factory.HumanParticipantFactory;

public class ParticipantController {


	private ParticipantService participantService;


	public ParticipantController() {
		this.participantService = new ParticipantService(new HumanParticipantFactory());   // TODO: Gör mer dynmaiskt - ta in olika sorters ParticipantFactory
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

	public void setParticipantService(ParticipantService participantService) {
		this.participantService = participantService;
	}

	public void removeParticipant(int index) {
		participantService.removeParticipant(index);
	}


}
