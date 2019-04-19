package co.lnu.elevatorapp.builder;

import co.lnu.elevatorapp.builder.component.builder.ElevatorBuilder;
import co.lnu.elevatorapp.builder.component.builder.FloorBuilder;
import co.lnu.elevatorapp.builder.component.builder.impl.ElevatorBuilderImpl;
import co.lnu.elevatorapp.builder.component.builder.impl.FloorBuilderImpl;
import co.lnu.elevatorapp.builder.impl.BuildingBuilderImpl;
import co.lnu.elevatorapp.builder.outcomponents.IncomingStrategyType;
import co.lnu.elevatorapp.builder.outcomponents.MovingStrategyType;
import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.ElevatorState;
import co.lnu.elevatorapp.elevator.moving.strategy.MovingStrategy;
import co.lnu.elevatorapp.elevator.moving.strategy.impl.WithStopElevatorStrategy;
import co.lnu.elevatorapp.elevator.moving.strategy.impl.WithoutStopElevatorStrategy;
import co.lnu.elevatorapp.floor.incoming.strategy.IncomingStrategy;
import co.lnu.elevatorapp.floor.incoming.strategy.impl.OrderingIncomingStrategy;
import co.lnu.elevatorapp.floor.incoming.strategy.impl.RandomIncomingStrategy;

import java.util.EnumMap;

public class BuildingDirector {
    public static final int MAX_WEIGHT = 500;

    private BuildingBuilder buildingBuilder;
    private ElevatorBuilder elevatorBuilder;
    private FloorBuilder floorBuilder;
    private Dispatcher dispatcher;


    private static EnumMap<IncomingStrategyType, IncomingStrategy> incomingStrategyEnumMap = new EnumMap<>(IncomingStrategyType.class);
    private static EnumMap<MovingStrategyType, MovingStrategy> movingStrategyEnumMap = new EnumMap<>(MovingStrategyType.class);

    static {
        incomingStrategyEnumMap.put(IncomingStrategyType.ORDERING_STRATEGY, new OrderingIncomingStrategy());
        incomingStrategyEnumMap.put(IncomingStrategyType.RANDOM_STRATEGY, new RandomIncomingStrategy());

        movingStrategyEnumMap.put(MovingStrategyType.WITH_STOP_STRATEGY, new WithStopElevatorStrategy());
        movingStrategyEnumMap.put(MovingStrategyType.WITHOUT_STOP_STRATEGY, new WithoutStopElevatorStrategy());
    }

    public BuildingDirector(Dispatcher dispatcher) {
        buildingBuilder = new BuildingBuilderImpl();
        elevatorBuilder = new ElevatorBuilderImpl();
        floorBuilder = new FloorBuilderImpl();
        this.dispatcher = dispatcher;
    }

    public Building construct(int numberOfFloors, int numberOfElevators) {
        buildingBuilder.createNewBuilding();
        addFloors(numberOfFloors);
        addElevators(numberOfElevators);
        return buildingBuilder.build();
    }

    private void addFloors(int numberOfFloors) {
        for (int floorIndex = 1; floorIndex <= numberOfFloors; floorIndex++) {
            buildingBuilder.setFloor(
                    floorBuilder.createNewFloor()
                            .setFloorId(floorIndex)
                            .setIncomingStrategy(incomingStrategyEnumMap.get(IncomingStrategyType.getRandomIncomingStrategy()))
                            .build());
        }
    }

    private void addElevators(int numberOfElevators) {
        for (int elevatorIndex = 1; elevatorIndex <= numberOfElevators; elevatorIndex++) {
            buildingBuilder.setElevator(
                    elevatorBuilder.createNewElevator()
                            .setElevatorId(elevatorIndex)
                            .setDispatcher(dispatcher)
                            .setMaxWeight(MAX_WEIGHT)
                            .setElevatorState(ElevatorState.STOP)
                            .setMovingStrategy(movingStrategyEnumMap.get(MovingStrategyType.getRandomMovingStrategyType()))
                            .build());

        }
    }
}
