import java.util.Iterator;
import java.util.NoSuchElementException;

public class EveryNthIterator<E> implements Iterator<E> {

    private int nth;
    private Iterator<E> input;
    private int count;
    private E element;

    public EveryNthIterator(Iterator<E> in, int n) {
        this.input = in;
        this.nth = n;
        this.count = 0;
        this.element = null;
    }

    @Override
    public boolean hasNext() {
        return input.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();

        // grab the current element
        element = input.next();

        // iterate to the next element for following call
        while(input.hasNext() && this.count < this.nth-1) {
            input.next();
            count++;
        }

        // reset counter
        count = 0;

        // return correct element
        return element;
    }
}
