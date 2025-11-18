using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using CiP_03_eWorldCupManager.Domain.Models;
using CiP_03_eWorldCupManager.Domain.Dtos;
using CiP_03_eWorldCupManager.Domain.Interfaces;

namespace CiP_03_eWorldCupManager.Controllers;

[Route("api/[controller]")]
[ApiController]
public class TournamentController(ITournamentService tournament, IParticipantRepository participant) : ControllerBase
{

    //GET		/rounds/:d				Returnerar alla matcher i runda d (1 ≤ d ≤ n−1).
    [HttpGet("/rounds/{roundNbr}")]
    public RoundDTO GetMatchesInRound(int roundNbr)
    {
        return tournament.GetPairsForSpecificRound(roundNbr);
    }


    //GET		/rounds/max?n=			Returnerar max antal rundor för n deltagare (n−1).
    [HttpGet("/rounds/max")]
    public int GetMaxRounds(int n)
    {
        return tournament.GetMaxNumberOfRounds(n);
    }

    //GET		/match/remaining?n=&D=	Returnerar antal återstående unika par efter att D rundor har spelats.
    [HttpGet("/match/remaining")]
    public int GetRemainingUniquePairs(int roundNbr)
    {
        return tournament.GetRemainingUniquePairs(roundNbr);
    }

    //GET		/match?n=&i=&d=			Returnerar direkt vem spelare i möter i runda d (0-baserat index).
    [HttpGet("/match")]
    public int GetOpponentIndex(int id,  int roundNbr)
    {
        return tournament.GetOpponentIndex(id, roundNbr);
    }

    //GET		/player/:i/schedule		Returnerar hela schemat för spelare i över rundor 1..n−1.
    [HttpGet("/player/{id}/schedule")]
    public PlayerScheduleDTO GetPlayerSchedule(int id)
    {
        return tournament.GetPlayerSchedule(id);
    }

    //GET		/player/:i/round/:d		Alias till “direktfråga” för spelare i i runda d, men med namn/objekt.
    [HttpGet("/player/{id}/round/{roundNbr}")]
    public Participant GetPlayerOpponentInRound(int id,  int roundNbr)
    {
        return tournament.GetOpponentParticipant(id, roundNbr);
    }




    // ::::::::::::::::::::::::: PARTICIPANT ROUTES ::::::::::::::::::::::::: //

    //GET		/participants/				Returnerar alla participants.
    [HttpGet("/participants")]
    public IList<Participant> GetAllParticipants()              // TODO: -------------------------------- SKAPA ParticipantDTO! ---------------------------------------
    {
        return participant.GetAllParticipants();
    }

    //POST	/player	(Bonus) 		Lägg till en ny deltagare i listan.
    [HttpPost("/player")]
    public void AddPlayer([FromBody] string name)
    {
        participant.AddParticipant(name);
    }

    //DELETE	/player/:id	(Bonus) 	Ta bort en deltagare ur listan baserat på ID.
    [HttpDelete("/player/{id}")]
    public void RemovePlayer(int id)
    {
        participant.RemoveParticipant(id);
    }

}
