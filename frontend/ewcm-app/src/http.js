/**
 * API-endpoints
HTTP-metod	URL-sökväg	Funktion / Beskrivning
GET	    /rounds/:d	    Returnerar alla matcher i runda d (1 ≤ d ≤ n−1).
GET	    /rounds/max?n=	    Returnerar max antal rundor för n deltagare (n−1).
GET	    /match/remaining?n=&D=	    Returnerar antal återstående unika par efter att D rundor har spelats.
GET	    /match?n=&i=&d=	    Returnerar direkt vem spelare i möter i runda d (0-baserat index).
GET	    /player/:i/schedule	    Returnerar hela schemat för spelare i över rundor 1..n−1.
GET	    /player/:i/round/:d	    Alias till “direktfråga” för spelare i i runda d, men med namn/objekt.
POST	/player	(Bonus)     Lägg till en ny deltagare i listan.
DELETE	/player/:id	(Bonus)     Ta bort en deltagare ur listan baserat på ID.
 */


<<<<<<< HEAD
const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:7280/api';

// Fetch all participants
=======
const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:5000';


>>>>>>> db55cb8 (Updated the Frontend.)
export const fetchParticipants = async () => {
    const url = `${API_BASE_URL}/participants`;

    const response = await fetch(url, {
        method: 'GET',
    });

if (!response.ok) {
    console.error(`Error response: ${response.status} ${response.statusText}`);
    throw new Error(`HTTP-status ${response.status}: ${response.statusText}`);
    } 

    const data = await response.json();
    return { data };
};


<<<<<<< HEAD
// Fetch participant by ID
=======

>>>>>>> db55cb8 (Updated the Frontend.)
export const fetchParticipantById = async (id) => {
    const url = `${API_BASE_URL}/participants/${id}`;

    const response = await fetch(url, {
        method: 'GET',
    });

if (!response.ok) {
    console.error(`Error response: ${response.status} ${response.statusText}`);
    throw new Error(`HTTP-status ${response.status}: ${response.statusText}`);
    } 

    const data = await response.json();
    return { data };
};

<<<<<<< HEAD
// Delete participant by ID
=======

>>>>>>> db55cb8 (Updated the Frontend.)
export const deleteParticipantById = async (id) => {
    const url = `${API_BASE_URL}/participants/${id}`;

    const response = await fetch(url, {
        method: 'DELETE',
    });
if (!response.ok) {
    console.error(`Error response: ${response.status} ${response.statusText}`);
    throw new Error(`HTTP-status ${response.status}: ${response.statusText}`);
    } 

    const data = await response.json();
    return { data };
};

<<<<<<< HEAD
// Add a new participant (string name)
=======
>>>>>>> db55cb8 (Updated the Frontend.)
export const addParticipant = async (name) => {
    const url = `${API_BASE_URL}/participants`;
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name }),
    });

    if (!response.ok) {
        console.error(`Error response: ${response.status} ${response.statusText}`);
        throw new Error(`HTTP-status ${response.status}: ${response.statusText}`);
    } 

    const data = await response.json();
    return { data };
};