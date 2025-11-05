using CiP_03_eWorldCupManager.Domain.Models;

namespace CiP_03_eWorldCupManager.Domain.Interfaces;

public interface IPairingStrategy
{
    IList<Participant> RotateParticipants(List<Participant> participants, int roundNbr);

    int GetOpponentIndex(int participantIndex, int n, int roundNbr);
}
