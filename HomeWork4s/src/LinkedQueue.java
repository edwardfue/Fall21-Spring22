import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

//import javax.swing.text.ElementIterator;

public class LinkedQueue<E> implements QueueInterface<E> {
	private Node<E> front;
	private Node<E> back;
	private int numElements;
	
	public LinkedQueue() {
		this.front = null;
		this.back = null;
		this.numElements = 0;
	}
	

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		Vector<E> vector = new Vector<E>(this.numElements);
		
		Node<E> curNode = this.front;
		while(curNode != null) {
			vector.add(curNode.getElement());
			curNode = curNode.getNext();
		}
		return new ElementIterator<E>(vector);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.numElements == 0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.numElements;
	}

	@Override
	public QueueInterface<E> copy() {
		// TODO Auto-generated method stub
		LinkedQueue<E> linkedQueue =  new LinkedQueue<>();
		Node<E> curNode = front;
		while(curNode != null) {
			linkedQueue.enqueue(curNode.getElement());
			curNode = curNode.getNext();
		}
		return linkedQueue;
	}

	@Override
	public void enqueue(E element) throws IllegalStateException, NullPointerException {
		// TODO Auto-generated method stub
		if (element == null) {
			throw new NullPointerException("Unable to equeue a null element");
		}
		
		Node<E> newNode = new Node<E>(element);
		if (this.isEmpty()) {
			this.front = newNode;
			this.back = newNode;
		} else {
			this.back.setNext(newNode);
			newNode.setPrevious(this.back);
			this.back = newNode;
		}
		
		this.numElements++;
		
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			return null;
		}
		else {
			return this.front.getElement();
		}
		//return null;
	}

	@SuppressWarnings("unused")
	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		
		int goForIt = 0;
		
		if (this.isEmpty()) {
			return null;
		} else {
			E element = this.front.getElement();
			this.front = this.front.getNext();
			
			if (this.front == null) {
				this.back = null;
			} else {
				this.front.setPrevious(null);
			}
			
			this.numElements--;
			return element;
		}
	}

	@Override
	public E dequeue(int index) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if ((index < 0) || (index >= this.numElements)) {
			throw new NoSuchElementException("Index = " + index + " is not valid in a queue with only " +
					this.numElements + " elements");
		}
		
		if (index == 0) {
			return this.dequeue();
		} else if (index == (this.numElements - 1)) {
			E element = this.back.getElement();
			this.back.getPrevious().setNext(null);
			this.back = this.back.getPrevious();
			this.numElements--;
			return element;
		} else {
			Node<E> curNode = this.front.getNext();
			int curIndex = 1;
			
			while (curIndex != index) {
				curNode = curNode.getNext();
				curIndex++;
			}
			
			E element = curNode.getElement();
			curNode.getPrevious().setNext(curNode.getNext());
			curNode.getNext().setPrevious(curNode.getPrevious());
			this.numElements--;
			return element;
		}
		//return null;
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		this.front = null;
		this.back  = null;
		this.numElements = 0;
		
	}

}
