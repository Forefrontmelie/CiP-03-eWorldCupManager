package com.verisure.practice.presentation.view;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.presentation.controller.ParticipantController;
import com.verisure.practice.presentation.controller.TournamentController;
import com.verisure.practice.presentation.dto.PlayerScheduleDTO;
import com.verisure.practice.presentation.dto.RoundDTO;

@RestController
@RequestMapping("/" + TournamentRestAPI.API_VERSION)
public class TournamentRestAPI {

	public static final String API_VERSION = "1.0"; // Used in base path /api/1.0/...
	private final TournamentController tournamentController;
	private final ParticipantController participantController;

	@Autowired
	public TournamentRestAPI(TournamentController tournamentController, ParticipantController participantController) {
		this.tournamentController = tournamentController;
		this.participantController = participantController;
	}

	@GetMapping("/tournaments/rounds/{d}")
	public RoundDTO getMatchesInRound(@PathVariable int d) {
		return tournamentController.getPairsForSpecificRound(d);
	}

	
	//GET		/rounds/max?n=			Returnerar max antal rundor för n deltagare (n−1).
	@GetMapping("/tournaments/rounds/max")
	public int getMaxRounds(@RequestParam int n) {
		return tournamentController.getMaxNumberOfRounds(n);
	}

	//GET		/match/remaining?n=&D=	Returnerar antal återstående unika par efter att D rundor har spelats.
	@GetMapping("/tournaments/match/remaining")
	public int getRemainingUniquePairs(@RequestParam int n, @RequestParam int D) {
		return tournamentController.getRemainingUniquePairs(n, D);
	}

	//GET		/match?n=&i=&d=			Returnerar direkt vem spelare i möter i runda d (0-baserat index).
	@GetMapping("/tournaments/match")
	public int getOpponentIndex(@RequestParam int i, @RequestParam int d) {
		return tournamentController.getOpponentIndex(i, d);
	}

	//GET		/player/:i/schedule		Returnerar hela schemat för spelare i över rundor 1..n−1.
	@GetMapping("/tournaments/player/{i}/schedule")
	public PlayerScheduleDTO getPlayerSchedule(@PathVariable int i) {
		return tournamentController.getPlayerSchedule(i);
	}

	//GET		/player/:i/round/:d		Alias till “direktfråga” för spelare i i runda d, men med namn/objekt.
	@GetMapping("/tournaments/player/{i}/round/{d}")
	public Participant getPlayerOpponentInRound(@PathVariable int i, @PathVariable int d) {
		return tournamentController.getOpponentParticipant(i, d);
	}


	//GET		/participants/				Returnerar alla participants.
	@GetMapping("/participants/")
	public ArrayList<Participant> getAllParticipants() {
		return participantController.getParticipants();
	}

	//POST	/player	(Bonus) 		Lägg till en ny deltagare i listan.
	@PostMapping("/participants/player")
	public void addPlayer(@RequestBody String name) {
		participantController.createParticipant(name);  
	}

	//DELETE	/player/:id	(Bonus) 	Ta bort en deltagare ur listan baserat på ID.
	@DeleteMapping("/participants/player/{id}")
	public void removePlayer(@PathVariable int id) {
		participantController.removeParticipant(id); 
	}
}
