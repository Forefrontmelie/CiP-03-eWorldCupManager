import './App.css';
import { useEffect, useState } from 'react';
import { fetchParticipants } from './http';
import { Button, Container, Typography, Tabs, Tab,TextField, Autocomplete, Box, Paper } from '@mui/material';

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

          </div>
        );
      default:
        return null;
    }
  };

  return (
    <Container maxWidth="lg">
      <Box sx={{ 
        display: 'flex', 
        flexDirection: 'column', 
        alignItems: 'center',
        minHeight: '100vh',
        py: 4 
      }}>
          <Typography variant="h1" gutterBottom>eWorldCup Manager</Typography>

          <Tabs 
            value={activeTab} 
            onChange={(event, newValue) => setActiveTab(newValue)}
            centered
            sx={{ mb: 4 }}
          >
            <Tab 
              label={<Typography variant="h6">Participants</Typography>} 
              value="participants" 
            />
            <Tab 
              label={<Typography variant="h6">Tournament</Typography>} 
              value="tournament" 
            />
          </Tabs>

        <Paper elevation={3} sx={{ p: 3, width: '100%', maxWidth: 600 }}>
          {renderTabContent()}  {/* Använd senare för att rendera informationen som hämtas? */}


          {/* ------------------------------------------ PARTICIPANTS ------------------------------------------ */}

          {activeTab === 'participants' && (
            <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
              <Box sx={{ display: 'flex', gap: 2, alignItems: 'center' }}>
                <Autocomplete
                  disablePortal
                  options={participants.map((option) => option.name)}
                  sx={{ width: 300 }}
                  renderInput={(params) => <TextField {...params} label="Participant" />}
                />

                <Button 
                  variant="contained"
                  onClick={handleFetchParticipants} 
                  sx={{ mb: 2 }}
                >
                  Get all participants
                </Button>
              </Box>

              <Box sx={{ display: 'flex', gap: 2, alignItems: 'center' }}>
                <TextField
                  id="outlined-helperText"
                  label="Participants ID"
                  placeholder="Enter participant ID" 
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
              </Box>

              <Box sx={{ display: 'flex', justifyContent: 'center', mt: 2 }}>
                <Button 
                  variant="outlined"
                  color="error"
                  onClick={() => alert(`Deleting participant with ID: ${participantId}`)} 
                >
                  Delete participant
                </Button>
              </Box>
            </Box>
          )}

          {/* ------------------------------------------ TOURNAMENT ------------------------------------------ */}

          {activeTab === 'tournament' && (
            <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
              <Box sx={{ display: 'flex', justifyContent: 'center', mb: 2 }}>
                <Button 
                  variant="contained"
                  onClick={() => alert('Create new tournament')} 
                  sx={{ mb: 2 }}
                >
                  Create Tournament
                </Button>
              </Box>

              <Box sx={{ display: 'flex', gap: 2, alignItems: 'center' }}>
                <TextField
                  label="Tournament Name"
                  placeholder="Enter tournament name"
                  // Add tournament-specific state and handlers
                />
                <Button 
                  variant="outlined"
                  onClick={() => alert('Start tournament')} 
                >
                  Start Tournament
                </Button>
              </Box>
            </Box>
          )}
        </Paper>
      </Box>
    </Container>
  );
}

export default App;
