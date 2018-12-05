package io.josemyduarte.cliques.finder.graph;

import io.josemyduarte.cliques.finder.SocialUser;
import io.josemyduarte.cliques.finder.User;
import io.josemyduarte.cliques.finder.social.github.GithubException;
import twitter4j.TwitterException;

import java.util.Set;

/**
 * Create Social Users with all required connections
 * (i.e Twiiter followers, Github organizations, Facebook friends, etc...)
 */
public interface ConnectionBuilder {

    /**
     * Given a set of users builds all their connections
     *
     * @param users a set of users to create external connections
     * @return a set of users with all the required connections
     */
    Set<SocialUser> build(Set<User> users);

    /**
     * Given a user builds all his connections
     *
     * @param user user to create external connections
     * @return a user with all the required connections
     */
    SocialUser build(User user) throws TwitterException, GithubException;
}
