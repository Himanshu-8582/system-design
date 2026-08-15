package Tinder.factories;

import Tinder.enums.MatcherType;
import Tinder.matchers.BasicMatcher;
import Tinder.matchers.InterestBasedMatcher;
import Tinder.matchers.LocationBasedMatcher;
import Tinder.matchers.Matcher;

public class MatcherFactory {
    public static Matcher createMatcher(MatcherType type) {
        switch (type) {
            case BASIC:
                return new BasicMatcher();
            case INTERESTS_BASED:
                return new InterestBasedMatcher();
            case LOCATION_BASED:
                return new LocationBasedMatcher();
            default:
                return new BasicMatcher();
        }
    }
}
