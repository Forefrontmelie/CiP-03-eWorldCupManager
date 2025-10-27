# eWorldCup Manager

A comprehensive tournament management system built with Java Spring Boot backend and React frontend. This application manages tournament participants, match scheduling, and pairing strategies for competitive tournaments. The system is containerized using Docker and includes MongoDB for data persistence.

## Project Structure

```
eWorldCupManager/
├── backend/                    # Java Spring Boot backend
│   ├── src/main/java/com/verisure/practice/
│   │   ├── application/        # Application services
│   │   │   ├── participant/    # Participant service layer
│   │   │   └── tournament/     # Tournament service and strategies
│   │   ├── domain/             # Domain entities
│   │   │   ├── participant/    # Participant domain models
│   │   │   └── MatchPair.java  # Match pair entity
│   │   ├── infrastructure/     # Infrastructure layer
│   │   │   ├── factory/        # Factory patterns
│   │   │   └── strategy/       # Pairing strategy implementations
│   │   └── presentation/       # Presentation layer
│   │       ├── controller/     # REST controllers
│   │       ├── dto/           # Data transfer objects
│   │       └── view/          # REST API endpoints
│   ├── pom.xml                # Maven dependencies
│   ├── Dockerfile             # Backend container configuration
│   └── server.js              # Node.js proxy server
├── frontend/                   # React frontend application
│   ├── ewcm-app/              # Vite React application
│   │   ├── src/               # React source code
│   │   ├── package.json       # Frontend dependencies
│   │   └── vite.config.js     # Vite configuration
│   ├── Dockerfile             # Frontend container configuration
│   └── server.js              # Frontend development server
├── mongo-init/                 # MongoDB initialization scripts
│   └── init-db.js             # Database setup and sample data
├── docker-compose.yml          # Multi-container orchestration
└── README.md                   # This file
```

## Technology Stack

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.x** - Application framework
- **Maven** - Dependency management and build tool
- **JUnit 5** - Testing framework

### Frontend
- **React 19** - User interface library
- **Vite** - Build tool and development server
- **ESLint** - Code linting

### Infrastructure
- **Docker & Docker Compose** - Containerization
- **MongoDB 7.0** - Database
- **Node.js** - Development proxy servers

## Features

- **Tournament Management**: Create and manage competitive tournaments
- **Participant Management**: Add and manage tournament participants
- **Match Pairing**: Intelligent pairing strategies including rotation-based pairing
- **Round Scheduling**: Generate and manage tournament rounds
- **REST API**: Comprehensive API for tournament operations
- **Responsive UI**: Modern React-based user interface
- **Containerized Deployment**: Full Docker support for easy deployment

## Quick Start

### Prerequisites

