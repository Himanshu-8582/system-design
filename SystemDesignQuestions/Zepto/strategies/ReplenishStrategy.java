package SystemDesignQuestions.Zepto.strategies;

import java.util.Map;

import SystemDesignQuestions.Zepto.managers.InventoryManager;

public interface ReplenishStrategy {
    void replenish(InventoryManager manager, Map<Integer,Integer> itemsToReplenish);
}
