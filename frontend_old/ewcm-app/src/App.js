import './App.css';
import { useEffect, useState } from 'react';
//import Button from './components/Button';
import InputField from './components/InputField';
import { fetchParticipants } from './http';
import { Button, Container, Typography, Tabs, Tab } from '@mui/material';

function App() {

  const [participants, setParticipants] = useState([]);
  const [participantId, setParticipantId] = useState("");



  


  const handleFetchParticipants = async () => {  
      const response = await fetchParticipants();
      setParticipants(response.data);
  };

 
  const [activeTab, setActiveTab] = useState('participants');

  const renderTabContent = () => {
    switch (activeTab) {
      case 'participants':
        return (
          <div>
            <Typography variant="h2" gutterBottom>
              Participants
            </Typography>
            <ul style={{ listStyle: 'none', padding: 0 }}>
              {participants.map(participant => (
                <Typography 
                  key={participant.id} 
                  component="li" 
                  variant="body1"
                  sx={{ mb: 1 }}
                >
                  {participant.name}
                </Typography>
              ))}
            </ul>
          </div>
        );
      case 'tournament':
        return (
          <div>
            <Typography variant="h2" gutterBottom>
              Tournament
            </Typography>
          </div>
        );
      default:
        return null;
    }
  };

  return (
    <Container maxWidth="lg">
      <div className="App">
        <header className="App-header">
          <Typography variant="h1" gutterBottom>eWorldCup Manager</Typography>

          <Tabs 
            value={activeTab} 
            onChange={(event, newValue) => setActiveTab(newValue)}
            centered
            sx={{ mb: 4 }}
          >
            <Tab label="Participants" value="participants" />
            <Tab label="Tournament" value="tournament" />
          </Tabs>

          {renderTabContent()}

          {activeTab === 'participants' && (
            <div>
              <div className="mb-4">
                <Button 
                  variant="contained"
                  onClick={handleFetchParticipants} 
                  sx={{ mb: 2 }}
                >
                  Get all participants
                </Button>
              </div>

              <div className="d-flex gap-2" style={{ display: 'flex', gap: '16px', marginBottom: '16px' }}>
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
                  variant="outlined"
                  onClick={() => alert(`Fetching participant with ID: ${participantId}`)} 
                >
                  Get participant by ID
                </Button>
              </div>

              <div style={{ display: 'flex', gap: '16px', marginTop: '16px' }}>
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
                  variant="outlined"
                  color="error"
                  onClick={() => alert(`Deleting participant with ID: ${participantId}`)} 
                >
                  Delete participant
                </Button>
              </div>
            </div>
          )}
        </header>
      </div>
    </Container>
  );
}

export default App;
