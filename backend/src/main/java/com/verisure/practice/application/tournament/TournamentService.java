package com.verisure.practice.application.tournament;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.verisure.practice.application.participant.ParticipantService;
import com.verisure.practice.application.tournament.strategy.PairingStrategy;
import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.infrastructure.strategy.RotationPairingStrategy;
import com.verisure.practice.presentation.dto.PlayerScheduleDTO;
import com.verisure.practice.presentation.dto.RoundDTO;
import com.verisure.practice.domain.MatchPair;

@Service
public class TournamentService {

	private final ParticipantService participantService;
	private final PairingStrategy pairingStrategy;

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


	public RoundDTO getPairsForSpecificRound(int round) {
		ArrayList<Participant> rotatedParticipants = getPairsForRound(round);
		List<MatchPair> pairs = new ArrayList<>();
		for (int i = 0; i < rotatedParticipants.size() / 2; i++) {
			Participant player1 = rotatedParticipants.get(i);
			Participant player2 = rotatedParticipants.get(rotatedParticipants.size() - 1 - i);
			pairs.add(new MatchPair(round, player1, player2));
		}
		return new RoundDTO(round, pairs);
	}



	public PlayerScheduleDTO getPlayerSchedule(int playerIndex) {
		List<Participant> participants = participantService.getParticipants();
		String playerName = participants.get(playerIndex).getName();
		int totalRounds = participants.size();
		List<MatchPair> schedule = new ArrayList<>();
			
		for (int round = 1; round <= totalRounds; round++) {
			int opponentIndex = getOpponentIndex(playerIndex, round);
			Participant player = participants.get(playerIndex);
			Participant opponent = participants.get(opponentIndex);
				
			schedule.add(new MatchPair(round, player, opponent));
		}

		return new PlayerScheduleDTO(playerName, totalRounds, schedule);
	}


}
