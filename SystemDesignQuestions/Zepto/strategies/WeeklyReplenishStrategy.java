package SystemDesignQuestions.Zepto.strategies;

import java.util.Map;

import SystemDesignQuestions.Zepto.managers.InventoryManager;

public class WeeklyReplenishStrategy implements ReplenishStrategy {
    public WeeklyReplenishStrategy() {}

    @Override
    public void replenish(InventoryManager manager, Map<Integer,Integer> itemsToReplenish) {
        System.out.println("[WeeklyReplenish] Weekly replenishment triggered for inventory.");
    }
}
