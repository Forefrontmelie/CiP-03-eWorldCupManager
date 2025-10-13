package com.verisure.practice.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.verisure.practice.domain.participant.HumanParticipant;
import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.application.participant.ParticipantService;

class ParticipantServiceTest {

	private ParticipantService controller;

	@BeforeEach
	void setUp() {
		controller = new ParticipantService();
	}


	@Test
	void createParticipant() {
		// When
		Participant participant = controller.createParticipant("John");

		// Then
		assertNotNull(participant);
		assertEquals("John", participant.getName());
		assertTrue(participant instanceof HumanParticipant);
	}

	@Test
	void createParticipantsFromList() {
	}

	@Test
	void addParticipant() {
	}
}