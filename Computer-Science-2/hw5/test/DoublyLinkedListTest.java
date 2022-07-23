import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedListTest {
    @Test
    public void iterator1Test() {
        DoublyLinkedList<Integer> li = new DoublyLinkedList<>();
        li.addLast(8);
        Integer[] expected = {8};
        TestUtilities.check(expected, li.iterator());
    }

    @Test
    public void iteratorTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        li.addLast("ab");
        li.addLast("cd");
        li.addLast("ef");
        li.addLast("gh");
        String[] expected = {"ab", "cd", "ef", "gh"};
        TestUtilities.check(expected, li.iterator());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceededTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        li.addLast("ab");
        li.addLast("cd");
        li.addLast("ef");
        li.addLast("gh");
        String[] expected = {"ab", "cd", "ef", "gh"};
        TestUtilities.checkBeyond(expected, li.iterator());
    }

    @Test
    public void iteratorTwiceTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        li.addLast("ab");
        li.addLast("cd");
        li.addLast("ef");
        li.addLast("gh");
        String[] expected = {"ab", "cd", "ef", "gh"};
        TestUtilities.check(expected, li.iterator());
        li.addLast("jk");
        li.addLast("lm");
        li.addLast("no");
        li.removeFirst();
        li.removeFirst();
        String[] expected2 = {"ef", "gh", "jk", "lm", "no"};
        TestUtilities.check(expected2, li.iterator());
    }

    @Test
    public void iteratorEmptyTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        String[] expected = {};
        TestUtilities.check(expected, li.iterator());
    }

    @Test
    public void backwardsIterator1Test() {
        DoublyLinkedList<Integer> li = new DoublyLinkedList<>();
        li.addLast(10);
        Integer[] expected = {10};
        TestUtilities.check(expected, li.iterator());
    }

    @Test
    public void backwardsIteratorTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        li.addLast("ab");
        li.addLast("cd");
        li.addLast("ef");
        li.addLast("gh");
        li.addLast("ij");
        li.addLast("kl");
        String[] expected = {"kl", "ij", "gh", "ef", "cd", "ab"};
        TestUtilities.check(expected, li.backwardsIterator());
    }

    @Test(expected = NoSuchElementException.class)
    public void backwardsIteratorExceededTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        li.addLast("ab");
        li.addLast("cd");
        li.addLast("ef");
        li.addLast("gh");
        li.addLast("ij");
        li.addLast("kl");
        String[] expected = {"kl", "ij", "gh", "ef", "cd", "ab"};
        TestUtilities.checkBeyond(expected, li.backwardsIterator());
    }

    @Test
    public void backwardsIteratorTwiceTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        li.addLast("ab");
        li.addLast("cd");
        li.addLast("ef");
        li.addLast("gh");
        li.addLast("ij");
        li.addLast("kl");
        li.addLast("mn");
        String[] expected = {"mn", "kl", "ij", "gh", "ef", "cd", "ab"};
        TestUtilities.check(expected, li.backwardsIterator());
        li.removeFirst();
        li.removeLast();
        li.addFirst("wx");
        li.addFirst("yz");
        String[] expected2 = {"kl", "ij", "gh", "ef", "cd", "wx", "yz"};
        TestUtilities.check(expected2, li.backwardsIterator());
    }

    @Test
    public void backwardsIteratorEmptyTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        String[] expected = {};
        TestUtilities.check(expected, li.backwardsIterator());
    }

    @Test
    public void pathTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        li.addLast("ab");
        li.addLast("cd");
        li.addLast("ef");
        li.addLast("gh");
        li.addLast("ij");
        li.addLast("kl");
        li.addLast("mn");
        Integer[] movesA = {2, 0, 3, -2, -1, 4, -5};
        Iterator<Integer> moves = Arrays.asList(movesA).iterator();
        String[] expected = {"ef", "ef", "kl", "gh", "ef", "mn", "cd"};
        TestUtilities.check(expected, li.path(moves));
    }

    @Test(expected = NoSuchElementException.class)
    public void pathExceededTest() {
        DoublyLinkedList<String> li = new DoublyLinkedList<>();
        li.addLast("ab");
        li.addLast("cd");
        li.addLast("ef");
        li.addLast("gh");
        li.addLast("ij");
        li.addLast("kl");
        li.addLast("mn");
        Integer[] movesA = {2, 0, 3, -2, -1, 4, -5};
        Iterator<Integer> moves = Arrays.asList(movesA).iterator();
        String[] expected = {"ef", "ef", "kl", "gh", "ef", "mn", "cd"};
        TestUtilities.checkBeyond(expected, li.path(moves));
    }

    @Test
    public void pathTest2() {
        DoublyLinkedList<Integer> li = new DoublyLinkedList<>();
        li.addLast(4);
        li.addLast(10);
        li.addLast(99);
        li.addLast(0);
        li.addLast(-3);
        li.addLast(-8);
        li.addLast(77);
        li.addLast(100);
        Integer[] movesA = {0, 6, 1, -1, -3, -2, 1, 1, 1};
        Iterator<Integer> moves = Arrays.asList(movesA).iterator();
        Integer[] expected = {4, 77, 100, 77, 0, 10, 99, 0, -3};
        TestUtilities.check(expected, li.path(moves));
    }

    @Test(timeout = 1000)
    public void pathInfiniteTest() {
        DoublyLinkedList<Integer> li = new DoublyLinkedList<>();
        li.addLast(4);
        li.addLast(10);
        li.addLast(99);
        li.addLast(0);
        li.addLast(-3);
        li.addLast(-8);
        li.addLast(77);
        li.addLast(100);
        Iterator<Integer> moves = new Counter(1);
        Integer[] expected = {4, 10, 0, 77};
        TestUtilities.checkInfinite(expected, li.path(moves));
    }

    @Test
    public void pathTwiceTest() {
        DoublyLinkedList<Integer> li = new DoublyLinkedList<>();
        li.addLast(4);
        li.addLast(10);
        li.addLast(99);
        li.addLast(0);
        Integer[] movesA = {3, -1, -1, -1, 2};
        Iterator<Integer> moves = Arrays.asList(movesA).iterator();
        Integer[] expected = {0, 99, 10, 4, 99};
        TestUtilities.check(expected, li.path(moves));
        li.removeLast();
        li.removeLast();
        li.addLast(3);
        li.addFirst(40);
        li.addFirst(17);
        Integer[] movesA2 = {3, 1, -1, 1, -4};
        Iterator<Integer> moves2 = Arrays.asList(movesA2).iterator();
        Integer[] expected2 = {10, 3, 10, 3, 17};
        TestUtilities.check(expected2, li.path(moves2));
    }
}