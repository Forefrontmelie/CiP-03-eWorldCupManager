package com.verisure.practice.presentation.controller;

import java.util.ArrayList;

import com.verisure.practice.application.participant.ParticipantService;
import com.verisure.practice.application.tournament.TournamentService;
import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.infrastructure.factory.HumanParticipantFactory;
import com.verisure.practice.infrastructure.strategy.RotationPairingStrategy;

public class TournamentController {

	private ParticipantService participantService;
	private TournamentService tournamentService;


	public TournamentController(ArrayList<String> strParticipants) {
		if (strParticipants.size() % 2 != 0) {
			throw new IllegalArgumentException("The number of participants must be even.");
		}

		this.participantService = new ParticipantService(new HumanParticipantFactory());
		participantService.createParticipantsFromList(strParticipants);

		this.tournamentService = new TournamentService(participantService.getParticipants(), new RotationPairingStrategy());
	}


	public ArrayList<Participant> getPairsForSpecificRound(int roundNbr) {
		ArrayList<Participant> rotated = tournamentService.getPairsForRound(roundNbr);
		printThisRound(rotated, roundNbr);

		return rotated;
	}


	public int getMaxNumberOfRounds(int n) {
		return tournamentService.getMaxNumberOfRounds(n);   //TODO: Inte skicka in n, utan returnera baserat på antal deltagare i turneringen?
	}

	public int getRemainingUniquePairsAfterRounds(int n, int roundNbr) {
		return tournamentService.getRemainingUniquePairsAfterRounds(n, roundNbr);
	}


	public void getRoundOpponent(int n, int index, int roundNbr) {
		int opponentIndex = tournamentService.getOpponentIndex(index, roundNbr);
		Participant participant = tournamentService.getParticipant(index);
		Participant opponent = tournamentService.getParticipant(opponentIndex);

		System.out.print("Opponent for participant " + index + "." + participant.getName() + ", in round " + roundNbr + " with " + n + " participants: " + opponentIndex + "." + opponent.getName() + ".");


	}

	public void printThisRound(ArrayList<Participant> participants, int roundNbr) {
		System.out.println("The pairs for round " + roundNbr + ":");
		for (int i = 0; i < participants.size() / 2; i++) {
			System.out.println(participants.get(i).getName() + " vs " + participants.get(participants.size() - 1 - i).getName());
		}
	}

	public void printMessage(String message) {
		System.out.println(message);
	}


}
