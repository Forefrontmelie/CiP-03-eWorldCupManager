const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3001;

// Middleware
app.use(cors());
app.use(express.json());

// MongoDB connection
const MONGODB_URI = process.env.MONGODB_URI || 'mongodb://admin:password123@localhost:27017/eworldcup?authSource=admin';

mongoose.connect(MONGODB_URI)
  .then(() => {
    console.log('Connected to MongoDB');
  })
  .catch((error) => {
    console.error('MongoDB connection error:', error);
  });

// Basic routes
app.get('/api/health', (req, res) => {
  res.json({ 
    message: 'eWorldCup Backend API is running!',
    timestamp: new Date().toISOString(),
    database: mongoose.connection.readyState === 1 ? 'connected' : 'disconnected'
  });
});

app.get('/api/teams', (req, res) => {
  res.json({ 
    message: 'Teams endpoint - ready for implementation',
    teams: []
  });
});

app.get('/api/players', (req, res) => {
  res.json({ 
    message: 'Players endpoint - ready for implementation',
    players: []
  });
});

app.get('/api/matches', (req, res) => {
  res.json({ 
    message: 'Matches endpoint - ready for implementation',
    matches: []
  });
});

// 404 handler
app.use('*', (req, res) => {
  res.status(404).json({ error: 'Route not found' });
});

// Error handler
app.use((error, req, res, next) => {
  console.error('Error:', error);
  res.status(500).json({ error: 'Internal server error' });
});

app.listen(PORT, '0.0.0.0', () => {
  console.log(`Backend server running on port ${PORT}`);
});