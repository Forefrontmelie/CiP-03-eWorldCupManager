using CiP_03_eWorldCupManager.Domain.Interfaces;
using CiP_03_eWorldCupManager.Domain.Models;

namespace CiP_03_eWorldCupManager.Infrastructure;

public class RoundRobinPairingStrategy : IPairingStrategy
{
    public IList<Participant> RotateParticipants(List<Participant> participants, int roundNbr)
    {
        if (participants == null || participants.Count < 2)
        {
            return participants;
        }

        if (roundNbr < 1)
        {
            throw new ArgumentException("The number of the round must be >= 1");
        }

        int n = participants.Count;

        int distance = (roundNbr - 1) % (n - 1); // 0 for round 1, 1 for round 2, etc.
        if (distance == 0)
        {
            return participants;
        }

        // Rotate only indices 1 .. n-1 (keeping index 0 fixed)
        int rotatableCount = n - 1;
        distance = distance % rotatableCount;

        var temp = new List<Participant>(rotatableCount);
        for (int i = 0; i < rotatableCount; i++)
        {
            int oldIndex = ((i - distance) % rotatableCount + rotatableCount) % rotatableCount;
            temp.Add(participants[1 + oldIndex]);
        }
        for (int i = 0; i < rotatableCount; i++)
        {
            participants[1 + i] = temp[i];
        }

        return participants; // Modified original list
    }

    public int GetOpponentIndex(int index, int n, int roundNbr)
    {
        if (n % 2 != 0 || index < 0 || index >= n || roundNbr < 1 || roundNbr >= n)
        {
            throw new ArgumentException("Invalid input parameters.");
        }

        if (index == 0)
        {
            int temp = (n - 1 + roundNbr - 1) % (n - 1) + 1;
            return temp;
        }
        else
        {
            int opponentIndex = (index + roundNbr - 1) % (n - 1);     // TODO:  SE ÖVER DENNA UTRÄKNING!
            return opponentIndex == 0 ? n - 1 : opponentIndex;
        }
    }
}
