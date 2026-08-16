package SplitWise.strategies;

import java.util.List;

import SplitWise.models.Split;

public interface SplitStrategy {
    public List<Split> calculateSplit(double totalAmount, List<String> userIds, List<Double> values);
}
