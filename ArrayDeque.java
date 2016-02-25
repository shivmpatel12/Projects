public class ArrayDeque<Item> {
	private Item[] items;
	private int size;
	private int nextFirst;
	private int nextLast;
	private static int RFACTOR = 2;

	public ArrayDeque() {
		items = (Item []) new Object[8];
		size = 0;
		nextFirst = 0;
		nextLast = 1;
	}

	private void resize(int capacity) {
		Item[] rFactored = (Item[]) new Object[capacity];
		items = scan(items);
		System.arraycopy(items, 0, rFactored, 0, size);
		nextFirst = (rFactored.length - 1);
		nextLast = size;
		items = rFactored;
	}

	private Item[] scan(Item[] i) {
		int start = plusOne(nextFirst);
		int end = minusOne(nextLast);
		int index = 0;
		Item[] copy = (Item[]) new Object[size];
		while (start != end) {
			copy[index] = i[start];
			start = plusOne(start);
			index ++;
		}
		copy[index] = i[start];
		return copy;
	}


	public void addFirst(Item x) {
		if (size == items.length) {
			resize(size * RFACTOR);
		}
		items[nextFirst] = x;
		size ++;
		nextFirst = minusOne(nextFirst);
	}

	public void addLast(Item x) {
		if (size == items.length) {
			resize(size * RFACTOR);
		}
		items[nextLast] = x;
		size ++;
		nextLast = plusOne(nextLast);
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else {
			return false;
		}

	}

	private int minusOne(int index) {
		if (index - 1 < 0) {
			return (items.length - 1);
		}
		else {
			return (index - 1);
		}
	}

	private int plusOne(int index) {
		if (index + 1 >= items.length) {
			return 0;
		}
		else {
			return (index + 1);
		}
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		int start = plusOne(nextFirst);
		int end = minusOne(nextLast);
		while (start != end) {
			System.out.print(items[start] + " ");
			start = plusOne(start);
		}
	}

	public Item removeFirst() {
		if (size == 0) {
			return null;
		}
		Item[] target = (Item[]) new Object[1];
		nextFirst = plusOne(nextFirst);
		target[0] = items[nextFirst];
		size --;
		items[nextFirst] = null;
		if ((items.length >= 16) && (size < (items.length / 4))) {
			resize(items.length / RFACTOR);
		}
		return target[0];
	}

	public Item removeLast() {
		if (size == 0) {
			return null;
		}
		Item[] target = (Item[]) new Object[1];
		nextLast = minusOne(nextLast);
		target[0] = items[nextLast];
		size --;
		items[nextLast] = null;
		if ((items.length >= 16) && (size < (items.length / 4))) {
			resize(items.length / RFACTOR);
		}
		return target[0];

	}

	public Item get(int index) {
		if (size <= index) {
			return null;
		}
		int start = plusOne(nextFirst);
		int end = minusOne(nextLast);
		while (index > 0) {
			start = plusOne(start);
			index --;
		}
		return items[start];
	}

}
