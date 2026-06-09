# 🎓 CI/CD Practice Guide - Jenkins & GitHub Actions

This guide will help you practice writing Jenkinsfile and GitHub Actions workflows.

---

## 📋 What You'll Learn

### Jenkinsfile Concepts
- Pipeline structure and syntax
- Agents and tools
- Stages and steps
- Environment variables
- Conditional execution (when)
- Post actions
- Error handling
- Docker integration
- Kubernetes deployment

### GitHub Actions Concepts
- Workflow triggers (on)
- Jobs and steps
- Runners and images
- Actions and commands
- Conditional execution (if)
- Artifacts and caching
- Matrix builds
- Secrets and variables

---

## 🎯 Progressive Learning Path

### Level 1: Basic Concepts (Week 1)

#### Jenkins - Simple Pipeline
Study the basic Jenkinsfile structure:
```groovy
pipeline {
    agent any
    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
}
```

**Task:** Create a Jenkinsfile that:
1. Checks out code
2. Prints "Build Started"
3. Prints "Build Completed"

#### GitHub Actions - Simple Workflow
Study the basic workflow:
```yaml
name: Simple Workflow
on: [push]
jobs:
  hello:
    runs-on: ubuntu-latest
    steps:
      - run: echo "Hello World"
```

**Task:** Create a workflow that:
1. Triggers on push
2. Runs on Ubuntu
3. Prints "Build Started"
4. Prints "Build Completed"

---

### Level 2: Build & Test (Week 2)

#### Jenkins - Build & Test
Modify Jenkinsfile to:
```groovy
stage('Build') {
    steps {
        sh 'mvn clean package'
    }
}

stage('Test') {
    steps {
        sh 'mvn test'
    }
}
```

**Task:** Add these stages and make sure:
- Maven builds successfully
- Unit tests run
- Build artifacts are generated

#### GitHub Actions - Build & Test
Modify workflow to:
```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
      - run: mvn clean package
      - run: mvn test
```

**Task:** Create a workflow that:
- Checks out code
- Sets up Java 17
- Builds with Maven
- Runs tests

---

### Level 3: Docker & Registry (Week 3)

#### Jenkins - Docker Build & Push
```groovy
stage('Build Docker Image') {
    steps {
        sh "docker build -t myapp:${BUILD_NUMBER} ."
    }
}

stage('Push to Registry') {
    steps {
        withCredentials([usernamePassword(credentialsId: 'docker-cred',
                usernameVariable: 'USER',
                passwordVariable: 'PASS')]) {
            sh "docker login -u $USER -p $PASS"
            sh "docker push myapp:${BUILD_NUMBER}"
        }
    }
}
```

**Task:** Add Docker stages to build and push images to DockerHub

#### GitHub Actions - Docker Build & Push
```yaml
- uses: docker/setup-buildx-action@v2
- uses: docker/login-action@v2
  with:
    registry: ghcr.io
    username: ${{ github.actor }}
    password: ${{ secrets.GITHUB_TOKEN }}
- uses: docker/build-push-action@v4
  with:
    context: .
    push: true
    tags: ghcr.io/${{ github.repository }}:latest
```

**Task:** Create a job that builds and pushes Docker image

---

### Level 4: Deployment (Week 4)

#### Jenkins - Deploy to Kubernetes
```groovy
stage('Deploy') {
    when { branch 'main' }
    steps {
        sh '''
            kubectl set image deployment/myapp \
            myapp=myapp:${BUILD_NUMBER} -n production
        '''
    }
}
```

**Task:** Add deployment stage that:
- Only runs on main branch
- Updates Kubernetes deployment
- Verifies deployment success

#### GitHub Actions - Deploy to Kubernetes
```yaml
deploy:
  needs: [build, docker]
  runs-on: ubuntu-latest
  steps:
    - uses: azure/setup-kubectl@v3
    - run: kubectl apply -f k8s-deployment.yaml
    - run: kubectl rollout status deployment/java-cicd-app
```

**Task:** Create deployment job that applies K8s manifests

---

### Level 5: Advanced Features (Week 5)

#### Jenkins - Advanced Features
Add these features:

1. **Email Notifications**
```groovy
post {
    failure {
        emailext(
            subject: "Build Failed",
            body: "Check console for details",
            to: "admin@example.com"
        )
    }
}
```

2. **Approval Steps**
```groovy
stage('Deploy to Production') {
    input {
        message "Deploy to production?"
        ok "Proceed"
    }
    steps {
        sh 'kubectl apply -f k8s-deployment.yaml'
    }
}
```

3. **Parallel Execution**
```groovy
parallel {
    stage('Unit Tests') { steps { sh 'mvn test' } }
    stage('Integration Tests') { steps { sh 'mvn verify' } }
}
```

#### GitHub Actions - Advanced Features
Add these features:

1. **Matrix Build**
```yaml
strategy:
  matrix:
    java-version: ['17', '21']
steps:
  - uses: actions/setup-java@v3
    with:
      java-version: ${{ matrix.java-version }}
```

