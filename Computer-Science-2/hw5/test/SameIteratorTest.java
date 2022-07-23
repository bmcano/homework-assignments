import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SameIteratorTest {

    @Test
    public void smallSameTest() {
        String[] l1 = {"ab", "cd", "ef", "gh"};
        String[] l2 = {"ab", "cd", "ef", "gh"};
        Iterator<String> i1 = Arrays.asList(l1).iterator();
        Iterator<String> i2 = Arrays.asList(l2).iterator();
        Iterator<String> iter = new SameIterator<>(i1, i2);
        String[] expected = {"ab", "cd", "ef", "gh"};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void smallOneDiffTest() {
        Integer[] l1 = {33,21,3,86,84,16,98,11,75,19};
        Integer[] l2 = {33,21,3,86,1,16,98,11,75,19};
        Iterator<Integer> i1 = Arrays.asList(l1).iterator();
        Iterator<Integer> i2 = Arrays.asList(l2).iterator();
        Iterator<Integer> iter = new SameIterator<>(i1, i2);
        Integer[] expected = {33,21,3,86,16,98,11,75,19};
        TestUtilities.check(expected, iter);
    }

    private static class Cat {
        public final String name;
        public final int birthYear;

        public Cat(String name, int birthYear) {
            this.name = name;
            this.birthYear = birthYear;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cat cat = (Cat) o;
            return birthYear == cat.birthYear && Objects.equals(name, cat.name);
        }
    }

    @Test
    public void smallOneDiffTest2() {
        Cat[] l1 = {new Cat("a", 1999), new Cat("b", 2001)};
        Cat[] l2 = {new Cat("a", 2001), new Cat("b", 2001)};
        Iterator<Cat> i1 = Arrays.asList(l1).iterator();
        Iterator<Cat> i2 = Arrays.asList(l2).iterator();
        Iterator<Cat> iter = new SameIterator<>(i1, i2);
        Cat[] expected = {new Cat("b", 2001)};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void smallOneDiffEndTest() {
        Integer[] l1 = {33,21,3,86,84,16,98,11,75,19};
        Integer[] l2 = {33,21,3,86,84,16,98,11,75,33};
        Iterator<Integer> i1 = Arrays.asList(l1).iterator();
        Iterator<Integer> i2 = Arrays.asList(l2).iterator();
        Iterator<Integer> iter = new SameIterator<>(i1, i2);
        Integer[] expected = {33,21,3,86,84,16,98,11,75};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void smallOneDiffStartTest() {
        Integer[] l1 = {33,21,3,86,84,16,98,11,75,19};
        Integer[] l2 = {88,21,3,86,84,16,98,11,75,19};
        Iterator<Integer> i1 = Arrays.asList(l1).iterator();
        Iterator<Integer> i2 = Arrays.asList(l2).iterator();
        Iterator<Integer> iter = new SameIterator<>(i1, i2);
        Integer[] expected = {21,3,86,84,16,98,11,75,19};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void smallShorterTest() {
        Integer[] l1 = {33,21,3,86,84,16,98,11,75,19};
        Integer[] l2 = {33,21,3,86,84,16,98};
        Iterator<Integer> i1 = Arrays.asList(l1).iterator();
        Iterator<Integer> i2 = Arrays.asList(l2).iterator();
        Iterator<Integer> iter = new SameIterator<>(i1, i2);
        Integer[] expected = {33,21,3,86,84,16,98};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void smallLongerTest() {
        Integer[] l1 = {33,21,3,86,84};
        Integer[] l2 = {33,21,3,86,84,16,98,11,75,19};
        Iterator<Integer> i1 = Arrays.asList(l1).iterator();
        Iterator<Integer> i2 = Arrays.asList(l2).iterator();
        Iterator<Integer> iter = new SameIterator<>(i1, i2);
        Integer[] expected = {33,21,3,86,84};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void smallOffsetTest() {
        Integer[] l1 = {33,21,3,86,84,16,98,11,75,19};
        Integer[] l2 = {33,21,3,84,16,98,11,75,19};
        Iterator<Integer> i1 = Arrays.asList(l1).iterator();
        Iterator<Integer> i2 = Arrays.asList(l2).iterator();
        Iterator<Integer> iter = new SameIterator<>(i1, i2);
        Integer[] expected = {33,21,3};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void smallOffset2Test() {
        Integer[] l1 = {33,21,3,86,84,11,75,19};
        Integer[] l2 = {33,21,3,86,84,16,98,11,75,19};
        Iterator<Integer> i1 = Arrays.asList(l1).iterator();
        Iterator<Integer> i2 = Arrays.asList(l2).iterator();
        Iterator<Integer> iter = new SameIterator<>(i1, i2);
        Integer[] expected = {33,21,3,86,84};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void smallOffset3Test() {
        Integer[] l1 = {33,21, 3,86,84,11,75,19};
        Integer[] l2 = {21, 3,86,84,16,98,11,75,19};
        Iterator<Integer> i1 = Arrays.asList(l1).iterator();
        Iterator<Integer> i2 = Arrays.asList(l2).iterator();
        Iterator<Integer> iter = new SameIterator<>(i1, i2);
        Integer[] expected = {};
        TestUtilities.check(expected, iter);
    }

    @Test
    public void smallManyDiffTest() {
        Integer[] l1 = {33,21, 3,86,84, 0,77,11,75};
        Integer[] l2 = {33,21,40,86, 0,84,98,11,75,19,75,8};
        Iterator<Integer> i1 = Arrays.asList(l1).iterator();
        Iterator<Integer> i2 = Arrays.asList(l2).iterator();
        Iterator<Integer> iter = new SameIterator<>(i1, i2);
        Integer[] expected = {33, 21, 86, 11, 75};
        TestUtilities.check(expected, iter);
    }

    @Test(timeout = 1000)
    public void infiniteIteratorTest() {
        Iterator<Integer> l1 = new Counter(2);
        Iterator<Integer> l2 = new Counter(2);
        Iterator<Integer> iter = new SameIterator<>(l1, l2);
        // only asks for the first several results
        Integer[] expected = {0,2,4,6,8,10,12,14,16,18,20};
        TestUtilities.checkInfinite(expected, iter);
    }
}