package io.josemyduarte.cliques.finder.social.twitter;

import twitter4j.TwitterException;

import java.util.List;

/**
 * Twitter SDK wrapper interface
 */
public interface TwitterWrapper {

    /**
     * Given a user retrieve a list of screen names of his followers
     *
     * @param username Twiiter's username to retrieve followers
     * @return a list of screen names of followers
     * @throws TwitterException if any communication with Twitter fails
     */
    List<String> getFriendsNames(String username) throws TwitterException;
}
