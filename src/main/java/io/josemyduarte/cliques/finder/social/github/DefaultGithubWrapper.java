package io.josemyduarte.cliques.finder.social.github;

import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHPersonSet;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class DefaultGithubWrapper implements GithubWrapper {

    private GitHub gitHub;

    private DefaultGithubWrapper() {
        try {
            this.gitHub = GitHub.connect();
        } catch (IOException e) {
            throw new GithubRuntimeException("Couldn't create github connection. Check credentials file.", e);
        }
    }

    static DefaultGithubWrapper newInstance() {
        return new DefaultGithubWrapper();
    }

    @Override
    public List<String> getOrganizationsNamesFrom(final String username) throws GithubException {
        try {
            GHUser ghUser = gitHub.getUser(username);
            GHPersonSet<GHOrganization> organizations = ghUser.getOrganizations();
            return organizations.parallelStream()
                    .map(this::getName)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new GithubException("Problems retrieving user's info.", e);
        }
    }

    private String getName(final GHOrganization organization) {
        try {
            return organization.getName();
        } catch (IOException e) {
            throw new GithubRuntimeException("Problems reading organizations names.", e);
        }
    }

}
