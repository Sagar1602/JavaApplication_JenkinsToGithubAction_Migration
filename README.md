# Java CICD Practice Application

A complete Java Spring Boot application designed for practicing CI/CD pipelines with Jenkins and GitHub Actions.

## 📋 Project Structure

```
java-cicd-practice/
├── src/
│   ├── main/
│   │   ├── java/com/cicdpractice/
│   │   │   ├── CicdPracticeApplication.java    (Main App)
│   │   │   ├── controller/ApiController.java   (REST API)
│   │   │   └── service/AppService.java         (Business Logic)
│   │   └── resources/application.properties    (Config)
│   └── test/
│       └── java/com/cicdpractice/service/AppServiceTest.java
├── Jenkinsfile                  (Jenkins Pipeline)
├── .github/workflows/ci-cd.yml  (GitHub Actions)
├── Dockerfile                   (Docker Config)
├── pom.xml                      (Maven Config)
└── README.md                    (This file)
```

## 🛠️ Technologies Used

- **Java 17** - Programming Language
- **Spring Boot 3.1.0** - Web Framework
- **Maven** - Build Tool
- **Docker** - Containerization
- **Kubernetes** - Orchestration
- **Jenkins** - CI/CD Tool
- **GitHub Actions** - CI/CD Tool

## 📦 Prerequisites

- Java 17+ installed
- Maven 3.9.0+ installed
- Docker installed
- Git installed
- Jenkins (for Jenkinsfile practice)
- GitHub account (for GitHub Actions practice)

## 🚀 How to Run Locally

### Build the Application

```bash
# Clone the repository
git clone <your-repo-url>
cd java-cicd-practice

# Build with Maven
mvn clean package

# Run the application
java -jar target/java-cicd-app-1.0.0.jar
```

### Test the API

```bash
# Health check
curl http://localhost:8080/api/hello

# Get status
curl http://localhost:8080/api/status

# Get info
curl http://localhost:8080/api/info

# Deploy (POST request)
curl -X POST http://localhost:8080/api/deploy
```

## 🐳 Docker Usage

```bash
# Build Docker image
docker build -t java-cicd-app:1.0.0 .

# Run container
docker run -p 8080:8080 java-cicd-app:1.0.0

# Push to DockerHub
docker tag java-cicd-app:1.0.0 yourusername/java-cicd-app:1.0.0
docker push yourusername/java-cicd-app:1.0.0
```

## 📝 Practice Exercises

### Exercise 1: Understanding Jenkinsfile

Study the `Jenkinsfile` and practice:

1. **Modify the Jenkinsfile:**
   - Add a new stage for "Security Scan"
   - Add email notifications on failure
   - Add approval before deployment

2. **Run in Jenkins:**
   - Create a new Pipeline job in Jenkins
   - Point to this repository
   - Trigger builds manually

**Task:** Add a stage to check code quality with SonarQube

```groovy
stage('SonarQube Analysis') {
    steps {
        script {
            echo "=== Running SonarQube ==="
            sh 'mvn sonar:sonar -Dsonar.projectKey=java-cicd-app'
        }
    }
}
```

---

### Exercise 2: Understanding GitHub Actions

Study the `.github/workflows/ci-cd.yml` and practice:

1. **Modify the Workflow:**
   - Add a job to test on multiple Java versions
   - Add scheduled runs (cron jobs)
   - Add approval before deployment

2. **Push to GitHub:**
   - Create a GitHub repository
   - Push this code
   - Watch the workflow run automatically

**Task:** Add a matrix build for multiple Java versions

```yaml
java-versions: ['17', '21']
strategy:
  matrix:
    java-version: ${{ env.java-versions }}
```

---

### Exercise 3: Create Your Own Pipeline

Create a new Jenkinsfile with these stages:

1. **Checkout** - Pull code from GitHub
2. **Build** - Compile Java code
3. **Test** - Run unit tests
4. **Analyze** - Code quality check
5. **Package** - Create Docker image
6. **Push** - Push to registry
7. **Deploy** - Deploy to Kubernetes
8. **Verify** - Health check

---

### Exercise 4: GitHub Actions Workflow

Create a new workflow file with:

1. **Trigger on:** push to main, pull requests
2. **Jobs:**
   - Build & Test
   - Security Scan
   - Docker Build & Push
   - Deploy to K8s
3. **Add notifications** on completion

---

## 📚 Key Jenkinsfile Concepts

| Concept | Example |
|---------|---------|
| **Agent** | `agent any` - Run on any available executor |
| **Tools** | `maven`, `jdk`, `docker` - Define tool versions |
| **Environment** | `APP_NAME = "value"` - Global variables |
| **Stages** | `stage('Build')` - Logical workflow steps |
| **Steps** | `sh`, `echo`, `docker build` - Commands |
| **When** | `when { branch 'main' }` - Conditional execution |
| **Post** | `always`, `success`, `failure` - Cleanup & notifications |

---

## 📚 Key GitHub Actions Concepts

| Concept | Example |
|---------|---------|
| **on** | `push`, `pull_request`, `schedule` - Triggers |
| **jobs** | `build`, `test`, `deploy` - Parallel/sequential |
| **runs-on** | `ubuntu-latest` - Runner environment |
| **steps** | `uses`, `run` - Actions or shell commands |
| **needs** | `needs: [build, test]` - Job dependencies |
| **if** | `if: github.ref == 'refs/heads/main'` - Conditions |
| **secrets** | `${{ secrets.TOKEN }}` - Sensitive data |

---

## 🎯 Practice Tasks

### Task 1: Add Parallel Testing
Modify both pipelines to run tests in parallel for different test suites.

### Task 2: Add Approval Steps
Add manual approval before deploying to production in both Jenkins and GitHub Actions.

### Task 3: Add Notifications
- Jenkins: Email notifications on build failure
- GitHub Actions: Slack notifications on completion

### Task 4: Add Security Scanning
Integrate SonarQube in Jenkinsfile and SAST scanning in GitHub Actions.

### Task 5: Create Separate Environments
Add separate pipelines for:
- Dev environment (auto-deploy)
- Staging (manual approval)
- Production (requires two approvals)

---

## 🔧 Troubleshooting

### Maven build fails
```bash
mvn clean install -X  # Enable debug mode
```

### Docker build fails
```bash
docker build --no-cache -t java-cicd-app:1.0.0 .
```

### Tests failing locally
```bash
mvn test -Dtest=AppServiceTest  # Run specific test
```

### Jenkinsfile syntax errors
```groovy
// Use Jenkins Pipeline Validator
// Jenkins > Manage Jenkins > Pipeline Syntax
```

---

## 🌟 Learning Path

1. **Week 1:** Build & Test locally
2. **Week 2:** Understand Jenkinsfile
3. **Week 3:** Set up Jenkins job
4. **Week 4:** Understand GitHub Actions
5. **Week 5:** Practice modifications
6. **Week 6:** Deploy to Kubernetes

---

## 📖 Resources

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Jenkins Documentation](https://jenkins.io/doc/)
- [GitHub Actions Docs](https://docs.github.com/en/actions)
- [Docker Docs](https://docs.docker.com/)
- [Kubernetes Docs](https://kubernetes.io/docs/)

---

## 🤝 Contributing

Modify and improve this project:
- Add new API endpoints
- Improve test coverage
- Add more stages to pipelines
- Add configuration management
- Add monitoring/logging

---

## 📄 License

Free to use for learning purposes.

---

**Happy Learning! 🚀**

For any questions, refer to the individual Jenkinsfile and GitHub Actions workflow files.
