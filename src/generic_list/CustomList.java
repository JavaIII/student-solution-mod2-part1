package generic_list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomList<E> implements List<E> {
	
	/** Underlying array that holds the list data */
	private Object[] elementData;
	
	/** Number of items currently in the list */
	private int numElements;
	
	/** The total available size of the underlying array */
	private int totalSize;
	
	
	/**
	 * Initializes the CustomList with the given starting size.
	 * @param totalSize The starting size for the underlying data array
	 */
	public CustomList(int totalSize) {
		this.totalSize = totalSize;
		numElements = 0;
		elementData = new Object[totalSize];
	}
	
	
	/**
	 * Initializes the CustomList with a starting size of 10.
	 */
	public CustomList() {
		this(10);
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return numElements;
	}

	/**
	 * Returns <tt>true</tt> if this list contains no elements.
	 *
	 * @return <tt>true</tt> if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return numElements == 0;
	}

	/**
	 * Returns <tt>true</tt> if this list contains the specified element. More
	 * formally, returns <tt>true</tt> if and only if this list contains at least
	 * one element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < numElements; i++) {
			if (elementData[i] == o) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param e element to be appended to this list
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 */
	@Override
	public boolean add(E e) {
		reSize();
		elementData[numElements++] = e;
		return true;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it
	 * is present. If the list does not contain the element, it is unchanged. More
	 * formally, removes the element with the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt> (if
	 * such an element exists). Returns <tt>true</tt> if this list contained the
	 * specified element (or equivalently, if this list changed as a result of the
	 * call).
	 *
	 * @param o element to be removed from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 */
	@Override
	public boolean remove(Object o) {
		boolean found = false;
		for (int i = 0; i < numElements; i++) {
			if (elementData[i] == o) {
				found = true;
			}
			if (found) {
				try {
					elementData[i] = elementData[i + 1];
				} catch (IndexOutOfBoundsException ex) {
					// End of list - do nothing
				}
			}
		}
		if (found) { numElements--; }
		return found;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this
	 * call returns.
	 */
	@Override
	public void clear() {
		numElements = 0;
		totalSize = 10;
		elementData = new Object[totalSize];
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		return (E) elementData[index];
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 *
	 * @param index   index of the element to replace
	 * @param element element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public E set(int index, E element) throws IndexOutOfBoundsException {
		Object previousObj = elementData[index];
		elementData[index] = element;
		return (E) previousObj;
	}

	/**
	 * Inserts the specified element at the specified position in this list. Shifts
	 * the element currently at that position (if any) and any subsequent elements
	 * to the right (adds one to their indices).
	 *
	 * @param index   index at which the specified element is to be inserted
	 * @param element element to be inserted
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		reSize();
		Object[] newArray = new Object[totalSize];
		for (int i = 0, j = 0; j < numElements; i++, j++) {
			if (i == index) {
				newArray[i] = element;
				i++;
			}
			newArray[i] = elementData[j];
		}
		numElements++;
		elementData = newArray;
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 *
	 * @param index the index of the element to be removed
	 * @return the element that was removed from the list
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		Object previousObj = elementData[index];
		for (int i = index; i < numElements; i++) {
			try {
				elementData[i] = elementData[i + 1];
			} catch (IndexOutOfBoundsException ex) {
				// End of list - set last element to null
				elementData[i] = null;
			}
		}
		numElements--;
		return (E) previousObj;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>, or
	 * -1 if there is no such index.
	 */
	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < numElements; i++) {
			if (elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the highest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>, or
	 * -1 if there is no such index.
	 */
	@Override
	public int lastIndexOf(Object o) {
		for (int i = numElements; i >= 0; i--) {
			if (elementData[i] == o) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * You do not need to implement any
	 * methods beyond this point. ..But if you're looking for a challenge, feel
	 * free.
	 */

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}
	
	private void reSize() {
		if (numElements == totalSize - 2) {
			totalSize += 10;
			Object[] newArray = new Object[totalSize];
			for (int i = 0; i < numElements; i++) {
				newArray[i] = elementData[i];
			}
			elementData = newArray;
		}
	}

}
