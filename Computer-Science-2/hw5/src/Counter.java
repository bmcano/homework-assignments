import java.util.Iterator;

// The Iterator that generates the infinite sequence of Integers
// counting by n
class Counter implements Iterator<Integer> {
    private int c;
    private final int n;
    public Counter(int n) {
        this.c = 0;
        this.n = n;
    }
 
    @Override
    public Integer next() {
        Integer result = c;
        c+=n;
        return result;
    }
    @Override
    public boolean hasNext() {
        return true;
    }
}
