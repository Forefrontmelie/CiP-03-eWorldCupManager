# eWorldCup Manager

A comprehensive World Cup management system built with Node.js, Express, and MongoDB. This monorepo contains both the frontend and backend applications, orchestrated with Docker Compose.

## Project Structure

```
eWorldCupManager/
├── frontend/           # Frontend application (Express + HTML/JS)
│   ├── Dockerfile     # Frontend container configuration
│   ├── package.json   # Frontend dependencies
│   └── server.js      # Frontend server
├── backend/           # Backend API (Express + MongoDB)
│   ├── Dockerfile     # Backend container configuration
│   ├── package.json   # Backend dependencies
│   └── server.js      # Backend API server
├── shared/            # Shared types and utilities
│   └── types.ts       # TypeScript interfaces and constants
├── mongo-init/        # MongoDB initialization scripts
│   └── init-db.js     # Sample data and collections
├── docker-compose.yml # Multi-container orchestration
├── .env.example       # Environment variables template
└── README.md          # This file
```

## Quick Start

### Prerequisites

- [Docker](https://www.docker.com/get-started) and Docker Compose
- [Git](https://git-scm.com/)

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd CiP-03-eWorldCupManager
   ```

2. **Set up environment variables**
   ```bash
   # Copy the example environment file
   cp .env.example .env
   
   # Edit .env file with your preferred settings (optional for development)
   ```

3. **Start all services with Docker Compose**
   ```bash
   docker-compose up --build
   ```

4. **Access the applications**
   - **Frontend**: http://localhost:3000
   - **Backend API**: http://localhost:3001
   - **MongoDB**: localhost:27017

### Development Mode

For development with hot reloading:

```bash
# Start all services in development mode
docker-compose up --build

# Or start specific services
docker-compose up mongodb backend  # Backend only
docker-compose up mongodb frontend # Frontend only
```

## 📡 API Endpoints

### Health Check
- `GET /api/health` - Check API status and database connection

### Teams
- `GET /api/teams` - Get all teams
- `POST /api/teams` - Create a new team
- `GET /api/teams/:id` - Get team by ID
- `PUT /api/teams/:id` - Update team
- `DELETE /api/teams/:id` - Delete team

### Players
- `GET /api/players` - Get all players
- `POST /api/players` - Create a new player
- `GET /api/players/:id` - Get player by ID
- `PUT /api/players/:id` - Update player
- `DELETE /api/players/:id` - Delete player

### Matches
- `GET /api/matches` - Get all matches
- `POST /api/matches` - Create a new match
- `GET /api/matches/:id` - Get match by ID
- `PUT /api/matches/:id` - Update match
- `DELETE /api/matches/:id` - Delete match

## 🗄️ Database

The application uses MongoDB with the following collections:

- **teams** - World Cup teams
- **players** - Player information
- **matches** - Match results and schedules
- **tournaments** - Tournament information

### Sample Data

The database is automatically initialized with sample data including:
- 3 sample teams (Brazil, Argentina, France)
- 3 sample players (Neymar Jr, Messi, Mbappé)

## 🐳 Docker Commands

```bash
# Build and start all services
docker-compose up --build

# Start services in background
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs
docker-compose logs backend   # Backend logs only
docker-compose logs frontend  # Frontend logs only
docker-compose logs mongodb   # Database logs only

# Rebuild specific service
docker-compose build backend
docker-compose up --no-deps backend

# Access database directly
docker exec -it eworldcup-mongodb mongosh -u admin -p password123
```

## Development

### Adding New Features

1. **Backend**: Add routes and controllers in `backend/server.js`
2. **Frontend**: Update UI in `frontend/server.js`
3. **Shared**: Add types and constants in `shared/types.ts`

### Environment Variables

Key environment variables (see `.env.example`):

- `MONGODB_URI` - MongoDB connection string
- `BACKEND_PORT` - Backend server port (default: 3001)
- `FRONTEND_PORT` - Frontend server port (default: 3000)
- `NODE_ENV` - Environment mode (development/production)

## Testing

```bash
# Test API endpoints
curl http://localhost:3001/api/health
curl http://localhost:3001/api/teams
curl http://localhost:3001/api/players
curl http://localhost:3001/api/matches
```

## Production Deployment

1. **Update environment variables**
   ```bash
   # Set production values in .env
   NODE_ENV=production
   MONGODB_URI=your-production-mongodb-uri
   JWT_SECRET=your-super-secure-secret
   ```

2. **Build for production**
   ```bash
   docker-compose -f docker-compose.yml -f docker-compose.prod.yml up --build
   ```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Troubleshooting

### Common Issues

1. **Port already in use**
   ```bash
   # Kill processes on required ports
   sudo lsof -ti:3000 | xargs kill -9  # Frontend port
   sudo lsof -ti:3001 | xargs kill -9  # Backend port
   sudo lsof -ti:27017 | xargs kill -9 # MongoDB port
   ```

2. **MongoDB connection issues**
   ```bash
   # Check MongoDB container status
   docker-compose logs mongodb
   
   # Restart MongoDB service
   docker-compose restart mongodb
   ```

3. **Permission issues**
   ```bash
   # Fix file permissions
   sudo chmod -R 755 .
   ```

4. **Clear Docker cache**
   ```bash
   docker system prune -a
   docker-compose down -v
   docker-compose up --build
   ```

---

**Happy coding!**