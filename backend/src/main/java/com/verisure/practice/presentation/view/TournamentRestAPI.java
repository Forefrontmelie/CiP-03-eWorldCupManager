package com.verisure.practice.presentation.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.verisure.practice.domain.participant.Participant;
import com.verisure.practice.presentation.controller.TournamentController;


/*
HTTP-metod	URL-path	Function / Description


 */

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestAPI {

	private float version = 1.0f;
	private TournamentController tournamentController;


	public TournamentRestAPI (TournamentController tournamentController) {
		this.tournamentController = tournamentController;
	}


	//GET		/rounds/:d				Returnerar alla matcher i runda d (1 ≤ d ≤ n−1).
	@GetMapping("/rounds/{d}")
	public List<Tournament> getMatchesInRound(@PathVariable int d) {
		return tournamentService.getMatchesInRound(d);
	}


	//GET		/rounds/max?n=			Returnerar max antal rundor för n deltagare (n−1).
	@GetMapping("/rounds/max")
	public int getMaxRounds(@RequestParam int n) {
		return tournamentService.getMaxRounds(n);
	}

	// TODO: KLAR!
	//GET		/match/remaining?n=&D=	Returnerar antal återstående unika par efter att D rundor har spelats.
	@GetMapping("/match/remaining")
	public int getRemainingUniquePairs(@RequestParam int n, @RequestParam int D) {
		return tournamentController.getRemainingUniquePairs(n, D);
	}

	//GET		/match?n=&i=&d=			Returnerar direkt vem spelare i möter i runda d (0-baserat index).
	@GetMapping("/match")
	public int getOpponentInRound(@RequestParam int n, @RequestParam int i, @RequestParam int d) {
		return tournamentController.getRoundOpponent(n, i, d);   //TODO: Returnerar index. Participant i stället?
	}

	//GET		/player/:i/schedule		Returnerar hela schemat för spelare i över rundor 1..n−1.
	@GetMapping("/player/{i}/schedule")
	public List<Match> getPlayerSchedule(@PathVariable int i) {
		return tournamentService.getPlayerSchedule(i);
	}

	//GET		/player/:i/round/:d		Alias till “direktfråga” för spelare i i runda d, men med namn/objekt.
	@GetMapping("/player/{i}/round/{d}")
	public Participant getPlayerOpponentInRound(@PathVariable int i, @PathVariable int d) {
		return tournamentService.getPlayerOpponentInRound(i, d);
	}

	//POST	/player	(Bonus) 		Lägg till en ny deltagare i listan.
	@PostMapping("/player")
	public Participant addPlayer(@RequestBody Participant participant) {
		return tournamentService.addPlayer(participant);
	}

	//DELETE	/player/:id	(Bonus) 	Ta bort en deltagare ur listan baserat på ID.
	@DeleteMapping("/player/{id}")
	public void removePlayer(@PathVariable Long id) {
		tournamentService.removePlayer(id);
	}


}
