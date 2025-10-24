package com.verisure.practice.application.tournament;

import java.util.ArrayList;

import com.verisure.practice.application.participant.ParticipantService;
import com.verisure.practice.application.tournament.strategy.PairingStrategy;
import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.infrastructure.strategy.RotationPairingStrategy;

public class TournamentService {

	private ParticipantService  participantService;
	private PairingStrategy pairingStrategy;

	public TournamentService(ParticipantService participantService) {
		this.participantService = participantService;
		this.pairingStrategy = new RotationPairingStrategy();
	}


	/*public TournamentService(ArrayList<Participant> participants, PairingStrategy pairingStrategy) {
		if (participants == null || participants.size() < 2 || participants.size() % 2 != 0) {
			throw new IllegalArgumentException("Participants list must contain at least two participants and be even.");
		}
		//this.participants = participants;
		this.pairingStrategy = pairingStrategy;
	} */

	public ArrayList<Participant> getPairsForRound(int roundNbr) {
		if (roundNbr < 1 || roundNbr > getMaxNumberOfRounds(participantService.getParticipants().size())) {
			throw new IllegalArgumentException("Round number must be between 1 and " + getMaxNumberOfRounds(participantService.getParticipants().size()));
		}

		return pairingStrategy.rotateParticipants(new ArrayList<>(participantService.getParticipants()), roundNbr);
	}



	public int getMaxNumberOfRounds(int n) {
		//return participants.size() - 1;
		return n - 1;
	}

	public int getRemainingUniquePairsAfterRounds(int n, int roundNbr) {
		int totalUniquePairs = n * (n - 1) / 2;
		int uniquePairsPerRound = n / 2;
		int totalPlayedPairs = roundNbr * uniquePairsPerRound;

		return totalUniquePairs - totalPlayedPairs;
	}

	public int getOpponentIndex(int participantIndex, int roundNbr) {
		return pairingStrategy.getOpponentIndex(participantIndex, participantService.getParticipants().size(), roundNbr);
	}

	public Participant getParticipant(int index) {
		return participantService.getParticipants().get(index);
	}
}
