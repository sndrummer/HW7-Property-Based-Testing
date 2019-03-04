package misc;

public class Queue {
	Object[] arr;
	int size;
	int first;
	int last;

	public Queue(int max) {
		arr = new Object[max];
		size = 0;
		first = 0;
		last = 0;
	}

	public int size() {
		return size;
	}

	public int max() {
		return arr.length;
	}

	public void enqueue(Object x) {
		arr[last] = x;
		last++;
		if (last == arr.length)
			last = 0;
		size++;
	}

	public Object dequeue() {
		if (size == 0)
			return null;
		Object x = arr[first];
		first++;
		if (first == arr.length)
			first = 0;
		size--;
		return x;
	}
}
