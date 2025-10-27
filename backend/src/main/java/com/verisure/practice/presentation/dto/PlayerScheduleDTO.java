package com.verisure.practice.presentation.dto;

import java.util.List;
import com.verisure.practice.domain.MatchPair;

public class PlayerScheduleDTO {
    private String player;
    private int n;
    private List<MatchPair> schedule;
    
    public PlayerScheduleDTO(String player, int n, List<MatchPair> schedule) {
        this.player = player;
        this.n = n;
        this.schedule = schedule;
    }
    
    public String getPlayer() {
        return player;
    }
    
    public int getN() {
        return n;
    }
    
    public List<MatchPair> getSchedule() {
        return schedule;
    }
}