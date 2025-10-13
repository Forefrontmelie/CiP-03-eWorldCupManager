package com.verisure.practice.presentation.view;

import java.util.ArrayList;

import com.verisure.practice.presentation.controller.TournamentController;


public class Main {
	public static void main(String[] args) {

		ArrayList<String> strParticipants = new ArrayList<>();
		strParticipants.add("Alice");
		strParticipants.add("Bob");
		strParticipants.add("Charlie");
		strParticipants.add("Diana");
		strParticipants.add("Eve");
		strParticipants.add("Fiona");
		strParticipants.add("George");
		strParticipants.add("Hanna");
		strParticipants.add("Isaac");
		strParticipants.add("Julia");
		strParticipants.add("Keira");
		strParticipants.add("Laura");
		strParticipants.add("Michael");
		strParticipants.add("Nina");
		strParticipants.add("Oscar");
		strParticipants.add("Paula");
		strParticipants.add("Quentin");
		strParticipants.add("Rachel");
		strParticipants.add("Samuel");
		strParticipants.add("Tina");


		TournamentController tc = new TournamentController(strParticipants);

		tc.getPairsForSpecificRound(4);
		System.out.println();

		System.out.print("Max number of rounds for 12 participants: ");
		System.out.println(tc.getMaxNumberOfRounds(12));
		System.out.println();

		System.out.print("Remaining unique pairs after 18 rounds with 20 participants: ");
		System.out.println(tc.getRemainingUniquePairsAfterRounds(20, 18));
		System.out.println();

		tc.getRoundOpponent(10, 4, 3);


	}
}