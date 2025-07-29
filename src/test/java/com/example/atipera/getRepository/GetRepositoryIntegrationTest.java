package com.example.atipera.getRepository;

import com.example.atipera.controller.GitHubController;
import com.example.atipera.model.appResponse.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetRepositoryIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GitHubController gitHubController;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void shouldReturnRepositoriesForValidUsername() {
        String repositoriesResponse = """
                    [
                        {
                            "id": 749251097,
                            "name": "bivegpe.github.io",
                            "owner": {
                                "login": "BiveGpe"
                            },
                            "branches_url": "https://api.github.com/repos/BiveGpe/bivegpe.github.io/branches{/branch}"
                        }
                    ]
                """;

        String branchesResponse = """
                    [
                        {
                            "name": "master",
                            "commit": {
                                "sha": "e8ed94a8c7999f1e2a4aed3fc8f6d6a517134a5b"
                            }
                        }
                    ]
                """;

        // Mock external API calls
        mockServer.expect(requestTo("https://api.github.com/users/BiveGpe/repos"))
                .andRespond(withSuccess(repositoriesResponse, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("https://api.github.com/repos/BiveGpe/bivegpe.github.io/branches"))
                .andRespond(withSuccess(branchesResponse, MediaType.APPLICATION_JSON));

        // Given
        String username = "BiveGpe";

        // When
        ResponseEntity<Repository[]> response = gitHubController.getRepositories("BiveGpe");

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);

        for (Repository repository : response.getBody()) {
            assertThat(repository.getRepositoryName()).isEqualTo("bivegpe.github.io");
            assertThat(repository.getOwnerLogin()).isEqualTo(username);
            assertThat(repository.getBranches()).isNotNull();
            assertThat(repository.getBranches().length).isGreaterThan(0);

            assertThat(repository.getBranches()[0].getName()).isEqualTo("master");
            assertThat(repository.getBranches()[0].getCommitSha()).isEqualTo("e8ed94a8c7999f1e2a4aed3fc8f6d6a517134a5b");
        }

        mockServer.verify();
    }
}
