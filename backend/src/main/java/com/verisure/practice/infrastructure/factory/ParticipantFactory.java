package com.verisure.practice.infrastructure.factory;

import com.verisure.practice.domain.participant.Participant;

public interface ParticipantFactory {

	Participant createParticipant(String name, int id);

}
