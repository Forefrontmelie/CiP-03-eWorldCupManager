package com.verisure.practice.presentation.view;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.verisure.practice.EWorldCupManagerApplication;
import com.verisure.practice.presentation.controller.ParticipantController;
import com.verisure.practice.presentation.controller.TournamentController;

public class Main {
    public static void main(String[] args) {
        // Bootstrap Spring (non-web mode) for CLI style invocation
        ApplicationContext ctx = new SpringApplicationBuilder(EWorldCupManagerApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

        ParticipantController participantController = ctx.getBean(ParticipantController.class);
        TournamentController tournamentController = ctx.getBean(TournamentController.class);

        List<String> names = Arrays.asList(
            "Alice","Bob","Charlie","Diana","Eve","Fiona","George","Hanna","Isaac","Julia",
            "Keira","Laura","Michael","Nina","Oscar","Paula","Quentin","Rachel","Samuel","Tina"
        );
        participantController.createParticipantsFromList(new ArrayList<>(names));

        System.out.println();
        System.out.print("Max number of rounds for 12 participants: ");
        System.out.println(tournamentController.getMaxNumberOfRounds(12));
        System.out.println();

        System.out.print("Remaining unique pairs after 18 rounds with 20 participants: ");
        System.out.println(tournamentController.getRemainingUniquePairs(20, 18));
        System.out.println();

        System.out.println("Round 4 pairs DTO: " + tournamentController.getPairsForSpecificRound(4));
    }
}