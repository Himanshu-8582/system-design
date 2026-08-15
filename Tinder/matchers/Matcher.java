package Tinder.matchers;

import Tinder.models.User;

public interface Matcher {
    double calculateMatchScore(User user1, User user2);
}
