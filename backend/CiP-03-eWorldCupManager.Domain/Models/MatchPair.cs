namespace CiP_03_eWorldCupManager.Domain.Models;

public class MatchPair(int round, Participant player1, Participant player2)
{
    public int Round { get; init; } = round;
    public Participant Player1 { get; init; } = player1;
    public Participant Player2 { get; init; } = player2;

    //[JsonProperty("opponent")]
    public string Opponent => Player2.Name;   //Computed property

}
