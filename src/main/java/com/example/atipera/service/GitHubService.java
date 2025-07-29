package com.example.atipera.service;

import com.example.atipera.errorHandler.NotFoundException;
import com.example.atipera.model.appResponse.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {
    private final RestTemplate restTemplate;

    public GitHubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Repository[] getRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        com.example.atipera.model.githubResponse.Repository[] repositories = restTemplate.getForObject(url, com.example.atipera.model.githubResponse.Repository[].class);

        Repository[] appResponse = new Repository[repositories.length];

        if (repositories.length == 0) {
            throw new NotFoundException(
                "No repositories found for user: " + username
            );
        }

        // Iterate through the repositories
        for (int i = 0; i < repositories.length; i++) {
            appResponse[i] = new Repository();
            appResponse[i].setRepositoryName(repositories[i].getRepositoryName());
            appResponse[i].setOwnerLogin(repositories[i].getOwnerLogin());

            com.example.atipera.model.githubResponse.Branch[] branches = restTemplate.getForObject(repositories[i].getBranchesUrl(), com.example.atipera.model.githubResponse.Branch[].class);
            for (com.example.atipera.model.githubResponse.Branch branch : branches) {
                com.example.atipera.model.appResponse.Branch appBranch = new com.example.atipera.model.appResponse.Branch();
                appBranch.setName(branch.getName());
                appBranch.setCommitSha(branch.getCommit().getSha());
                appResponse[i].addBranch(appBranch);
            }
        }

        return appResponse;
    }
}
