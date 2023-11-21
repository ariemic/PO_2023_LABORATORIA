package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void testParseDirections() {
        String[] args = {"f", "b", "r", "ah", "l", "nah", "meh"};
        MoveDirection[] expected = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assertArrayEquals(expected, OptionsParser.parseDirections(args).toArray(new MoveDirection[0]));

        String[] args2 = {};
        MoveDirection[] expected2 = {};
        assertArrayEquals(expected2, OptionsParser.parseDirections(args2).toArray(new MoveDirection[0]));
    }
}