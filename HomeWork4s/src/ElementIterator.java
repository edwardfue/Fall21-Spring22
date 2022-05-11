import java.util.Iterator;
import java.util.Vector;

public class ElementIterator<E> implements Iterator<E> {
	
	Vector<E> vector;
	
	public ElementIterator(Vector<E> vector) {
		this.vector = vector;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return !this.vector.isEmpty();
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		return vector.remove(0);
	}

}
