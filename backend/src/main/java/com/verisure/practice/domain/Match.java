package com.verisure.practice.domain.participant;


public class Match {
    private int round;
    private Participant player1;
    private Participant player2;
    
    public Match(int round, Participant player1, Participant player2) {
        this.round = round;
        this.player1 = player1;
        this.player2 = player2;
    }
    
    // Getters and setters for JSON serialization
    public int getRound() {
        return round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public Participant getPlayer1() {
        return player1;
    }
    
    public void setPlayer1(Participant player1) {
        this.player1 = player1;
    }
    
    public Participant getPlayer2() {
        return player2;
    }
    
    public void setPlayer2(Participant player2) {
        this.player2 = player2;
    }
}