// MongoDB initialization script
// This script runs when the MongoDB container starts for the first time

db = db.getSiblingDB('eworldcup');

// Create collections
db.createCollection('participants');

// Insert sample data
db.participants.insertMany([
  { "id": 1, "name": "Alice" },
  { "id": 2, "name": "Bob" },
  { "id": 3, "name": "Charlie" },
  { "id": 4, "name": "Diana" },
  { "id": 5, "name": "Ethan" },
  { "id": 6, "name": "Fiona" },
  { "id": 7, "name": "George" },
  { "id": 8, "name": "Hannah" },
  { "id": 9, "name": "Isaac" },
  { "id": 10, "name": "Julia" },
  { "id": 11, "name": "Kevin" },
  { "id": 12, "name": "Laura" },
  { "id": 13, "name": "Michael" },
  { "id": 14, "name": "Nina" },
  { "id": 15, "name": "Oscar" },
  { "id": 16, "name": "Paula" },
  { "id": 17, "name": "Quentin" },
  { "id": 18, "name": "Rachel" },
  { "id": 19, "name": "Samuel" },
  { "id": 20, "name": "Tina" }
]);


console.log('Database initialized with sample data');
console.log('Participants:', db.participants.count());