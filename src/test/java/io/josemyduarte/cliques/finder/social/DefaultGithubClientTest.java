package io.josemyduarte.cliques.finder.social;

import io.josemyduarte.cliques.MotherClazz;
import io.josemyduarte.cliques.finder.SocialUser;
import io.josemyduarte.cliques.finder.User;
import io.josemyduarte.cliques.finder.social.github.DefaultGithubClient;
import io.josemyduarte.cliques.finder.social.github.GithubClient;
import io.josemyduarte.cliques.finder.social.github.GithubException;
import io.josemyduarte.cliques.finder.social.github.GithubWrapper;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultGithubClientTest {

    @Test
    public void givenAUser_shouldRetrieveOrganizationsNames() throws GithubException {

        GithubWrapper githubWrapper = mock(GithubWrapper.class);
        SocialUser josemy = MotherClazz.socialUserBuilder(new User("Josemy"));

        when(githubWrapper.getOrganizationsNamesFrom(josemy.getUsername())).thenReturn(josemy.getGithubOrganizations());

        GithubClient githubClient = DefaultGithubClient.newInstance(githubWrapper);

        Assert.assertEquals("Should return a list of organizations names",
                josemy.getGithubOrganizations(),
                githubClient.retrieveOrganizations(josemy.getUser()));
    }
}