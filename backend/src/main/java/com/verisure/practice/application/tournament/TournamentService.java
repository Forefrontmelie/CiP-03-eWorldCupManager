package com.verisure.practice.application.tournament;

import java.util.ArrayList;

import com.verisure.practice.application.tournament.strategy.PairingStrategy;
import com.verisure.practice.domain.participant.Participant;

public class TournamentService {

	private ArrayList<Participant> participants;
	private PairingStrategy pairingStrategy;

	public TournamentService(ArrayList<Participant> participants, PairingStrategy pairingStrategy) {
		if (participants == null || participants.size() < 2 || participants.size() % 2 != 0) {
			throw new IllegalArgumentException("Participants list must contain at least two participants and be even.");
		}
		this.participants = participants;
		this.pairingStrategy = pairingStrategy;
	}

	public ArrayList<Participant> getPairsForRound(int roundNbr) {
		if (roundNbr < 1 || roundNbr > getMaxNumberOfRounds(participants.size())) {
			throw new IllegalArgumentException("Round number must be between 1 and " + getMaxNumberOfRounds(participants.size()));
		}

		return pairingStrategy.rotateParticipants(new ArrayList<>(participants), roundNbr);
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
		return pairingStrategy.getOpponentIndex(participantIndex, participants.size(), roundNbr);
	}

	public Participant getParticipant(int index) {
		return participants.get(index);
	}
}
