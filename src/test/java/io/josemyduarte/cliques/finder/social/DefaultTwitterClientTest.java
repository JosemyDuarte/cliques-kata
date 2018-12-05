package io.josemyduarte.cliques.finder.social;

import io.josemyduarte.cliques.MotherClazz;
import io.josemyduarte.cliques.finder.SocialUser;
import io.josemyduarte.cliques.finder.User;
import io.josemyduarte.cliques.finder.social.twitter.DefaultTwitterClient;
import io.josemyduarte.cliques.finder.social.twitter.TwitterClient;
import io.josemyduarte.cliques.finder.social.twitter.TwitterWrapper;
import org.junit.Assert;
import org.junit.Test;
import twitter4j.TwitterException;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultTwitterClientTest {

    @Test
    public void retrieveFriends() throws TwitterException {
        TwitterWrapper twitterWrapper = mock(TwitterWrapper.class);

        SocialUser jose = MotherClazz.socialUserBuilder(new User("Jose"));

        when(twitterWrapper.getFriendsNames(jose.getUsername())).thenReturn(jose.getTwitterFollowersScreenName());

        TwitterClient twitterClient = DefaultTwitterClient.newInstance(twitterWrapper);

        List<String> friendsNames = twitterClient.retrieveFollowers(jose.getUser());

        Assert.assertEquals("Should return a list of followers names", jose.getTwitterFollowersScreenName(), friendsNames);
    }
}