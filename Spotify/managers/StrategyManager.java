package Spotify.managers;

import Spotify.strategies.CustomPlayStrategy;
import Spotify.strategies.RandomPlayStrategy;
import Spotify.strategies.SequentialPlayStrategy;
import Spotify.strategies.PlayStrategy;
import Spotify.enums.PlayStrategyType;

public class StrategyManager {
    private static StrategyManager instance = null;
    private CustomPlayStrategy customPlayStrategy;
    private SequentialPlayStrategy sequentialPlayStrategy;
    private RandomPlayStrategy randomPlayStrategy;

    private StrategyManager() {
        customPlayStrategy = new CustomPlayStrategy();
        sequentialPlayStrategy = new SequentialPlayStrategy();
        randomPlayStrategy = new RandomPlayStrategy();
    }

    public static StrategyManager getInstance() {
        if (instance == null) {
            instance = new StrategyManager();
        }
        return instance;
    }

    public PlayStrategy getStrategy(PlayStrategyType strategyType) {
        if(strategyType==PlayStrategyType.SEQUENTIAL){
            return sequentialPlayStrategy;
        } else if(strategyType==PlayStrategyType.RANDOM){
            return randomPlayStrategy;
        } else {
            return customPlayStrategy;
        }
    }
}