package io.josemyduarte.cliques.finder;

import io.josemyduarte.cliques.MotherClazz;
import io.josemyduarte.cliques.finder.graph.ConnectionBuilder;
import io.josemyduarte.cliques.finder.graph.ConnectorsFactory;
import io.josemyduarte.cliques.finder.social.github.GithubClient;
import io.josemyduarte.cliques.finder.social.github.GithubException;
import io.josemyduarte.cliques.finder.social.twitter.TwitterClient;
import org.junit.Test;
import twitter4j.TwitterException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultConnectionBuilderTest {

    @Test
    public void givenAUser_shouldCreateASocialUser() throws TwitterException, GithubException {
        User adam = new User("Adam");
        SocialUser socialAdam = MotherClazz.socialUserBuilder(adam);
        ConnectionBuilder connectionBuilder = mock(ConnectionBuilder.class);
        when(connectionBuilder.build(adam)).thenReturn(socialAdam);
        SocialUser actualSocialUser = connectionBuilder.build(adam);

        assertEquals("Should return a SocialUser", socialAdam, actualSocialUser);
    }

    @Test
    public void givenAUser_shouldGetFriendsNameOnTwitter() throws TwitterException, GithubException {
        User adam = new User("Adam");
        SocialUser socialAdam = MotherClazz.socialUserBuilder(adam);
        GithubClient githubClient = mock(GithubClient.class);
        TwitterClient twitterClient = mock(TwitterClient.class);

        when(twitterClient.retrieveFollowers(adam)).thenReturn(socialAdam.getTwitterFollowersScreenName());

        ConnectionBuilder connectionBuilder = ConnectorsFactory.defaultConnectionBuilder(githubClient, twitterClient);

        SocialUser actualSocialUser = connectionBuilder.build(adam);

        assertEquals("SocialUser should have a list of twitter friends", socialAdam.getTwitterFollowersScreenName(), actualSocialUser.getTwitterFollowersScreenName());

    }

    @Test
    public void givenAUser_shouldGetOrganizationsOnGithub() throws GithubException, TwitterException {
        User adam = new User("Adam");
        SocialUser socialAdam = MotherClazz.socialUserBuilder(adam);
        GithubClient githubClient = mock(GithubClient.class);
        TwitterClient twitterClient = mock(TwitterClient.class);

        when(githubClient.retrieveOrganizations(adam)).thenReturn(socialAdam.getGithubOrganizations());

        ConnectionBuilder connectionBuilder = ConnectorsFactory.defaultConnectionBuilder(githubClient, twitterClient);

        SocialUser actualSocialUser = connectionBuilder.build(adam);

        assertEquals("SocialUser should have a list of organizations", socialAdam.getGithubOrganizations(), actualSocialUser.getGithubOrganizations());

    }
}