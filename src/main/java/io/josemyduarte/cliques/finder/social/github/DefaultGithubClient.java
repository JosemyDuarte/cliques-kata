package io.josemyduarte.cliques.finder.social.github;

import io.josemyduarte.cliques.finder.User;

import java.util.List;

public class DefaultGithubClient implements GithubClient {

    private final GithubWrapper gitHub;

    private DefaultGithubClient(final GithubWrapper gitHub) {
        this.gitHub = gitHub;
    }

    public static GithubClient newInstance() {
        return new DefaultGithubClient(DefaultGithubWrapper.newInstance());
    }

    public static GithubClient newInstance(final GithubWrapper gitHub) {
        return new DefaultGithubClient(gitHub);
    }

    @Override
    public List<String> retrieveOrganizations(final User user) throws GithubException {
        return gitHub.getOrganizationsNamesFrom(user.getUsername());
    }

}
