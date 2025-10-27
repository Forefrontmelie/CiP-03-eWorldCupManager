package com.verisure.practice.presentation.dto;

import java.util.List;

import com.verisure.practice.domain.MatchPair;

public class RoundDTO {
    private int round;
    private List<MatchPair> pairs;
    
    public RoundDTO(int round, List<MatchPair> pairs) {
        this.round = round;
        this.pairs = pairs;
    }
    

    public int getRound() {
        return round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public List<MatchPair> getPairs() {
        return pairs;
    }
    
    public void setPairs(List<MatchPair> pairs) {
        this.pairs = pairs;
    }

}