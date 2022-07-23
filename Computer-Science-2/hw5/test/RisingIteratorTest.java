import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RisingIteratorTest {
    @Test
    public void allIncreasingTest() {
        Integer[] li = {-7745, -7547, -7476, -7328, -7293, -7239, -7221, -7128, -7053, -7007};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new RisingIterator(input);
        Integer[] expected = {-7745, -7547, -7476, -7328, -7293, -7239, -7221, -7128, -7053, -7007};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void allDecreasingTest() {
        Integer[] li = {94, 78, 73, 54, 49, 41, 33, 21, 18, 1};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new RisingIterator(input);
        Integer[] expected = {94};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void endNotIncreasingTest() {
        Integer[] li = {1, 18, 21, 49, 59, 74, 84, 97, 33, 41, 45};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new RisingIterator(input);
        Integer[] expected = {1, 18, 21, 49, 59, 74, 84, 97};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void midNotIncreasingTest() {
        Integer[] li = {1, 18, 21, 49, 59, 74, 84, 97, 33, 41, 45, 100, 102, 200};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new RisingIterator(input);
        Integer[] expected = {1, 18, 21, 49, 59, 74, 84, 97, 100, 102, 200};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void alternatingTest () {
        Integer[] li = {18, 1, 21, 59, 40, 74, 97, 84, 102, 99, 101};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new RisingIterator(input);
        Integer[] expected = {18, 21, 59, 74, 97, 102};
        TestUtilities.check(expected, iter);
    }

    @Test(expected = NoSuchElementException.class)
    public void exceptionTest() {
        Integer[] li = {2, 20, 37, 44, 47};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new RisingIterator(input);
        Integer[] expected = {2, 20, 37, 44, 47};
        TestUtilities.checkBeyond(expected, iter);
    }

    @Test(timeout = 1000)
    public void infiniteIteratorTest() {
        Iterator<Integer> l1 = new Counter(3);
        Iterator<Integer> iter = new RisingIterator(l1);
        Integer[] expected = {0, 3, 6, 9, 12, 15, 18};
        TestUtilities.checkInfinite(expected, iter);
    }
}