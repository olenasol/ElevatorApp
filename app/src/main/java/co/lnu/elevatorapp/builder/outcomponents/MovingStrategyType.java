package co.lnu.elevatorapp.builder.outcomponents;

import java.util.Random;

public enum MovingStrategyType {
    WITH_STOP_STRATEGY, WITHOUT_STOP_STRATEGY;

    private static final MovingStrategyType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static MovingStrategyType getRandomMovingStrategyType() {
//        return VALUES[RANDOM.nextInt(SIZE)];
        return WITHOUT_STOP_STRATEGY;
    }
}