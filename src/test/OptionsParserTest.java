package test;

import agh.cs.po.lab2.MoveDirection;
import agh.cs.po.lab2.OptionsParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void fromString()
    {
        String[] args = {"f", "forward", "b", "backward", "123", "l", "left", "322", "r", "right"};
        MoveDirection[] result = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.BACKWARD, null, MoveDirection.LEFT, MoveDirection.LEFT, null, MoveDirection.RIGHT,
                MoveDirection.RIGHT};
//        Assertions.assertArrayEquals(OptionsParser.fromStrings(args), result);
        assertThrows(IllegalArgumentException.class, () -> {
            OptionsParser.fromStrings(args);
        });
    }

}