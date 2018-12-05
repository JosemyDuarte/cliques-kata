package io.josemyduarte.cliques.finder.social.github;

import java.util.List;

/**
 * Github SDK wrapper
 */
public interface GithubWrapper {

    /**
     * Return a list of a user's organizations.
     *
     * @param username user to retrieve organizations
     * @return list of organizations names
     * @throws GithubException if any problem with authentication or network
     */
    List<String> getOrganizationsNamesFrom(String username) throws GithubException;
}
