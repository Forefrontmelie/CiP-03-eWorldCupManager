package com.verisure.practice.presentation.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.presentation.controller.ParticipantController;
import com.verisure.practice.presentation.controller.TournamentController;
import com.verisure.practice.domain.Match;



 // ------------------------------------ TODO: SE ÖVER SÅ SKICKAR JSON!! ------------------------------------


@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestAPI {

	private float version = 1.0f;
	private TournamentController tournamentController;
	private ParticipantController participantController;


	public TournamentRestAPI () {
		this.participantController = new ParticipantController();
		this.tournamentController = new TournamentController(participantController);

	}


	// TODO: KLAR!
	//GET		/rounds/:d				Returnerar alla matcher i runda d (1 ≤ d ≤ n−1).
	@GetMapping("/rounds/{d}")
	public ArrayList<Participant> getMatchesInRound(@PathVariable int d) {
		return tournamentController.getPairsForSpecificRound(d);
	}


	// TODO: KLAR!
	//GET		/rounds/max?n=			Returnerar max antal rundor för n deltagare (n−1).
	@GetMapping("/rounds/max")
	public int getMaxRounds(@RequestParam int n) {
		return tournamentController.getMaxNumberOfRounds(n);
	}

	// TODO: KLAR!
	//GET		/match/remaining?n=&D=	Returnerar antal återstående unika par efter att D rundor har spelats.
	@GetMapping("/match/remaining")
	public int getRemainingUniquePairs(@RequestParam int n, @RequestParam int D) {
		return tournamentController.getRemainingUniquePairs(n, D);
	}

	//GET		/match?n=&i=&d=			Returnerar direkt vem spelare i möter i runda d (0-baserat index).
	@GetMapping("/match")
	public int getOpponentIndex(@RequestParam int i, @RequestParam int d) {
		return tournamentController.getOpponentIndex(i, d);
	}

	//GET		/player/:i/schedule		Returnerar hela schemat för spelare i över rundor 1..n−1.
	@GetMapping("/player/{i}/schedule")
	public List<Match> getPlayerSchedule(@PathVariable int i) {
		return tournamentController.getPlayerSchedule(i);
	}

	//GET		/player/:i/round/:d		Alias till “direktfråga” för spelare i i runda d, men med namn/objekt.
	@GetMapping("/player/{i}/round/{d}")
	public Participant getPlayerOpponentInRound(@PathVariable int i, @PathVariable int d) {
		return tournamentController.getOpponentParticipant(i, d);
	}


	//GET		/rounds/:d				Returnerar alla participants.
	@GetMapping("/rounds/{d}")
	public ArrayList<Participant> getAllParticipants() {
		return participantController.getParticipants();
	}

	//POST	/player	(Bonus) 		Lägg till en ny deltagare i listan.
	@PostMapping("/player")
	public void addPlayer(@RequestBody String name) {
		participantController.createParticipant(name);   // TODO: Ha en retur?
	}

	//DELETE	/player/:id	(Bonus) 	Ta bort en deltagare ur listan baserat på ID.
	@DeleteMapping("/player/{id}")
	public void removePlayer(@PathVariable int id) {
		participantController.removeParticipant(id); // TODO: Ha en retur?
	}


}
