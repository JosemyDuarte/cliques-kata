package io.josemyduarte.cliques.finder.social.github;

import io.josemyduarte.cliques.finder.User;

import java.util.List;

/**
 * Github client interface
 */
public interface GithubClient {

    /**
     * Return a list of a user's organizations.
     *
     * @param user user to retrieve organizations
     * @return list of organizations names
     * @throws GithubException if any problem with authentication or network
     */
    List<String> retrieveOrganizations(User user) throws GithubException;
}
