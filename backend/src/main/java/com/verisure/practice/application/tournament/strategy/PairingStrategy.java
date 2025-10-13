package com.verisure.practice.application.tournament.strategy;

import java.util.ArrayList;

import com.verisure.practice.domain.participant.Participant;

public interface PairingStrategy {

	ArrayList<Participant> rotateParticipants(ArrayList<Participant> participants, int roundNbr);

	int getOpponentIndex(int participantIndex, int n, int roundNbr);

}
