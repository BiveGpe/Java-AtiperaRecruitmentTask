package com.example.atipera.model.githubResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Repository {
    @JsonProperty("name")
    private String repositoryName;

    @JsonProperty("owner")
    private Owner owner;

    @JsonProperty("branches_url")
    private String branchesUrl;

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return owner != null ? owner.getLogin() : null;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getBranchesUrl() {
        return branchesUrl.replace("{/branch}", "");
    }

    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    // Inner class to map the owner object
    public static class Owner {
        @JsonProperty("login")
        private String login;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }
}