2. **Conditional Steps**
```yaml
- name: Deploy
  if: github.ref == 'refs/heads/main'
  run: kubectl apply -f k8s-deployment.yaml
```

3. **Caching**
```yaml
- uses: actions/cache@v3
  with:
    path: ~/.m2/repository
    key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
```

---

## 🔥 Hands-On Exercises

### Exercise 1: Multi-Stage Pipeline
**Objective:** Create a complete pipeline with all stages

**Jenkins Task:**
1. Checkout code
2. Build application
3. Run tests
4. Analyze code quality (mock)
5. Build Docker image
6. Push to registry (with approval)
7. Deploy to K8s (main branch only)
8. Verify deployment
9. Send notification

**GitHub Actions Task:**
Same as above but using GitHub Actions workflow

---

### Exercise 2: Conditional Execution
**Objective:** Learn when to run stages based on conditions

**Tasks:**
1. Deploy only when:
   - Code pushed to main branch
   - All tests passed
   - Manual approval given

2. Send notifications only:
   - On failure
   - On successful deployment
   - On approval request

---

### Exercise 3: Error Handling
**Objective:** Handle failures gracefully

**Tasks:**
1. Add retry logic for flaky tests
2. Cleanup on failure
3. Rollback on deployment failure
4. Send alerts on critical failures

**Jenkins Example:**
```groovy
post {
    failure {
        sh 'kubectl rollout undo deployment/myapp -n production'
    }
    always {
        cleanWs()
    }
}
```

---

### Exercise 4: Security
**Objective:** Secure credentials and secrets

**Tasks:**
1. Store DockerHub credentials securely
2. Use GitHub secrets for API keys
3. Never commit sensitive data
4. Use least privilege principle

**Jenkins Example:**
```groovy
withCredentials([usernamePassword(credentialsId: 'my-secret', 
        usernameVariable: 'USER', 
        passwordVariable: 'PASS')]) {
    sh 'echo Credentials are masked'
}
```

**GitHub Actions Example:**
```yaml
env:
  API_KEY: ${{ secrets.API_KEY }}
run: echo "Using secret: $API_KEY"
```

---

## 📊 Practice Checklist

### Jenkinsfile Mastery

- [ ] Create simple pipeline with echo statements
- [ ] Add Checkout, Build, Test stages
- [ ] Use environment variables
- [ ] Implement conditional execution (when)
- [ ] Add post actions (success/failure/always)
- [ ] Integrate Docker build & push
- [ ] Add Kubernetes deployment
- [ ] Implement approval gates
- [ ] Add email notifications
- [ ] Handle errors and rollbacks
- [ ] Implement parallel execution
- [ ] Add security scanning
- [ ] Create reusable shared libraries
- [ ] Monitor pipeline metrics

### GitHub Actions Mastery

- [ ] Create simple workflow with runs
- [ ] Setup Java and Maven environment
- [ ] Run tests and build
- [ ] Upload test artifacts
- [ ] Build and push Docker image
- [ ] Deploy to Kubernetes
- [ ] Implement matrix builds
- [ ] Add conditional steps
- [ ] Use secrets properly
- [ ] Cache dependencies
- [ ] Add workflow badges
- [ ] Implement approval workflows
- [ ] Add notifications
- [ ] Monitor workflow runs

---

## 🎓 Self-Assessment

After completing each level, answer:

- Can you explain the purpose of each stage?
- Can you modify the pipeline for your own project?
- Can you troubleshoot common errors?
- Can you add security best practices?
- Can you optimize performance?
- Can you document the pipeline?

---

## 📚 Resources

### Jenkins Learning
- Official Jenkins Documentation
- Jenkins Pipeline Syntax
- Groovy Programming Language
- Jenkins Shared Libraries

### GitHub Actions Learning
- GitHub Actions Documentation
- YAML Syntax
- Actions Marketplace
- GitHub Advanced Security

### General CI/CD
- The DevOps Handbook
- CI/CD Best Practices
- Docker & Kubernetes Documentation
- SonarQube Documentation

---

## 🚀 Real-World Tips

1. **Start Simple** - Master basics before advanced features
2. **Test Locally** - Use docker-compose for local testing
3. **Document Well** - Add comments explaining your pipeline
4. **Version Control** - Track pipeline changes like code
5. **Monitor Metrics** - Track build times and success rates
6. **Security First** - Never hardcode credentials
7. **Automate Everything** - Manual steps slow you down
8. **Fail Fast** - Catch errors early in pipeline
9. **Parallel Execution** - Run tests and builds in parallel
10. **Rollback Plan** - Always have a way to revert

---

## ✅ Completion Criteria

You've mastered Jenkins & GitHub Actions when you can:

✓ Write a complete CI/CD pipeline from scratch
✓ Deploy applications to Kubernetes automatically
✓ Handle failures and rollbacks gracefully
✓ Secure credentials and secrets properly
✓ Optimize pipeline performance
✓ Monitor and troubleshoot issues
✓ Teach others how to use these tools
✓ Implement best practices consistently

---

**Happy Learning! Keep practicing! 🎉**
