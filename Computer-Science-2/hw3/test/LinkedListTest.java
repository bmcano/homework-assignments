import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTest {

	@Test
	public void testRemove1() {
		List<Integer> ll = new LinkedList<>();
		ll.add(100);
		assertEquals(Integer.valueOf(100), ll.remove(0));
		assertEquals(0, ll.size());
	}

	@Test
	public void testRemoveBigger() {
		List<Integer> ll = new LinkedList<Integer>();
		ll.add(100);
		ll.add(200);
		ll.add(300);
		ll.add(400);
		assertEquals(Integer.valueOf(300), ll.remove(2));
		assertEquals(Integer.valueOf(200), ll.remove(1));
		assertEquals(Integer.valueOf(400), ll.remove(1));
		assertEquals(1, ll.size());
	}

	@Test
	public void testRemoveAndGet() {
		List<Integer> ll = new LinkedList<Integer>();
		ll.add(100);
		ll.add(200);
		ll.add(300);
		ll.add(400);
		assertEquals(Integer.valueOf(300), ll.remove(2));
		assertEquals(Integer.valueOf(200), ll.get(1));
		assertEquals(Integer.valueOf(400), ll.get(2));
		assertEquals(3, ll.size());
	}

	@Test
	public void testRemoveGetAppend() {
		List<Integer> ll = new LinkedList<Integer>();
		ll.add(100);
		ll.add(200);
		ll.add(300);
		ll.add(400);
		assertEquals(Integer.valueOf(300), ll.remove(2));
		assertEquals(Integer.valueOf(400), ll.get(2));
		assertEquals(3, ll.size());
		ll.add(500);
		ll.add(600);
		assertEquals(Integer.valueOf(400), ll.remove(2));
		assertEquals(Integer.valueOf(500), ll.get(2));
		assertEquals(Integer.valueOf(600), ll.remove(3));
		assertEquals(3, ll.size());
	}

	/**
	 * Test of get method, of class LinkedList<Integer>.
	 */
	@Test
	public void testGet1() {
		List<Integer> ll = new LinkedList<Integer>();
		ll.add(100);
		assertEquals(Integer.valueOf(100), ll.get(0));
		assertEquals(1, ll.size());
	}

	/**
	 * Test of get method, of class LinkedList<Integer>.
	 */
	@Test
	public void testGetBigger() {
		List<Integer> ll = new LinkedList<Integer>();
		ll.add(100);
		ll.add(200);
		ll.add(300);
		ll.add(400);
		assertEquals(Integer.valueOf(100), ll.get(0));
		assertEquals(Integer.valueOf(200), ll.get(1));
		assertEquals(Integer.valueOf(300), ll.get(2));
		assertEquals(Integer.valueOf(400), ll.get(3));
		assertEquals(4, ll.size());
	}

	/**
	 * Test of size method, of class LinkedList<Integer>.
	 */
	@Test
	public void testSizeRemove(){
		List<Integer> ll = new LinkedList<Integer>();
		assertEquals(0, ll.size());
		ll.add(100);
		assertEquals(1, ll.size());
		ll.add(200);
		ll.add(300);
		assertEquals(3, ll.size());
		ll.remove(2);
		ll.add(400);
		ll.remove(2);
		assertEquals(2, ll.size());
		ll.remove(0);
		assertEquals(1, ll.size());
		ll.remove(0);
		assertEquals(0, ll.size());
	}

	@Test
	public void testSizeEmpty() {
		List<Integer> ll = new LinkedList<Integer>();
		assertEquals(0, ll.size());
		assertEquals(0, ll.size());
	}

	@Test
	public void testAddSize() {
		List<Integer> ll = new LinkedList<Integer>();
		assertEquals(0, ll.size());
		ll.add(100);
		ll.add(200);
		assertEquals(2, ll.size());
		assertEquals(2, ll.size());
		ll.add(300);
		ll.add(400);
		ll.add(500);
		assertEquals(5, ll.size());
	}
}
