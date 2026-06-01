# 🌐 Network Projects - Java Mini Projects Dashboard

A **comprehensive Java web application** featuring 22+ interactive mini-projects for networking, security, data processing, and utilities. All projects are accessible through a modern, intuitive web dashboard.

---

## 📋 Table of Contents

- [Features](#features)
- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Project Details](#project-details)
- [API Endpoints](#api-endpoints)
- [WebSocket Support](#websocket-support)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

---

## ✨ Features

✅ **22+ Mini Projects** in one dashboard  
✅ **Modern UI** with sidebar navigation and real-time search  
✅ **REST API Backend** built with Java Servlets  
✅ **WebSocket Support** for real-time chat  
✅ **Security Tools** (password hashing, SQL injection detection, breach checker)  
✅ **Text Processing** (JSON validation, similarity checker, keyword scanner)  
✅ **Network Utilities** (website uptime checker, HTTP inspector, robots.txt parser)  
✅ **Data Analysis** (log reader, duplicate file finder, integrity verifier)  
✅ **Real-time Features** (live chat, booking system, bug tracker)  
✅ **Responsive Design** - Works on desktop and mobile  

---

## 🎯 Project Overview

This application is a **collection of practical utilities** designed to demonstrate Java programming concepts while providing useful tools for developers and network professionals.

### Dashboard Features:
- 🔍 **Search Bar** - Quickly find projects by name
- 📱 **Responsive Sidebar** - Easy navigation with active state tracking
- 💻 **Interactive Forms** - Each project has dedicated input forms
- 📊 **Real-time Results** - Instant feedback from backend
- 📡 **Request/Response Viewer** - See API payloads and expected responses

---

## 🛠️ Tech Stack

### Backend
- **Java 17** - Latest LTS version
- **Jakarta EE 6** - Modern Java EE standard
- **Apache Maven** - Build automation
- **MySQL Connector** - Database connectivity
- **Jackson** - JSON processing
- **JSoup** - HTML parsing
- **jBCrypt** - Password hashing
- **Jakarta Mail** - Email support
- **WebSocket API** - Real-time communication

### Frontend
- **HTML5** - Semantic markup
- **CSS3** - Modern styling with flexbox/grid
- **Vanilla JavaScript** - No jQuery dependency
- **WebSocket Client** - Real-time chat

### Build & Deployment
- **Docker** - Containerization ready
- **WAR Package** - Java servlet container deployment

---

## 📦 Installation

### Prerequisites
- **Java 17** or higher
- **Maven 3.8+**
- **Git**

### Clone the Repository
```bash
git clone https://github.com/yourusername/networkprojects.git
cd networkprojects
```

### Build the Project
```bash
mvn clean package
```

This will generate a WAR file in the `target/` directory:
```
target/Network-Project-1.0.war
```

---

## 🚀 Running the Application

### Option 1: Using Maven
```bash
# Compile and run with embedded server
mvn clean install
mvn tomcat7:run
```

Then visit: **http://localhost:8080/networkprojects**

### Option 2: Use a Servlet Container
Deploy the generated WAR file to:
- **Apache Tomcat** (9.x or later)
- **WildFly**
- **Jetty**
- **Docker** (using the provided Dockerfile)

### Option 3: Docker
```bash
docker build -t networkprojects .
docker run -p 8080:8080 networkprojects
```

Then visit: **http://localhost:8080**

---

## 📚 Project Details

### 🔒 Security & Cryptography

#### 1. **Secure Login Simulator** 🔐
- Hash and verify passwords using BCrypt
- Simulate user authentication flow
- **Endpoint:** `POST /hashpassword`
- **Uses:** jBCrypt library

#### 2. **Password Strength Checker** 💪
- Evaluate password complexity
- Returns: Weak/Medium/Strong with score
- **Endpoint:** `POST /passwordchecker`
- **Criteria:** Length, uppercase, numbers, special characters

#### 3. **SQL Injection Detector** 🛡️
- Detect common SQL injection patterns
- Identifies: `' OR 1=1`, `UNION SELECT`, etc.
- **Endpoint:** `POST /sqlinjection`
- **Risk Levels:** Low/Medium/High

#### 4. **Password Breach Checker** 🔓
- Check if password appears in known breaches
- Uses: HIBP API (Have I Been Pwned)
- **Endpoint:** `POST /passwordbreachcheck`
- **Returns:** Breach count and recommendations

---

### 📄 Data Processing & Validation

#### 5. **JSON Validator** 📄
- Validate JSON syntax
- Pretty-print formatted output
- **Endpoint:** `POST /jsonvalidate`
- **Uses:** Jackson library

#### 6. **Text Similarity Checker** 📊
- Compare two texts for similarity
- Algorithm: Jaccard/Cosine similarity
- **Endpoint:** `POST /textsimilaritychecker`
- **Returns:** Similarity percentage & method

#### 7. **Log Reader** 📋
- Parse log files (INFO/WARNING/ERROR)
- Count log level occurrences
- **Endpoint:** `POST /logreader`
- **Accepts:** File upload

#### 8. **Resume Keyword Scanner** 📄
- Match skills against job requirements
- Calculate fit percentage
- **Endpoint:** `POST /resumekeywordscanner`
- **Returns:** Matched & missing skills

#### 9. **Search Engine** 🔍
- Keyword search across uploaded files
- Returns: File, line number, matched text
- **Endpoint:** `POST /searchengine`
- **Accepts:** Multiple file upload

---

### 🌐 Network & Web Tools

#### 10. **Website Uptime Checker** 🌐
- Ping URL and measure response time
- Get HTTP status codes
- **Endpoint:** `POST /websiteuptimechecker`
- **Returns:** Status, response time (ms), status code

#### 11. **HTTP Request Inspector** 🔬
- Inspect HTTP headers and metadata
- Measure response time
- **Endpoint:** `POST /httprequestinspector`
- **Returns:** Headers, content-type, server info

#### 12. **URL Metadata Extractor** 📈
- Extract title, meta description
- Count links on a page
- **Endpoint:** `POST /urlmetadataextracter`
- **Uses:** JSoup HTML parser

#### 13. **Robots.txt Parser** 🤖
- Fetch and parse robots.txt files
- Extract crawling rules
- **Endpoint:** `POST /robottextrules`
- **Returns:** Disallow rules, sitemap URL

#### 14. **Block Ads** 🚫
- Check if URL is in ad-block list
- Built-in ad domain database
- **Endpoint:** `POST /blockads`
- **Returns:** Blocked status & reason

---

### 🛠️ Utility Tools

#### 15. **Duplicate File Finder** 📂
- Find duplicate files using MD5 hashing
- Compare files in directory
- **Endpoint:** `POST /duplicatefilefinder`
- **Returns:** Groups of duplicate files

#### 16. **Integrity Verifier** ✅
- Hash two inputs and compare
- Detect data modifications
- **Endpoint:** `POST /verifyintegrity`
- **Uses:** SHA-256 hashing

#### 17. **Mail Sender** 📧
- Send emails via SMTP
- Configured with JavaMail API
- **Endpoint:** `POST /mail`
- **Requires:** SMTP configuration

#### 18. **Mail Pattern Generator** 📧
- Generate possible corporate email patterns
- Common formats: firstname.lastname@domain
- **Endpoint:** `POST /mailgenerator`
- **Returns:** Generated email addresses

#### 19. **Quotes API** 💬
- Fetch random inspirational quotes
- Filter by category
- **Endpoint:** `POST /quotesapi`
- **Returns:** Quote & author

---

### 📊 Business Logic Tools

#### 20. **Bug Tracker** 🐛
- Create and manage bug reports
- Track bug status (open/in-progress/closed)
- Set priority levels
- **Endpoint:** `POST /bugtracker`
- **Actions:** add, view, update

#### 21. **Room Booking System** 🏠
- Book time slots for rooms
- Cancel bookings
- View all bookings
- **Endpoint:** `POST /booking`
- **In-memory Storage:** HashMap

#### 22. **Socket Chat** 💬
- Real-time WebSocket communication
- Connect multiple users
- Send direct messages
- **Endpoint:** `WS /chat`
- **Protocol:** WebSocket

---

## 🔌 API Endpoints

### REST Endpoints

| Project | Method | Endpoint | Input |
|---------|--------|----------|-------|
| Secure Login | POST | `/hashpassword` | username, password, action |
| JSON Validate | POST | `/jsonvalidate` | jsonInput |
| Password Checker | POST | `/passwordchecker` | passStrength |
| Uptime Checker | POST | `/websiteuptimechecker` | urlInput |
| Text Similarity | POST | `/textsimilaritychecker` | text1, text2 |
| Search Engine | POST | `/searchengine` | files, keyword |
| Log Reader | POST | `/logreader` | log file |
| URL Metadata | POST | `/urlmetadataextracter` | urlMeta |
| Verify Integrity | POST | `/verifyintegrity` | input1, input2 |
| Duplicate Finder | POST | `/duplicatefilefinder` | dupDirPath |
| Block Ads | POST | `/blockads` | blockUrl |
| HTTP Inspector | POST | `/httprequestinspector` | inspectUrl |
| Mail Sender | POST | `/mail` | From, To, subject, text |
| Quotes API | POST | `/quotesapi` | query |
| Bug Tracker | POST | `/bugtracker` | action, title, priority, id, status |
| SQL Injection | POST | `/sqlinjection` | sqlUsername, sqlPassword |
| Breach Checker | POST | `/passwordbreachcheck` | breachPassword |
| Resume Scanner | POST | `/resumekeywordscanner` | resumeSkills, jobRoleSkills |
| Robots.txt | POST | `/robottextrules` | robotUrl |
| Room Booking | POST | `/booking` | action, name, date, slot, id |
| Mail Generator | POST | `/mailgenerator` | Name, Domain |

### Content-Type
All endpoints use: `Content-Type: application/json`

---

## 🔗 WebSocket Support

### Socket Chat

**Connection:**
```javascript
const ws = new WebSocket('ws://localhost:8080/networkprojects/chat');

// Send your name on connect
ws.onopen = () => ws.send('Alice');

// Receive online users list
ws.onmessage = (event) => {
    if (event.data.startsWith('online:')) {
        const users = event.data.substring(7).split(',');
    }
};

// Send private message
ws.send('Bob:Hello Bob!');  // format: "username:message"
```

**Message Format:**
- **Server → Client (Users):** `online:Alice,Bob,Carol`
- **Server → Client (Message):** `Alice → Bob: Hello Bob!`
- **Client → Server (Name):** `Alice`
- **Client → Server (Message):** `Bob:Hello Bob!`

---

## 📁 Project Structure

```
networkprojects/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── controller/          # Servlet endpoints (22 servlets)
│   │   │   ├── service/             # Business logic classes
│   │   │   ├── model/               # Data models (User, Bug, Booking)
│   │   │   ├── security/            # CORS filter
│   │   │   └── extensionaddblocker/ # Ad-blocking lists
│   │   ├── resources/               # Configuration files
│   │   └── webapp/
│   │       ├── index.html           # Main dashboard
│   │       └── WEB-INF/
│   │           └── web.xml          # Servlet mappings
│   └── test/
│       └── java/                    # Unit tests
├── pom.xml                          # Maven configuration
├── Dockerfile                       # Docker container setup
├── target/
│   └── Network-Project-1.0.war      # Built WAR package
└── README.md                        # This file
```

---

## 🎨 File Organization

### Controllers (Servlets)
- `HashPasswordServlet.java` - Password hashing
- `JsonValidateServlet.java` - JSON validation
- `PasswordCheckerServlet.java` - Strength calculation
- `WebSiteUptimeCheckerServlet.java` - Uptime monitoring
- `TextSimilarityCheckerServlet.java` - Text comparison
- `SearchEngineServlet.java` - File searching
- `LogReaderServlet.java` - Log analysis
- `UrlMetaExtractorServlet.java` - Metadata extraction
- `VerifyIntegrityServlet.java` - Hash comparison
- `DuplicateFileFinderServlet.java` - Duplicate detection
- `BlockAdsServlet.java` - Ad blocking
- `HttpInspectorServlet.java` - HTTP inspection
- `MailServlet.java` - Email sending
- `QuotesServlet.java` - Quote API
- `BugTrackerServlet.java` - Bug management
- `SqlVulnerableCheckerServlet.java` - SQL injection detection
- `PasswordBreachCheckerServlet.java` - Breach checking
- `ResumeKeywordScannerServlet.java` - Skill matching
- `RobotTextRulesServlet.java` - Robots.txt parsing
- `BookingServlet.java` - Room booking
- `ChatWebSocket.java` - WebSocket chat
- `MailPatternGenerator.java` - Email generation

### Models
- `User.java` - User data
- `Bug.java` - Bug tracker data
- `Booking.java` - Room booking data

### Services
Business logic for each controller

---

## 🌍 CORS Support

All endpoints have **CORS enabled** via `CorsFilter` for cross-origin requests.

**Allowed Methods:** `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`  
**Allowed Headers:** `Content-Type`, `Authorization`

---

## 📋 Example Usage

### 1. Check Password Strength
```bash
curl -X POST http://localhost:8080/passwordchecker \
  -H "Content-Type: application/json" \
  -d '{"passStrength":"MyPassword@123"}'
```

**Response:**
```json
{
  "strength": "Strong",
  "score": 4,
  "tips": ["Your password is secure"]
}
```

### 2. Check Website Uptime
```bash
curl -X POST http://localhost:8080/websiteuptimechecker \
  -H "Content-Type: application/json" \
  -d '{"urlInput":"https://example.com"}'
```

**Response:**
```json
{
  "status": "UP",
  "responseTime": 142,
  "statusCode": 200
}
```

### 3. Validate JSON
```bash
curl -X POST http://localhost:8080/jsonvalidate \
  -H "Content-Type: application/json" \
  -d '{"jsonInput":"{\"name\":\"Alice\",\"age\":30}"}'
```

**Response:**
```json
{
  "valid": true,
  "pretty": "{\n  \"name\": \"Alice\",\n  \"age\": 30\n}"
}
```

---

## 🔧 Configuration

### Database (Optional)
If using database features, configure in your servlet:
```java
String url = "jdbc:mysql://localhost:3306/networkdb";
String user = "root";
String password = "your_password";
Connection conn = DriverManager.getConnection(url, user, password);
```

### SMTP Configuration (Mail Sender)
Update in `MailServlet.java`:
```java
String host = "smtp.gmail.com";
String port = "587";
String sender = "your-email@gmail.com";
String password = "app-password";
```

### WebSocket Configuration
WS URL automatically detected from frontend:
```javascript
const protocol = window.location.protocol === "https:" ? "wss://" : "ws://";
const ws = new WebSocket(protocol + window.location.host + "/chat");
```

---

## 🧪 Testing

Run unit tests:
```bash
mvn test
```

Manual testing via dashboard:
1. Start the server
2. Open `http://localhost:8080`
3. Click any project tile
4. Fill in the form
5. Click the action button
6. View results and API details below

---

## 🐛 Troubleshooting

### Port Already in Use
```bash
# Kill process on port 8080
lsof -ti:8080 | xargs kill -9
```

### WebSocket Connection Error
- Ensure the server is running
- Check firewall settings
- Verify WebSocket support in container

### CORS Errors
- Verify `CorsFilter` is enabled
- Check request headers
- Ensure `*` origin is allowed

---

## 📈 Performance Tips

- Use **connection pooling** for database
- Enable **response caching** for static content
- Implement **rate limiting** for APIs
- Use **pagination** for large file uploads
- Enable **gzip compression** in server config

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. **Fork** this repository
2. **Create** a feature branch (`git checkout -b feature/NewProject`)
3. **Commit** your changes (`git commit -m 'Add new project'`)
4. **Push** to the branch (`git push origin feature/NewProject`)
5. **Create** a Pull Request

### Guidelines:
- Follow Java naming conventions
- Add JSDoc comments for public methods
- Update this README with new projects
- Include error handling and validation

---

## 📄 License

This project is licensed under the **MIT License** - see the LICENSE file for details.

---

## 👨‍💻 Author

**Tamil Selvan**  
GitHub: [@tamilselvan](https://github.com/tamilselvan)  
Email: [your-email@example.com]

---

## 🎓 Learning Resources

This project demonstrates:
- ✅ Java Servlet API (Jakarta EE)
- ✅ RESTful API design
- ✅ WebSocket real-time communication
- ✅ JSON processing with Jackson
- ✅ HTML scraping with JSoup
- ✅ Security (BCrypt, SQL injection detection)
- ✅ Frontend JavaScript with Fetch API
- ✅ Docker containerization
- ✅ Maven build automation

---

## 📞 Support

For issues and questions:
- 📝 Open an **Issue** on GitHub
- 💬 Start a **Discussion**
- 📧 Contact directly via email

---

## ⭐ Show Your Support

If you find this project useful, please give it a **star**! ⭐

---

## 🗺️ Roadmap

Future enhancements:
- [ ] Database persistence (MySQL integration)
- [ ] User authentication & JWT
- [ ] Rate limiting & API keys
- [ ] More mini projects (Cryptocurrency, AI, ML)
- [ ] Mobile app (React Native)
- [ ] Admin dashboard
- [ ] Project analytics

---

## 🎉 Thank You!

Thank you for using **Network Projects**! We hope you find these tools useful and the code educational. Happy coding! 🚀


