using CiP_03_eWorldCupManager.Domain.Models;
using System.Numerics;

namespace CiP_03_eWorldCupManager.Domain.Interfaces;

public interface IParticipantRepository
{

    // IEnumerable<Participant> GetAll();

    IList<Participant> GetAllParticipants();
    void AddParticipant(string name);
    void RemoveParticipant(int id);
    Participant? GetParticipantById(int id);
}
