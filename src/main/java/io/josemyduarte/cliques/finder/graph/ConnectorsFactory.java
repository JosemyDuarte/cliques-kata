package io.josemyduarte.cliques.finder.graph;

import io.josemyduarte.cliques.finder.social.github.GithubClient;
import io.josemyduarte.cliques.finder.social.twitter.TwitterClient;

public final class ConnectorsFactory {

    public static ConnectionBuilder defaultConnectionBuilder() {
        return DefaultConnectionBuilder.newInstance();
    }

    public static ConnectionBuilder defaultConnectionBuilder(final GithubClient githubClient, final TwitterClient twitterClient) {
        return DefaultConnectionBuilder.newInstance(githubClient, twitterClient);
    }

}
