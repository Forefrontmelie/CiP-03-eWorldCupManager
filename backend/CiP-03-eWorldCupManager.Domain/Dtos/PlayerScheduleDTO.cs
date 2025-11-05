using CiP_03_eWorldCupManager.Domain.Models;

namespace CiP_03_eWorldCupManager.Domain.Dtos;

public class PlayerScheduleDTO
{
    public string PlayerName { get; init; }
    public int TotalRounds { get; init; }
    public IList<PlayerScheduleEntryDTO> Schedule { get; init; }

    public PlayerScheduleDTO(string playerName, int totalRounds, IList<PlayerScheduleEntryDTO> schedule)
    {
        PlayerName = playerName;
        TotalRounds = totalRounds;
        Schedule = schedule;
    }
}
