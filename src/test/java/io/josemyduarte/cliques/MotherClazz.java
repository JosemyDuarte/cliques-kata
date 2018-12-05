package io.josemyduarte.cliques;

import io.josemyduarte.cliques.finder.SocialUser;
import io.josemyduarte.cliques.finder.User;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MotherClazz {

    private MotherClazz() {
    }

    public static SocialUser socialUserBuilder(User user) {
        List<String> friendsIds = new ArrayList<>();
        List<String> organizationIds = new ArrayList<>();
        int nTwitterFriends = ThreadLocalRandom.current().nextInt(0, 10);
        int nOrganizations = ThreadLocalRandom.current().nextInt(0, 10);

        for (int i = 0; i < nTwitterFriends; i++) {
            friendsIds.add(user.getUsername() + " - friend #" + i);
        }

        for (int i = 0; i < nOrganizations; i++) {
            organizationIds.add("organization #" + i);
        }

        return SocialUser.from(user, friendsIds, organizationIds);
    }

    public static Set<SocialUser> createRandomUsers(int numberOfUsersToCreate) {
        Set<User> users = new TreeSet<>();

        for (int i = 0; i < numberOfUsersToCreate; i++) {
            int randomId = ThreadLocalRandom.current().nextInt(1, 10000000);
            users.add(new User("RandomUserName#" + randomId));
        }

        return users.stream()
                .map(MotherClazz::socialUserBuilder)
                .collect(Collectors.toSet());
    }

    public static Set<SocialUser> createListWithCliques(Set<User> users) {

        Set<SocialUser> genericSocialUsers = createRandomUsers(7);

        List<String> organizationsNames = Collections.singletonList("MyOrganizationOnGithub");

        List<SocialUser> socialUsersOnClique = users.stream()
                .map(user -> createClique(user, users, organizationsNames))
                .collect(Collectors.toList());

        return Stream.concat(genericSocialUsers.stream(), socialUsersOnClique.stream())
                .collect(Collectors.toSet());
    }

    private static SocialUser createClique(User user, Set<User> users, List<String> organizationsNames) {
        List<String> twitterFriends = new ArrayList<>(createTwitterFriendsClique(users));
        twitterFriends.remove(user.getUsername());
        return SocialUser.from(user, twitterFriends, organizationsNames);
    }

    private static Set<String> createTwitterFriendsClique(Set<User> users) {
        return users.stream()
                .map(User::getUsername)
                .collect(Collectors.toSet());
    }
}
