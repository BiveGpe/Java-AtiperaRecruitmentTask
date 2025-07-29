# GitHub Repositories API (Atipera Recruitment Task)

This Spring Boot application exposes a REST API that allows users to retrieve **non-fork GitHub repositories** for a given username, along with all branches and their latest commit SHAs.

## ✨ Features

- Lists all **non-fork** repositories for a given GitHub user
- For each repository:
    - Shows repository name
    - Owner login
    - All branches with last commit SHA
- Returns 404 with a custom message if GitHub user does not exist
- One integration test covers the happy path (business-relevant scenario)

## 🔧 Technologies

- Java 21
- Spring Boot 3.5
- Spring Web
- JUnit 5

## 📦 API Endpoint

### `GET /api/github/repositories/{username}`

#### ✅ Success Response (200 OK)

```json
[
  {
    "repositoryName": "cool-repo",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "main",
        "commitSha": "abc123def456..."
      },
      ...
    ]
  },
  ...
]
```

#### ❌ Error Response (404 Not Found)

```json
{
  "message": "GitHub user 'nonexistentuser' does not exist."
}
```

## 🚀 Deployment
This application can be run locally using the following command:

```bash
git clone https://github.com/your-username/github-repo-api.git
cd github-repo-api
./mvnw clean install
./mvnw spring-boot:run
```

