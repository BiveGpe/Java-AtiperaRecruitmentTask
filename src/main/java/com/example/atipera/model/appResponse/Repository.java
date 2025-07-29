package com.example.atipera.model.appResponse;

public class Repository {
    private String repositoryName;
    private String ownerLogin;
    private Branch[] branches;

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public Branch[] getBranches() {
        return branches;
    }

    public void addBranch(Branch appBranch) {
        if (branches == null) {
            branches = new Branch[1];
            branches[0] = appBranch;
        } else {
            Branch[] newBranches = new Branch[branches.length + 1];
            System.arraycopy(branches, 0, newBranches, 0, branches.length);
            newBranches[branches.length] = appBranch;
            branches = newBranches;
        }
    }
}
