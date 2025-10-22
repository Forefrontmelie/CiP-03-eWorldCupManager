import './App.css';
import { useEffect, useState } from 'react';
//import Button from './components/Button';
import InputField from './components/InputField';
import { fetchParticipants } from './http';
import { Button, Container, Typography } from '@mui/material';

function App() {

  const [participants, setParticipants] = useState([]);
  const [participantId, setParticipantId] = useState("");



  


  const handleFetchParticipants = async () => {  
      const response = await fetchParticipants();
      setParticipants(response.data);
  };



    // Alternative approach with tabs
const [activeTab, setActiveTab] = useState('participants');

const renderTabContent = () => {
  switch (activeTab) {
    case 'participants':
      return (
        <div>
          <h2>Participants</h2>
          <ul>
            {participants.map(participant => (
              <li key={participant.id}>{participant.name}</li>
            ))}
          </ul>
        </div>
      );
    case 'matches':
      return <div><h2>Matches</h2></div>;
    default:
      return null;
  }
};

  return (
    <div className="App">
      <header className="App-header">
        <h1 className="mb-4">eWorldCup Manager</h1>

        <ul className="nav justify-content-center nav-tabs mb-4">
          <li className="nav-item">
            <button 
              className={`nav-link ${activeTab === 'participants' ? 'active' : ''}`}
              onClick={() => setActiveTab('participants')}
            >
              Participants
            </button>
          </li>
          <li className="nav-item">
            <button 
              className={`nav-link ${activeTab === 'tournament' ? 'active' : ''}`}
              onClick={() => setActiveTab('tournament')}
            >
              Tournament
            </button>
          </li>
        </ul>

        {renderTabContent()}

        {activeTab === 'participants' && (
          <div>
            <div className="mb-4">
              <Button 
                text="Get all participants"
                className="me-0 align-self-center"
                onClick={handleFetchParticipants} 
              />
            </div>

            <div className="d-flex gap-2">
              <InputField 
                placeholder="Enter participant ID" 
                id="participantId" 
                onChange={(e) => setParticipantId(e.target.value)} 
                value={participantId} 
                onKeyUp={(e) => {
                  if (e.key === 'Enter') {
                    alert(`Fetching participant with ID: ${participantId}`);
                  }
                }} 
              />
              <Button 
                text="Get participant by ID"
                className="me-0 align-self-center"
                onClick={() => alert(`Fetching participant with ID: ${participantId}`)} 
              />
            </div>

            <div className="d-flex gap-2 mt-4">

              {/* TODO: Lägg till Form runomkting och kanske TextField i stället för InputField? */}

              <InputField 
                placeholder="Enter participant ID to delete" 
                id="deleteParticipantId" 
                onChange={(e) => setParticipantId(e.target.value)} 
                value={participantId} 
                onKeyUp={(e) => {
                  if (e.key === 'Enter') {
                    alert(`Deleting participant with ID: ${participantId}`);
                  }
                }} 
              />
              <Button 
                text="Delete participant"
                className="me-0 align-self-center"
                onClick={() => alert(`Deleting participant with ID: ${participantId}`)} 
              />
            </div>
          </div>
        )}
      </header>
    </div>
  );
}

export default App;
