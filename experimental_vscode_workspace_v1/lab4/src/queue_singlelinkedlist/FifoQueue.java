package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> 
{
	private QueueNode<E> last;
	private int size;

	public FifoQueue() 
	{
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible.
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e)
	{
		QueueNode<E> tempNode = new QueueNode<>(e);

		if (size == 0)
		{
			last = tempNode;
			last.next = last;
		}
		else
		{
			tempNode.next = last.next;
            last.next = tempNode;
            last = tempNode;
		}

		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size()
	{		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() 
	{
		if (size == 0)
		{
			return null;
		}

		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() 
	{
		if (size == 0)
		{
			return null;
		}
		E tempNode = last.next.element;

		if (size == 1)
		{
			last = null;
		}
		else
		{
			last.next = last.next.next;
		}
		size--;
		return tempNode;
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) 
	{
		if (this == q) 
		{
			throw new IllegalArgumentException("Cannot append queue to itself");
		}
	
		if (q.isEmpty()) 
		{
			return;
		}
	
		if (this.isEmpty()) 
		{
			this.last = q.last;
		}
		else 
		{
			QueueNode<E> thisFirst = this.last.next;
			QueueNode<E> qFirst = q.last.next;
			this.last.next = qFirst;
			q.last.next = thisFirst;
			this.last = q.last;
		}
	
		this.size += q.size;
		q.size = 0;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() 
	{
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> 
	{
        private QueueNode<E> pos;

        private QueueIterator()
		 {
            pos = (last != null && last.next != null) ? last.next : null;
        }

        public boolean hasNext() 
		{
            return pos != null;
        }

        public E next() 
		{
            if (!hasNext()) 
			{
                throw new NoSuchElementException();
            }

			E tempNode = pos.element;

            pos = (pos.next != last.next) ? pos.next : null;

			return tempNode;
        }
    }

	private static class QueueNode<E> 
	{
		E element;
		QueueNode<E> next;

		private QueueNode(E x) 
		{
			element = x;
			next = null;
		}
	}

}