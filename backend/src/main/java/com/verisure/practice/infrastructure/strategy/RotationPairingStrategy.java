package com.verisure.practice.infrastructure.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.verisure.practice.application.tournament.strategy.PairingStrategy;
import com.verisure.practice.domain.participant.Participant;

public class RotationPairingStrategy implements PairingStrategy {

	@Override
	public ArrayList<Participant> rotateParticipants(ArrayList<Participant> participants, int roundNbr) {
		if (participants == null || participants.size() < 2) {
			return participants;
		}

		if (roundNbr < 1) {
			throw new IllegalArgumentException("The number of the round must be >= 1");
		}

		int n = participants.size();

		// Only participants index 1 - n-1 rotate
		int distance = (roundNbr - 1) % (n - 1); // 0 for round 1, 1 for round 2, etc.
		if (distance == 0) {
			return participants;
		}

		// Sublist of all participants index 1 - n-1. Participant at index 0 is fixed. Not a copy - changes original list.
		List<Participant> rotatable = participants.subList(1, n);
		Collections.rotate(rotatable, distance);

		return participants;  //Modified original list
	}

	@Override
	public int getOpponentIndex(int index, int n, int roundNbr) {
		if (n % 2 != 0 || index < 0 || index >= n || roundNbr < 1 || roundNbr >= n) {  // Jämnt antal deltagare, giltigt index och giltig runda
			throw new IllegalArgumentException("Invalid input parameters.");
		}

		// Calculate the opponent's index for round roundNbr
		if (index == 0) {
			return (n - 1 + roundNbr - 1) % (n - 1) + 1;
		} else {
			int opponentIndex = (index + roundNbr - 1) % (n - 1);
			//if (opponentIndex == 0) opponentIndex = n - 1;
			return opponentIndex == 0 ? n - 1 : opponentIndex;
		}
		//System.out.println(participants.get(index).getName() + " vs " + participants.get(opponentIndex).getName());
	}
}
