import org.junit.Assert;

import java.util.Iterator;

public class TestUtilities {
    public static <E> void check(E[] expected, Iterator<E> actual) {
       check(expected, actual, false);
    }

    public static <E> void check(E[] expected, Iterator<E> actual, boolean print) {
        check(expected, actual, print, false, false);
    }

    public static <E> void checkBeyond(E[] expected, Iterator<E> actual) {
        checkBeyond(expected, actual, false);
    }

    public static <E> void checkBeyond(E[] expected, Iterator<E> actual, boolean print) {
        check(expected, actual, print, true, false);
    }

    public static <E> void checkInfinite(E[] expected, Iterator<E> actual) {
        checkInfinite(expected, actual, false);
    }

    public static <E> void checkInfinite(E[] expected, Iterator<E> actual, boolean print) {
        check(expected, actual, print, false, true);
    }

    private static <E> void check(E[] expected, Iterator<E> actual, boolean print, boolean goBeyond, boolean infinite) {
        // check whether iterator returns all elements in expected
        for (int i = 0; i < expected.length; i++) {
            Assert.assertTrue(actual.hasNext());
            E a = actual.next();
            if (print) System.out.print(a + ",");
            Assert.assertEquals(expected[i], a);
        }
        // intentionally exceed the end of the iterator
        if (goBeyond) actual.next();

        // check that the iterator doesn't have extra elements
        if (!infinite) Assert.assertFalse(actual.hasNext());
        else Assert.assertTrue(actual.hasNext());

        if (print) System.out.println();
    }
}
