import java.util.Iterator;
import java.util.NoSuchElementException;

public class SameIterator<E>  implements Iterator<E> {

   private Iterator<E> left;
   private Iterator<E> right;
   private E lItem;
   private E rItem;
   private E lNext;
   private E rNext;
   private boolean fail;

   public SameIterator(Iterator<E> left, Iterator<E> right) {
      this.left = left;
      this.right = right;
      this.lItem = null;
      this.rItem = null;
      this.fail = false;

      // find first element, if it exists
      while(this.left.hasNext() && this.right.hasNext()) {
         this.lNext = this.left.next();
         this.rNext = this.right.next();
         if(this.lNext.equals(this.rNext)) {
            break;
         }
      }
      // fail if no matches
      if (this.lNext != null && !this.lNext.equals(this.rNext)) {
         fail = true;
      }
   }

   @Override
   public boolean hasNext() {
      return((left.hasNext() && right.hasNext()) || !fail);
   }

   @Override
   public E next() {
      if (!hasNext()) throw new NoSuchElementException();

      // set elements to the current that have been predetermined
      lItem = lNext;
      rItem = rNext;
      fail = true;

      // find the next element if it exists
      while(left.hasNext() && right.hasNext()) {
         lNext = left.next();
         rNext = right.next();
         if(lNext.equals(rNext)) {
            fail = false;
            break;
         }
      }

      return lItem;
   }
}
