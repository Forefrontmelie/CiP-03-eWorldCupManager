const express = require('express');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 3000;

// Serve static files
app.use(express.static('public'));

// Basic HTML page
app.get('/', (req, res) => {
  res.send(`
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>eWorldCup Manager</title>
        <style>
            body { 
                font-family: Arial, sans-serif; 
                max-width: 800px; 
                margin: 50px auto; 
                padding: 20px;
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                color: white;
                text-align: center;
            }
            .container {
                background: rgba(255, 255, 255, 0.1);
                padding: 40px;
                border-radius: 10px;
                backdrop-filter: blur(10px);
            }
            .status { 
                margin: 20px 0; 
                padding: 15px; 
                border-radius: 5px; 
                background: rgba(255, 255, 255, 0.2);
            }
            button {
                background: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                margin: 5px;
            }
            button:hover { background: #45a049; }
            #apiStatus { font-family: monospace; }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>🏆 eWorldCup Manager</h1>
            <p>Welcome to your World Cup management system!</p>
            
            <div class="status">
                <h3>Frontend Status: ✅ Running</h3>
                <p>Frontend server is up and running on port ${PORT}</p>
            </div>
            
            <div class="status">
                <h3>Backend API Status:</h3>
                <div id="apiStatus">Checking...</div>
                <button onclick="checkAPI()">Test API Connection</button>
            </div>
            
            <div class="status">
                <h3>Quick Actions:</h3>
                <button onclick="loadTeams()">Load Teams</button>
                <button onclick="loadPlayers()">Load Players</button>
                <button onclick="loadMatches()">Load Matches</button>
            </div>
            
            <div id="results"></div>
        </div>

        <script>
            const API_URL = 'http://localhost:3001';
            
            async function checkAPI() {
                try {
                    const response = await fetch(API_URL + '/api/health');
                    const data = await response.json();
                    document.getElementById('apiStatus').innerHTML = 
                        '✅ Connected<br>' + JSON.stringify(data, null, 2);
                } catch (error) {
                    document.getElementById('apiStatus').innerHTML = 
                        '❌ Connection failed: ' + error.message;
                }
            }
            
            async function loadTeams() {
                await makeAPICall('/api/teams');
            }
            
            async function loadPlayers() {
                await makeAPICall('/api/players');
            }
            
            async function loadMatches() {
                await makeAPICall('/api/matches');
            }
            
            async function makeAPICall(endpoint) {
                try {
                    const response = await fetch(API_URL + endpoint);
                    const data = await response.json();
                    document.getElementById('results').innerHTML = 
                        '<h3>API Response:</h3><pre>' + JSON.stringify(data, null, 2) + '</pre>';
                } catch (error) {
                    document.getElementById('results').innerHTML = 
                        '<h3>Error:</h3><p>' + error.message + '</p>';
                }
            }
            
            // Check API status on page load
            window.onload = checkAPI;
        </script>
    </body>
    </html>
  `);
});

app.listen(PORT, '0.0.0.0', () => {
  console.log(`Frontend server running on port ${PORT}`);
});