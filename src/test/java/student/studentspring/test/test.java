package student.studentspring.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class test {
    private static Map<Long, String> testList = new HashMap<Long,String>();
    private static Long sequence = 0L;

    @Test
    public void addItem(){
        testList.put(++sequence, String.valueOf('a'));
        testList.put(++sequence, String.valueOf('b'));
        testList.put(++sequence, String.valueOf('c'));
        testList.put(++sequence, String.valueOf('d'));
        testList.put(++sequence, String.valueOf('e'));
        testList.put(++sequence, String.valueOf('f'));
        testList.put(++sequence, String.valueOf('g'));
        testList.put(++sequence, String.valueOf('h'));
        testList.put(++sequence, String.valueOf('i'));
        testList.put(++sequence, String.valueOf('j'));
    }

}
