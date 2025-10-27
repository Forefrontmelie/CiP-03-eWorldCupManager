package com.verisure.practice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.verisure.practice.domain.participant.Participant;

public class MatchPair {
    

    private int round;
    private Participant player1;
    private Participant player2;
    
    public MatchPair(int round, Participant player1, Participant player2) {
        this.round = round;
        this.player1 = player1;
        this.player2 = player2;
    }
    
    
    public int getRound() {
        return round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }

    @JsonIgnore
    public Participant getPlayer1() {
        return player1;
    }
    
    public void setPlayer1(Participant player1) {
        this.player1 = player1;
    }
    
    @JsonIgnore
    public Participant getPlayer2() {
        return player2;
    }
    
    public void setPlayer2(Participant player2) {
        this.player2 = player2;
    }

    @JsonProperty("opponent")
    public String getOpponent() {
        return player2.getName();
    }
}
