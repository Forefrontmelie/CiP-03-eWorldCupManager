using Xunit;
using CiP_03_eWorldCupManager.Infrastructure;
using CiP_03_eWorldCupManager.Application.Services;
using CiP_03_eWorldCupManager.Domain.Dtos;

namespace CiP_03_eWorldCupManager.Tests;

public class TournamentServiceTests
{
    [Fact]
    public void GetPlayerSchedule_NoOnePlaysSelf_ForAllSeededParticipants()
    {
        // Arrange - use the seeded repository and real pairing strategy
        var repo = new ParticipantRepository();
        var pairing = new RoundRobinPairingStrategy();
        var service = new TournamentService(repo, pairing);

        var participants = repo.GetAllParticipants();

        // Act & Assert - for each seeded participant, ensure no opponent equals player's name
        foreach (var p in participants)
        {
            PlayerScheduleDTO schedule = service.GetPlayerSchedule(p.Id);

            Assert.NotNull(schedule);
            Assert.Equal(service.GetMaxNumberOfRounds(participants.Count), schedule.TotalRounds);

            foreach (var entry in schedule.Schedule)
            {
                Assert.NotNull(entry);
                Assert.NotEqual(p.Name, entry.Opponent);
            }
        }
    }
}