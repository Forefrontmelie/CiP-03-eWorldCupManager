package com.verisure.practice.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.presentation.controller.TournamentController;

import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class TournamentControllerTest {

	private TournamentController controller;
	private ArrayList<String> participantNames;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() {
		participantNames = new ArrayList<>();
		participantNames.add("Alice");
		participantNames.add("Bob");
		participantNames.add("Charlie");
		participantNames.add("Diana");
		participantNames.add("Eve");
		participantNames.add("Fred");
		controller = new TournamentController(participantNames);
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	@DisplayName("Should set up tournament with even number of participants")
	void setUpTournamentWithEvenParticipants() {
		// When
		controller.setUpTournament(participantNames);

		// Then - no exception should be thrown
		assertDoesNotThrow(() -> controller.setUpTournament(participantNames));
	}

	@Test
	@DisplayName("Should throw exception for odd number of participants")
	void setUpTournamentWithOddParticipants() {
		// Given
		ArrayList<String> oddParticipants = new ArrayList<>();
		oddParticipants.add("Alice");
		oddParticipants.add("Bob");
		oddParticipants.add("Charlie");

		// When & Then
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> controller.setUpTournament(oddParticipants)
		);
		assertEquals("The number of participants must be even.", exception.getMessage());
	}

	@Test
	@DisplayName("Should rotate participants correctly")
	void rotateParticipants() {
	}

	@Test
	@DisplayName("Should throw exception for round number less than 1")
	void getPairsForRoundLessThanOne() {
		// Given
		controller.setUpTournament(participantNames);

		// When & Then
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> controller.getPairsForSpecificRound(0)
		);
		assertEquals("Round number must be between 1 and 5", exception.getMessage());
	}

	@Test
	@DisplayName("Should throw exception for round number greater than max rounds")
	void getPairsForRoundGreaterThanMax() {
		// Given
		controller.setUpTournament(participantNames);

		// When & Then
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> controller.getPairsForSpecificRound(6)
		);
		assertEquals("Round number must be between 1 and 5", exception.getMessage());
	}

	@Test
	@DisplayName("Should get pairs for valid round number")
	void getPairsForValidRound() {
		// Given
		controller.setUpTournament(participantNames);

		// When & Then
		assertDoesNotThrow(() -> controller.getPairsForSpecificRound(3));
	}

	@Test
	@DisplayName("Should print correct round header")
	void printThisRoundHeader() {

	}

	@Test
	@DisplayName("Should print correct number of pairs")
	void printCorrectNumberOfPairs() {

	}

	@Test
	@DisplayName("Should handle minimum participants (2)")
	void handleMinimumParticipants() {
		// Given
		ArrayList<String> twoParticipants = new ArrayList<>();
		twoParticipants.add("Alice");
		twoParticipants.add("Bob");

		// When & Then
		assertDoesNotThrow(() -> controller.setUpTournament(twoParticipants));
		assertDoesNotThrow(() -> controller.getPairsForSpecificRound(1));
	}

	@Test
	@DisplayName("Should handle maximum valid round for 6 participants")
	void handleMaximumValidRound() {
		// Given
		controller.setUpTournament(participantNames);

		// When & Then
		assertDoesNotThrow(() -> controller.getPairsForSpecificRound(5));
	}



	// Helper class for testing rotation
	private static class TestParticipant extends Participant {

		public TestParticipant(String name) {
			super(name);
		}

	}
}
