package agh.ics.oop.model.util;

import agh.ics.oop.model.enums.MoveDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> parseDirections(String[] args) throws IllegalArgumentException {
        return Stream.of(args)
                .map(arg -> switch (arg) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    case "l" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException(arg + "is not valid move specification");
                })
                .collect(Collectors.toList());
    }
}
