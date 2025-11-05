using CiP_03_eWorldCupManager.Domain.Dtos;
using CiP_03_eWorldCupManager.Domain.Interfaces;
using CiP_03_eWorldCupManager.Domain.Models;
using System;

namespace CiP_03_eWorldCupManager.Application.Services;

public class TournamentService : ITournamentService
{
    public IParticipantRepository ParticipantRepository { get; }
    public IPairingStrategy PairingStrategy { get; }

    private IList<Participant> Participants { get; set; }
    //private IList<List<MatchPair>> Rounds { get; init; }
    private IList<List<PairDTO>> Rounds { get; init; }


    public TournamentService(IParticipantRepository participantRepository, IPairingStrategy pairingStrategy)
    {
        ParticipantRepository = participantRepository;
        PairingStrategy = pairingStrategy;

        UpdateParticipantsList();
    }


    public void UpdateParticipantsList()
    {
        Participants = new List<Participant>(ParticipantRepository.GetAllParticipants());         
    }

    public int GetMaxNumberOfRounds(int n)
    {
        //int n = participants.Count;
        if (n == 0 || n % 2 != 0) return 0;
        return n - 1;
    }


    public int GetOpponentIndex(int index, int roundNbr)   // <<<<------------------------ SE ÖVER OCH ÄNDRA -------------------------------------------------
    {
        if (roundNbr <= 0)
            throw new ArgumentOutOfRangeException(nameof(roundNbr));
        if (index < 0 || index > Participants.Count)    
            throw new ArgumentOutOfRangeException(nameof(index));

        UpdateParticipantsList();

        return PairingStrategy.GetOpponentIndex(index, Participants.Count, roundNbr);
    }

    public Participant GetOpponentParticipant(int index, int roundNumber)
    {
        int opponentIndex = GetOpponentIndex(index, roundNumber);
        return GetParticipant(opponentIndex);
    }

    public Participant GetParticipant(int id)        // <<<<------------------------ SE ÖVER OCH ÄNDRA -------------------------------------------------
    {
        UpdateParticipantsList();

        if (id < 0 || id > Participants.Max(p => p.Id))     // TODO: <------------------------     FUNKAR?
            throw new ArgumentOutOfRangeException(nameof(id));

        return Participants.FirstOrDefault(p => p.Id == id); 
    }


    public IList<Participant> GetPairsForRound(int roundNbr)   // <<<<------------------------ SE ÖVER OCH ÄNDRA -------------------------------------------------
    {
        UpdateParticipantsList();

        if (roundNbr < 1 || roundNbr > GetMaxNumberOfRounds(Participants.Count))
        {
            throw new ArgumentException("Round number must be between 1 and " + GetMaxNumberOfRounds(ParticipantRepository.GetAllParticipants().Count));
        }

        return PairingStrategy.RotateParticipants(new List<Participant>(Participants), roundNbr);
    }


    public RoundDTO GetPairsForSpecificRound(int roundNbr)   // <<<<------------------------ SE ÖVER OCH ÄNDRA -------------------------------------------------
    {
        if (roundNbr <= 0)
            throw new ArgumentOutOfRangeException(nameof(roundNbr));

        IList<Participant> rotatedParticipants = GetPairsForRound(roundNbr);
        //IList<MatchPair> pairs = new List<MatchPair>();
        IList<PairDTO> pairs = new List<PairDTO>();

        for (int i = 0; i < rotatedParticipants.Count / 2; i++)
        {
            Participant Player1 = rotatedParticipants[i];
            Participant Player2 = rotatedParticipants[rotatedParticipants.Count - 1 - i];
            //pairs.Add(new MatchPair(roundNbr, Player1, Player2));
            pairs.Add(new PairDTO(Player1.Name, Player2.Name));
        }
        return new RoundDTO(roundNbr, pairs);
    }


    public PlayerScheduleDTO GetPlayerSchedule(int id)    // <<<<------------------------ SE ÖVER OCH ÄNDRA ??? -------------------------------------------------
    {
        UpdateParticipantsList();
        Participant player = Participants.FirstOrDefault(p => p.Id == id);
        string playerName = player.Name; 

        int totalRounds = GetMaxNumberOfRounds(Participants.Count);
        IList<PlayerScheduleEntryDTO> schedule = new List<PlayerScheduleEntryDTO>();

        for (int round = 1; round <= totalRounds; round++)
        {   
            int opponentIndex = GetOpponentIndex(id, round);  
            string opponent = Participants[opponentIndex].Name;

            //schedule.Add(new MatchPair(round, player, opponent));
            schedule.Add(new PlayerScheduleEntryDTO(round, opponent));
        }

        return new PlayerScheduleDTO(playerName, totalRounds, schedule);
    }

    public int GetRemainingUniquePairs(int roundsPlayed)         // <<<<------------------------ SE ÖVER OCH ÄNDRA --- Kolla Java-koden! ---------------------------------
    {
        UpdateParticipantsList();
        int n = Participants.Count;
        if (n < 2) return 0;      // TODO:  <--------------------- Ändra till Exception !!!
        int totalPairs = n * (n - 1) / 2;
        int pairsPlayed = Math.Min(roundsPlayed, GetMaxNumberOfRounds(n)) * (n / 2);
        return Math.Max(0, totalPairs - pairsPlayed);
    }




    // (Optional) Simple console printing methods similar to original controllers.
    public void PrintRound(int roundNumber)
    {
        var round = GetPairsForSpecificRound(roundNumber);
        Console.WriteLine($"The pairs for round {round.Round}:");
        foreach (var p in round.Pairs)
            //Console.WriteLine($"{p.Player1.Name} vs {p.Player2.Name}");
            Console.WriteLine($"{p.player1} vs {p.player2}");
    }

    public void PrintMessage(string message)
    {
        Console.WriteLine(message);
    }




}
