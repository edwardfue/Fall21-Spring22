import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

//import Sep29.ArrayBaseQueue;
//import Sep29.QueueInterface;

public class ArrayBasedQueue<E> implements QueueInterface<E> {

	private final int DEFAULT_ARRAY_SIZE = 10_000;
	
	private Object[] array;
	private int front;
	private int back;
	private int numELements;
	
	public ArrayBasedQueue() {
		this.array = new Object[this.DEFAULT_ARRAY_SIZE];
		this.numELements = 0;
		this.front = -1;
		this.back = -1;
	}
	
	public ArrayBasedQueue(int arraySize) {
		this();
		if (arraySize > 0) {
			this.array = new Object[arraySize];
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		Vector<E> vector = new Vector<E>(this.numELements);
		
		for ( int i = this.front, count = 0 ; count < this.numELements ; i = this.increment(i), count++ ) {
			vector.add((E) this.array[i]);
		}
		
		ElementIterator<E> iterator = new ElementIterator<E>(vector);
		
		return iterator;
	}

	@Override
	public boolean isEmpty() {
		return (this.numELements == 0);
	}

	@Override
	public int size() {
		return this.numELements;
	}

	//@SuppressWarnings("unchecked")
	//@Override
	public QueueInterface<E> copy() {
		// TODO Auto-generated method stub
		//QueueInterface<E> cop = new ArrayBasedQueue();
		return this;
	}

	@Override
	public void enqueue(E element) throws IllegalStateException, NullPointerException {
		if (element == null) {
			throw new NullPointerException("Unable to enqueue element = null");
		} else if (this.numELements == this.array.length) {
			throw new IllegalStateException("Unable to enqueue element because ArrayBasedQueue is already full");
		}
		
		if (this.isEmpty()) {
			this.array[0] = element;
			this.front = 0;
			this.back = 0;
		} else {
			this.back = this.increment(this.back);
			this.array[this.back] = element;
		}
		
		this.numELements++;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E peek() {
		if (this.isEmpty()) {
			return null;
		} else {
			E peekedElement = (E) this.array[this.front];
			return peekedElement;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public E dequeue() {
		if (this.isEmpty()) {
			return null;
		} else {
			E dequeuedElement = (E) this.array[this.front];
			this.array[this.front] = null;
			this.front = this.increment(this.front);
			this.numELements--;
			return dequeuedElement;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public E dequeue(int index) throws NoSuchElementException {
		if ((index < 0) || (index >= this.numELements)) {
			throw new NoSuchElementException("ArrayBasedQueue has numElements = " + this.numELements + 
					". index = " + index + " is not valid");
		}
		
		if (index == 0) {
			return dequeue();
		} else {
			int arrayIndex = findIndex(index);
			E dequeuedElement = (E) this.array[arrayIndex];

			for ( int i = arrayIndex ; i != this.back ; i = this.increment(i) ) {
				int j = this.increment(i);
				this.array[i] = this.array[j];
			}
			
			this.back = this.decrement(this.back);
			
			this.numELements--;
			return dequeuedElement;
		}
	}

	@Override
	public void removeAll() {
		this.array = new Object[this.array.length];
		this.numELements = 0;
		this.front = -1;
		this.back = -1;
	}
	
	@Override
	public String toString() {
		String s = "numElements = " + this.numELements + "\n";
		s += "elements = [";
		int num = 1;
		int index = this.front;
		while (num <= this.numELements) {
			s += this.array[index];
			if (num != this.numELements) {
				s += ", ";
			}
			index = increment(index);
			num++;
		}
		s += "]\n";
		s += "front = " + this.front + "\n";
		s += "back = " + this.back + "\n";
		s += "Array Size = " + this.array.length + "\n\n\n"; 
		return s;
	}

	private int increment(int index) {
		return ((index + 1) % this.array.length);
	}
	
	private int findIndex(int index) {
		return ((this.front + index) % this.array.length);
	}
	
	private int decrement(int index) {
		if (index == 0) {
			return this.array.length - 1;
		} else {
			return index - 1;
		}
	}
	
	
	
	public static void main(String[] args) {
		ArrayBasedQueue<String> abq = new ArrayBasedQueue<String>(13);
		
		for ( int i = 1 ; i <= 10 ; i++ ) {
			abq.enqueue(new String("String " + i));
		}
		
		System.out.println(abq);
		
		String element;
		
		for ( int i = 1 ; i <= 3 ; i++ ) {
			element = abq.dequeue();
			System.out.println("Dequeued element = " + element);
			System.out.println(abq);
		}
		
		element = abq.peek();
		System.out.println("Peeked element = " + element + "\n\n\n");
		
		
		element = abq.dequeue(4);
		System.out.println("Dequeued element = " + element);
		System.out.println(abq);
		
		for ( int i = 11 ; i <= 17 ; i++ ) {
			abq.enqueue(new String("String " + i));
		}
		
		System.out.println(abq);

		try {
			abq.enqueue("String 18");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n\n\nIterator Start");
		Iterator<String> iterator = abq.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("Iterator End");
		
	}

}
