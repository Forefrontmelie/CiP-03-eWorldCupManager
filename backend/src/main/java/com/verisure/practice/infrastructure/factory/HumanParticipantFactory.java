package com.verisure.practice.infrastructure.factory;

import com.verisure.practice.domain.participant.HumanParticipant;
import com.verisure.practice.domain.participant.Participant;

public class HumanParticipantFactory implements ParticipantFactory {


	@Override
	public Participant createParticipant(String name, int id) {
		return new HumanParticipant(name, id);
	}
}
