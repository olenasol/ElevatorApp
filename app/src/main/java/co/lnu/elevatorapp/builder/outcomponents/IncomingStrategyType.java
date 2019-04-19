package co.lnu.elevatorapp.builder.outcomponents;

import java.util.Random;

public enum IncomingStrategyType {
    RANDOM_STRATEGY, ORDERING_STRATEGY;

    private static final IncomingStrategyType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static IncomingStrategyType getRandomIncomingStrategy() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
