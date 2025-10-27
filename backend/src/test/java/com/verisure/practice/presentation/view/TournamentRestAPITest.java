import com.fasterxml.jackson.databind.ObjectMapper;
import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.domain.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

package com.verisure.practice.presentation.view;



@WebMvcTest(TournamentRestAPI.class)
public class TournamentRestAPITest {

    private MockMvc mockMvc;
    private TournamentRestAPI tournamentRestAPI;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        tournamentRestAPI = new TournamentRestAPI();
        mockMvc = MockMvcBuilders.standaloneSetup(tournamentRestAPI).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetMatchesInRound() throws Exception {
        mockMvc.perform(get("/api/tournaments/rounds/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetMaxRounds() throws Exception {
        mockMvc.perform(get("/api/tournaments/rounds/max")
                .param("n", "8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetRemainingUniquePairs() throws Exception {
        mockMvc.perform(get("/api/tournaments/match/remaining")
                .param("n", "8")
                .param("D", "3"))
                .andExpected(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetOpponentIndex() throws Exception {
        mockMvc.perform(get("/api/tournaments/match")
                .param("i", "0")
                .param("d", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetPlayerSchedule() throws Exception {
        mockMvc.perform(get("/api/tournaments/player/0/schedule"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetPlayerOpponentInRound() throws Exception {
        mockMvc.perform(get("/api/tournaments/player/0/round/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAddPlayer() throws Exception {
        mockMvc.perform(post("/api/tournaments/player")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"John Doe\""))
                .andExpect(status().isOk());
    }

    @Test
    void testRemovePlayer() throws Exception {
        mockMvc.perform(delete("/api/tournaments/player/1"))
                .andExpect(status().isOk());
    }
}