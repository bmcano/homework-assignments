import java.util.Iterator;
import java.util.NoSuchElementException;

public class RisingIterator implements Iterator<Integer> {

    private Iterator<Integer> input;
    private Integer highest;
    private Integer element;
    private Integer next;
    private boolean fail;

    public RisingIterator(Iterator<Integer> input) {
        this.input = input;
        this.highest = -2147483648;
        this.element = null;
        this.fail = false;

        // grab first element and set highest
        if(this.input.hasNext()) this.next = this.input.next();
        this.highest = this.next;
    }

    @Override
    public boolean hasNext() {
        // both must fail in order for hasNext() to fail
        return(input.hasNext() || !fail);
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        // set element to the already obtained value
        element = next;

        // find the next viable element in the iterator
        // if it is achieved, we break from loop and updated the highest value
        while(input.hasNext()) {
            next = input.next();
            if(next > highest) {
                highest = next;
                break;
            }
        }

        // if element the next is not bigger than current then we make hasNext() fail
        fail = next <= element;
        return element;
    }
}
