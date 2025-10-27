package com.verisure.practice.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import com.verisure.practice.application.participant.ParticipantService;
import com.verisure.practice.application.tournament.TournamentService;
import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.domain.Match;

public class TournamentController {

	private ParticipantController participantController;
	private ParticipantService participantService;
	private TournamentService tournamentService;


	public TournamentController(){
		this.participantService = new ParticipantService();
		this.participantController = new ParticipantController();
		this.tournamentService = new TournamentService(participantController.getParticipantService());
	}

	public TournamentController(ParticipantController participantController){
		this.participantController = participantController;
		this.tournamentService = new TournamentService(participantController.getParticipantService());
	}

	public TournamentController(ArrayList<String> strParticipants) {
		if (strParticipants.size() % 2 != 0) {
			throw new IllegalArgumentException("The number of participants must be even.");
		}

		participantController.createParticipantsFromList(strParticipants);

		this.tournamentService = new TournamentService(participantController.getParticipantService());
		//this.tournamentService = new TournamentService(participantService.getParticipants(), new RotationPairingStrategy());
	}


	public ArrayList<Participant> getPairsForSpecificRound(int roundNbr) {
		ArrayList<Participant> rotated = tournamentService.getPairsForRound(roundNbr);
		printThisRound(rotated, roundNbr);

		return rotated;
	}


	public int getMaxNumberOfRounds(int n) {
		return tournamentService.getMaxNumberOfRounds(n);   //TODO: Inte skicka in n, utan returnera baserat på antal deltagare i turneringen?
	}

	public int getRemainingUniquePairs(int n, int roundNbr) {
		return tournamentService.getRemainingUniquePairsAfterRounds(n, roundNbr);
	}


	public int getOpponentIndex(int index, int roundNbr) {
		int opponentIndex = tournamentService.getOpponentIndex(index, roundNbr);

		return opponentIndex;
	}

	public Participant getOpponentParticipant(int index, int roundNbr) {
		int opponentIndex = tournamentService.getOpponentIndex(index, roundNbr);
		Participant opponent = tournamentService.getParticipant(opponentIndex);

		return opponent;
	}

	public List<Match> getPlayerSchedule(int playerIndex) {
        return tournamentService.getPlayerSchedule(playerIndex);
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
