package Tinder.matchers;

import Tinder.models.User;

public class LocationBasedMatcher implements Matcher {
    public double calculateMatchScore(User user1, User user2) {
        // First, check basic compatibility
        InterestBasedMatcher interestsMatcher = new InterestBasedMatcher();
        double baseScore = interestsMatcher.calculateMatchScore(user1, user2);

        if (baseScore == 0.0) {
            return 0.0; // No need to continue if basic criteria don't match
        }

        // Calculate score based on proximity
        double distance = user1.getProfile().getLocation().distanceInKm(user2.getProfile().getLocation());
        double maxDistance = Math.min(user1.getPreference().getMaxDistance(), user2.getPreference().getMaxDistance());

        // Closer is better, score decreases with distance (up to 0.2 additional points)
        double proximityScore = maxDistance > 0 ? 0.2 * (1.0 - (distance / maxDistance)) : 0.0;

        return baseScore + proximityScore;
    }
}
