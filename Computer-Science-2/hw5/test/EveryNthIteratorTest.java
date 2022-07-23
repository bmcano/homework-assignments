import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EveryNthIteratorTest {
    @Test
    public void every1TestInteger() {
        Integer[] li = {12, 92, 58, 99, 61, 53, 99, 55, 93, 88};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new EveryNthIterator<>(input, 1);
        Integer[] expected = {12, 92, 58, 99, 61, 53, 99, 55, 93, 88};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void every1TestString() {
        String[] li = {"ab", "cd", "ef", "gh", "ij", "kl"};
        Iterator<String> input = Arrays.asList(li).iterator();
        Iterator<String> iter = new EveryNthIterator<>(input, 1);
        String[] expected = {"ab", "cd", "ef", "gh", "ij", "kl"};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void every2TestOdd() {
        String[] li = {"76", "95", "16", "44", "3", "31", "5", "39", "19"};
        Iterator<String> input = Arrays.asList(li).iterator();
        Iterator<String> iter = new EveryNthIterator<>(input, 2);
        String[] expected = {"76", "16", "3", "5", "19"};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void every2TestEven() {
        Integer[] li = {76, 95, 16, 44, 3, 31, 5, 39, 19, 75};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new EveryNthIterator<>(input, 2);
        Integer[] expected = {76, 16, 3, 5, 19};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void every5Test() {
        Integer[] li = {91, 41, 7, 44, 78, 27, 6, 92, 97};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new EveryNthIterator<>(input, 5);
        Integer[] expected = {91, 27};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void every5TestLong() {
        Integer[] li = new Integer[101];
        for (int i=0; i<li.length; i++) {
            li[i] = 5000 + i;
        }
        Integer[] expected = new Integer[21];
        for (int i=0; i<21; i++) {
            expected[i] = 5000 + 5*i;
        }
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new EveryNthIterator<>(input, 5);
        TestUtilities.check(expected, iter);
    }

    @Test
    public void every5TestLonger() {
        Integer[] li = new Integer[104];
        for (int i=0; i<li.length; i++) {
            li[i] = 5000 + i;
        }
        Integer[] expected = new Integer[21];
        for (int i=0; i<21; i++) {
            expected[i] = 5000 + 5*i;
        }
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new EveryNthIterator<>(input, 5);
        TestUtilities.check(expected, iter);
    }

    @Test
    public void every6TestLong() {
        Integer[] li = new Integer[100];
        for (int i=0; i<li.length; i++) {
            li[i] = 5000 + i;
        }
        Integer[] expected = new Integer[17];
        for (int i=0; i<17; i++) {
            expected[i] = 5000 + 6*i;
        }
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new EveryNthIterator<>(input, 6);
        TestUtilities.check(expected, iter);
    }

    @Test(expected = NoSuchElementException.class)
    public void exception1Test() {
        String[] li = {"ab", "cd", "ef", "gh"};
        Iterator<String> input = Arrays.asList(li).iterator();
        Iterator<String> iter = new EveryNthIterator<>(input, 1);
        String[] expected = {"ab", "cd", "ef", "gh"};
        TestUtilities.checkBeyond(expected, iter);
    }

    @Test(expected = NoSuchElementException.class)
    public void exception2Test() {
        Integer[] li = {1,2,3,4,5,6,7};
        Iterator<Integer> input = Arrays.asList(li).iterator();
        Iterator<Integer> iter = new EveryNthIterator<>(input, 2);
        Integer[] expected = {1,3,5,7};
        TestUtilities.checkBeyond(expected, iter);
    }

    @Test(timeout = 1000)
    public void infiniteIteratorTest() {
        Iterator<Integer> l1 = new Counter(1);
        Iterator<Integer> iter = new EveryNthIterator<>(l1, 5);
        Integer[] expected = {0,5,10,15,20};
        TestUtilities.checkInfinite(expected, iter);
    }
}