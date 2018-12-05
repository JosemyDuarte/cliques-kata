package io.josemyduarte.cliques.finder.social.twitter;

import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.List;
import java.util.stream.Collectors;

class DefaultTwitterWrapper implements TwitterWrapper {

    private final Twitter twitter;

    private DefaultTwitterWrapper(Twitter twitter) {
        this.twitter = twitter;
    }

    public static TwitterWrapper newInstance() {
        Twitter twitter = TwitterFactory.getSingleton();
        return new DefaultTwitterWrapper(twitter);
    }

    @Override
    public List<String> getFriendsNames(String username) throws TwitterException {
        PagableResponseList<twitter4j.User> followers = twitter.getFollowersList(username, -1);
        return followers.stream()
                .map(twitter4j.User::getScreenName)
                .collect(Collectors.toList());

    }
}
