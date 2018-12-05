package io.josemyduarte.cliques.finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper of User to hold social connections
 */
public class SocialUser {

    private final User user;
    private final List<String> twitterFollowersScreenName;
    private final List<String> githubOrganizations;

    private SocialUser(User user, List<String> twitterFollowersScreenName, List<String> githubOrganizations) {
        this.user = user;
        this.twitterFollowersScreenName = twitterFollowersScreenName;
        this.githubOrganizations = new ArrayList<>(githubOrganizations);
    }

    public static SocialUser from(User user, List<String> twitterFriendIds, List<String> githubOrganizations) {
        return new SocialUser(user, twitterFriendIds, githubOrganizations);
    }

    public String getUsername() {
        return user.getUsername();
    }

    public User getUser() {
        return user;
    }

    public List<String> getTwitterFollowersScreenName() {
        return twitterFollowersScreenName;
    }

    public List<String> getGithubOrganizations() {
        return githubOrganizations;
    }

    public boolean isFollowedBy(SocialUser user) {
        return this.getTwitterFollowersScreenName().contains(user.getUsername());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialUser that = (SocialUser) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(twitterFollowersScreenName, that.twitterFollowersScreenName) &&
                Objects.equals(githubOrganizations, that.githubOrganizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, twitterFollowersScreenName, githubOrganizations);
    }

    @Override
    public String toString() {
        return "SocialUser{" +
                "user=" + user +
                ", twitterFollowersScreenName=" + twitterFollowersScreenName +
                ", githubOrganizations=" + githubOrganizations +
                '}';
    }
}
