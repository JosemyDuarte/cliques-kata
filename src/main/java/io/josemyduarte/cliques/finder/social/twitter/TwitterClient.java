package io.josemyduarte.cliques.finder.social.twitter;

import io.josemyduarte.cliques.finder.User;
import twitter4j.TwitterException;

import java.util.List;

/**
 * Twitter client interface
 */
public interface TwitterClient {
    /**
     * Given a user retrieve a list of screen names of his followers
     *
     * @param user user to retrieve followers
     * @return a list of screen names of followers
     * @throws TwitterException if any communication with Twitter fails
     */
    List<String> retrieveFollowers(User user) throws TwitterException;
}
