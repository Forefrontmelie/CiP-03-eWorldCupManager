package com.verisure.practice.application.participant;

import java.util.ArrayList;

import com.verisure.practice.infrastructure.factory.HumanParticipantFactory;
import com.verisure.practice.infrastructure.factory.ParticipantFactory;
import com.verisure.practice.domain.participant.Participant;

public class ParticipantService {

	private ParticipantFactory factory;
	private ArrayList<Participant> participants;
	private int nextId;


	public ParticipantService() {
		this.factory = new HumanParticipantFactory();
		this.participants = new ArrayList<>();
		this.nextId = 0;
	}

	public ParticipantService(ParticipantFactory factory) {
		this.participants = new ArrayList<>();
		this.nextId = 0;
		this.factory = factory;
	}


	public Participant createParticipant(String name) {    // TODO: Lägg också till i databasen!
		Participant participant = factory.createParticipant(name, nextId);
		participants.add(participant);
		nextId++;

		return participant;
	}


	public void createParticipantsFromList(ArrayList<String> names) {
		if (names.size() % 2 != 0) {
			throw new IllegalArgumentException("The number of participants must be even.");
		}
		for (String name : names) {
			Participant p = createParticipant(name);
		}
	}

	public void removeParticipant(int index) {     // TODO: Ta också bort från databasen!
		participants.remove(index);
	}


	public ArrayList<Participant> getParticipants() {
		return new ArrayList<>(participants);
	}


	public void setFactory(ParticipantFactory factory) {
		this.factory = factory;
	}


}