- [Docker](https://www.docker.com/get-started) and Docker Compose
- [Git](https://git-scm.com/)
- [Java 17](https://adoptium.net/) (for local development)
- [Maven 3.6+](https://maven.apache.org/) (for local development)
- [Node.js 18+](https://nodejs.org/) (for frontend development)

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Forefrontmelie/CiP-03-eWorldCupManager.git
   cd CiP-03-eWorldCupManager
   ```

2. **Start all services with Docker Compose**
   ```bash
   docker-compose up --build
   ```

3. **Access the applications**
   - **Frontend**: http://localhost:3000
   - **Backend API**: http://localhost:3001
   - **MongoDB**: localhost:27017

### Local Development

#### Backend Development

```bash
cd backend

# Build the project
mvn clean install

# Run tests
mvn test

# Run the Spring Boot application
mvn spring-boot:run
```

#### Frontend Development

```bash
cd frontend/ewcm-app

# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build
```

## 📡 API Endpoints

### Tournament Management
- `GET /api/tournaments/rounds/{d}` - Get all matches in round d (1 ≤ d ≤ n−1)
- `GET /api/tournaments/rounds/max?n=` - Get maximum number of rounds for n participants (n−1)
- `GET /api/tournaments/match/remaining?n=&D=` - Get number of remaining unique pairs after D rounds
- `GET /api/tournaments/schedule/{id}` - Get tournament schedule for participant
- `POST /api/tournaments/generate` - Generate tournament schedule

### Participant Management
- `GET /api/participants` - Get all participants
- `POST /api/participants` - Create a new participant
- `GET /api/participants/{id}` - Get participant by ID
- `PUT /api/participants/{id}` - Update participant
- `DELETE /api/participants/{id}` - Delete participant

### Health Check
- `GET /api/health` - Check API status and database connection

## Architecture

### Domain-Driven Design

The backend follows Domain-Driven Design principles with clear separation of concerns:

```
Application Layer    →  ParticipantService, TournamentService
Domain Layer        →  Participant, MatchPair, PairingStrategy
Infrastructure Layer →  Factories, Strategy Implementations
Presentation Layer   →  Controllers, DTOs, REST API
```

### Design Patterns

- **Factory Pattern**: `ParticipantFactory`, `HumanParticipantFactory`
- **Strategy Pattern**: `PairingStrategy`, `RotationPairingStrategy`
- **Controller Pattern**: REST controllers for API endpoints
- **DTO Pattern**: Data transfer objects for API communication

### Key Components

#### Participants
- **Participant**: Abstract base class for tournament participants
- **HumanParticipant**: Concrete implementation for human players

#### Tournament Logic
- **TournamentService**: Core tournament management logic
- **PairingStrategy**: Interface for different pairing algorithms
- **RotationPairingStrategy**: Round-robin tournament implementation

#### Match Management
- **MatchPair**: Represents a match between two participants
- **PlayerScheduleDTO**: Data transfer object for participant schedules
- **RoundDTO**: Data transfer object for tournament rounds

## 🗄️ Database

The application uses MongoDB with the following structure:

- **participants** - Tournament participants and their information
- **tournaments** - Tournament configurations and settings
- **matches** - Match pairs and results
- **schedules** - Participant schedules and round information

### Sample Data

The database is automatically initialized with sample data for development and testing purposes.

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

# Clean up containers and volumes
docker-compose down -v
docker system prune -a
```

## Development

### Backend Development

The backend is structured using clean architecture principles:

1. **Domain Layer**: Core business logic and entities
2. **Application Layer**: Use cases and service orchestration
3. **Infrastructure Layer**: External concerns (database, factories)
4. **Presentation Layer**: REST API and data transfer objects

### Adding New Features

#### Backend
1. **Domain**: Add new entities in `domain/` package
2. **Application**: Create services in `application/` package
3. **Infrastructure**: Implement factories or strategies in `infrastructure/`
4. **Presentation**: Add controllers and DTOs in `presentation/`

#### Frontend
1. **Components**: Add React components in `src/components/`
2. **Services**: Create API services in `src/services/`
3. **Styles**: Update CSS in component files or `src/`

### Testing

```bash
# Backend tests
cd backend
mvn test

# Frontend tests (when implemented)
cd frontend/ewcm-app
npm test

# Integration tests via Docker
docker-compose up --build
curl http://localhost:3001/api/tournaments/rounds/max?n=8
```

### Environment Variables

Key environment variables in Docker Compose:

#### Backend
- `NODE_ENV` - Environment mode (development/production)
- `PORT` - Backend server port (default: 3001)
- `MONGODB_URI` - MongoDB connection string

#### Frontend
- `NODE_ENV` - Environment mode (development/production)
- `PORT` - Frontend server port (default: 3000)
- `REACT_APP_API_URL` - Backend API URL

## Production Deployment

### Environment Setup

1. **Update production configuration**
   ```bash
   # Set production values for backend
   NODE_ENV=production
   MONGODB_URI=your-production-mongodb-uri
   
   # Set production values for frontend
   REACT_APP_API_URL=https://your-backend-api-url
   ```

2. **Build for production**
   ```bash
   # Build optimized images
   docker-compose build --no-cache
   
   # Deploy with production settings
   docker-compose up -d
   ```

### Deployment Checklist

- [ ] Configure production MongoDB instance
- [ ] Set secure environment variables
- [ ] Enable HTTPS/SSL certificates
- [ ] Configure reverse proxy (nginx/Apache)
- [ ] Set up monitoring and logging
- [ ] Configure backup strategies

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes following the project structure
4. Add tests for new functionality
5. Commit changes (`git commit -m 'Add amazing feature'`)
6. Push to branch (`git push origin feature/amazing-feature`)
7. Open a Pull Request

### Code Style

- **Java**: Follow standard Java conventions and Spring Boot best practices
- **React**: Use functional components with hooks
- **Formatting**: Use consistent indentation and naming conventions

## Troubleshooting

### Common Issues

1. **Maven build failures**
   ```bash
   # Clean and rebuild
   cd backend
   mvn clean install -U
   
   # Skip tests if needed
   mvn clean install -DskipTests
   ```

2. **Frontend build issues**
   ```bash
   cd frontend/ewcm-app
   # Clear node modules and reinstall
   rm -rf node_modules package-lock.json
   npm install
   ```

3. **Port conflicts**
   ```bash
   # Kill processes on required ports
   # Windows (use Task Manager or):
   netstat -ano | findstr :3000
   taskkill /PID <PID> /F
   
   # Linux/Mac:
   sudo lsof -ti:3000 | xargs kill -9
   sudo lsof -ti:3001 | xargs kill -9
   sudo lsof -ti:27017 | xargs kill -9
   ```

4. **MongoDB connection issues**
   ```bash
   # Check MongoDB container status
   docker-compose logs mongodb
   
   # Restart MongoDB service
   docker-compose restart mongodb
   
   # Connect directly to MongoDB
   docker exec -it eworldcup-mongodb mongosh -u admin -p password123
   ```

5. **Docker issues**
   ```bash
   # Clear Docker cache
   docker system prune -a
   docker-compose down -v
   
   # Rebuild everything
   docker-compose up --build --force-recreate
   ```

6. **Java version conflicts**
   ```bash
   # Check Java version
   java -version
   mvn -version
   
   # Ensure Java 17 is being used
   export JAVA_HOME=/path/to/java17
   ```

### Performance Tips

- Use Docker build cache for faster builds
- Enable hot reloading for development
- Monitor container resource usage
- Optimize Maven dependencies
- Use React dev tools for frontend debugging

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Happy Tournament Managing! 🏆**