public class LinkedListDeque<Item> {
	private Node sentinel = new Node (null, null, null);
	private int size;


	private class Node {
		public Item item;
		public Node next;
		public Node prev;


		private Node (Item i, Node p, Node n) {
			item = i;
			prev = p;
			next = n;
		}
	}

	public LinkedListDeque() {
		size = 0;
	}

	public void addFirst(Item x) {
		if (sentinel.next == null) {
			sentinel.next = new Node (x, sentinel, sentinel);
			sentinel.prev = sentinel.next;
			size ++;
		}
		else {
			sentinel.next = new Node (x, sentinel, sentinel.next);
			sentinel.next.next.prev = sentinel.next;
			size ++;
		}
	} 

	public void addLast(Item x) {
		if (sentinel.prev == null) {
			sentinel.prev = new Node (x, sentinel, sentinel);
			sentinel.next = sentinel.prev;
			size ++;
		}
		else {
			sentinel.prev = new Node (x, sentinel.prev, sentinel);
			sentinel.prev.prev.next = sentinel.prev;
			size ++;
		}
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		else {
			return false;
		}
	}



	public int size() {
		return size;
	}

	public void printDeque() {
		Node start = sentinel.next;
		if (size > 0) {
			while (start != sentinel) {
				System.out.print(start.item + " ");
				start = start.next;
			}
		}
	}

	public Item removeFirst() {
		if (size == 0) {
			return null;
		}
		Node front = sentinel.next;
		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		size --;
		return front.item;

	}

	public Item removeLast() {
		if (size == 0) {
			return null;
		}
		Node back = sentinel.prev;
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		size --;
		return back.item;

	}

	public Item get (int index) {
		if (size <= index) {
			return null;
		}
		int count = 0;
		Node target = sentinel.next;
		while (count != index) {
			target = target.next;
			count ++;

		}
		return target.item;

	}

	private Item helper (int i, Node n) {
		if (i == 0) {
			return n.item;
		}
		else {
			return helper (i - 1, n.next);
		}
	}

	public Item getRecursive (int index) {
		if (size <= index) {
			return null;
		}
		Node copy = sentinel.next;
		return helper(index, copy);
	}
}