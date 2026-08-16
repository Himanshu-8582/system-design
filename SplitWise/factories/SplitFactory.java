package SplitWise.factories;

import SplitWise.enums.SplitType;
import SplitWise.strategies.EqualSplit;
import SplitWise.strategies.ExactSplit;
import SplitWise.strategies.PercentageSplit;
import SplitWise.strategies.SplitStrategy;

public class SplitFactory {
    public static SplitStrategy getSplitStrategy(SplitType type) {
        switch (type) {
            case EQUAL:
                return new EqualSplit();
            case EXACT:
                return new ExactSplit();
            case PERCENTAGE:
                return new PercentageSplit();
            default:
                return new EqualSplit();
        }
    }
}
