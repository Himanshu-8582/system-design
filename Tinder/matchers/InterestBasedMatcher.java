package Tinder.matchers;

import java.util.ArrayList;
import java.util.List;

import Tinder.models.Interest;
import Tinder.models.User;

public class InterestBasedMatcher implements Matcher {
    public double calculateMatchScore(User user1, User user2) {
        // First, check basic compatibility
        BasicMatcher basicMatcher = new BasicMatcher();
        double baseScore = basicMatcher.calculateMatchScore(user1, user2);

        if (baseScore == 0.0) {
            return 0.0; // No need to continue if basic criteria don't match
        }

        // Calculate score based on shared interests
        List<String> user1InterestNames = new ArrayList<>();
        for (Interest interest : user1.getProfile().getInterests()) {
            user1InterestNames.add(interest.getName());
        }

        int sharedInterests = 0;
        for (Interest interest : user2.getProfile().getInterests()) {
            if (user1InterestNames.contains(interest.getName())) {
                sharedInterests++;
            }
        }

        // Bonus score based on shared interests (up to 0.5 additional points)
        double maxInterests = Math.max(user1.getProfile().getInterests().size(), user2.getProfile().getInterests().size());
        double interestScore = maxInterests > 0 ? 0.5 * ((double) sharedInterests / maxInterests) : 0.0;

        return baseScore + interestScore;
    }
}
