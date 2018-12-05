package io.josemyduarte.cliques.finder.social.twitter;

import io.josemyduarte.cliques.finder.User;
import twitter4j.TwitterException;

import java.util.List;

public class DefaultTwitterClient implements TwitterClient {

    private final TwitterWrapper twitter;

    private DefaultTwitterClient(TwitterWrapper twitter) {
        this.twitter = twitter;
    }

    public static DefaultTwitterClient newInstance() {
        return new DefaultTwitterClient(DefaultTwitterWrapper.newInstance());
    }

    public static DefaultTwitterClient newInstance(TwitterWrapper twitterWrapper) {
        return new DefaultTwitterClient(twitterWrapper);
    }

    @Override
    public List<String> retrieveFollowers(User user) throws TwitterException {
        return twitter.getFriendsNames(user.getUsername());
    }
}
