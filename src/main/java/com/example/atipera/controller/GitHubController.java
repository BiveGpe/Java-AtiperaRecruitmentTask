package com.example.atipera.controller;

import com.example.atipera.model.appResponse.Repository;
import com.example.atipera.service.GitHubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/github")
public class GitHubController {
    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<Repository[]> getRepositories(@PathVariable String username) {
        return ResponseEntity.ok(gitHubService.getRepositories(username));
    }
}
