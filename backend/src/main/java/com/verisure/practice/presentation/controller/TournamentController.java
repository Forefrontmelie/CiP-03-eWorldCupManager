package com.verisure.practice.presentation.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.verisure.practice.application.tournament.TournamentService;
import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.presentation.dto.PlayerScheduleDTO;
import com.verisure.practice.presentation.dto.RoundDTO;

@Component
public class TournamentController {

	private final ParticipantController participantController;
	private final TournamentService tournamentService;

	@Autowired
	public TournamentController(ParticipantController participantController, TournamentService tournamentService) {
		this.participantController = participantController;
		this.tournamentService = tournamentService;
	}

	public int getMaxNumberOfRounds(int n) {
		return tournamentService.getMaxNumberOfRounds(n);   //TODO: Inte skicka in n, utan returnera baserat på antal deltagare i turneringen?
	}

	public int getRemainingUniquePairs(int n, int roundNbr) {
		return tournamentService.getRemainingUniquePairsAfterRounds(n, roundNbr);
	}

	public int getOpponentIndex(int index, int roundNbr) {
		return tournamentService.getOpponentIndex(index, roundNbr);
	}

	public Participant getOpponentParticipant(int index, int roundNbr) {
		int opponentIndex = tournamentService.getOpponentIndex(index, roundNbr);
		return tournamentService.getParticipant(opponentIndex);
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

	public RoundDTO getPairsForSpecificRound(int round) {
		return tournamentService.getPairsForSpecificRound(round);
	}

	public PlayerScheduleDTO getPlayerSchedule(int playerIndex) {
		return tournamentService.getPlayerSchedule(playerIndex);
	}
}