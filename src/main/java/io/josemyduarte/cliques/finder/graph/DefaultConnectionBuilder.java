package io.josemyduarte.cliques.finder.graph;

import io.josemyduarte.cliques.finder.SocialUser;
import io.josemyduarte.cliques.finder.User;
import io.josemyduarte.cliques.finder.social.github.DefaultGithubClient;
import io.josemyduarte.cliques.finder.social.github.GithubClient;
import io.josemyduarte.cliques.finder.social.github.GithubException;
import io.josemyduarte.cliques.finder.social.github.GithubRuntimeException;
import io.josemyduarte.cliques.finder.social.twitter.DefaultTwitterClient;
import io.josemyduarte.cliques.finder.social.twitter.TwitterClient;
import io.josemyduarte.cliques.finder.social.twitter.TwitterRuntimeException;
import twitter4j.TwitterException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Default implementation of ConnectionBuilder
 * That retrieve all the Twitter's followers and Github Organizations
 */
class DefaultConnectionBuilder implements ConnectionBuilder {

    private final GithubClient githubClient;
    private final TwitterClient twitterClient;

    private DefaultConnectionBuilder(final GithubClient githubClient, final TwitterClient twitterClient) {
        this.githubClient = githubClient;
        this.twitterClient = twitterClient;
    }

    public static ConnectionBuilder newInstance() {
        return new DefaultConnectionBuilder(DefaultGithubClient.newInstance(), DefaultTwitterClient.newInstance());
    }

    public static ConnectionBuilder newInstance(final GithubClient githubClient, final TwitterClient twitterClient) {
        return new DefaultConnectionBuilder(githubClient, twitterClient);
    }

    @Override
    public Set<SocialUser> build(final Set<User> users) {
        return users.stream()
                .map(this::buildConnection)
                .collect(Collectors.toSet());
    }

    @Override
    public SocialUser build(final User user) throws TwitterException, GithubException {
        List<String> twitterFollowersNames = twitterClient.retrieveFollowers(user);
        List<String> organizations = githubClient.retrieveOrganizations(user);
        return SocialUser.from(user, twitterFollowersNames, organizations);
    }

    private SocialUser buildConnection(User user) {
        try {
            return build(user);
        } catch (TwitterException e) {
            throw new TwitterRuntimeException(e.getMessage(), e);
        } catch (GithubException e) {
            throw new GithubRuntimeException(e.getMessage(), e);
        }
    }

}
