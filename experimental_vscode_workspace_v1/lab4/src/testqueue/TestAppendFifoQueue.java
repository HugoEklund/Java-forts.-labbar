package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue 
{

    FifoQueue<Integer> q1;
    FifoQueue<Integer> q2;

    @BeforeEach
	void setUp() 
	{
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown()
	{
		q1 = null;
		q2 = null;
	}

    @Test
    void testAppendTwoEmptyQueues() 
    {   
        q1.append(q2);

        assertTrue(q1.isEmpty(), "Queue q1 is empty");
        assertTrue(q2.isEmpty(), "Queue q2 is empty");
    }

    @Test
    void testAppendEmptyQueueToNonEmptyQueue() 
    {
        q1.offer(1);
        q1.offer(2);

        q1.append(q2);

        assertEquals(2, q1.size(), "Queue q1 has size 2");
        assertTrue(q2.isEmpty(), "Queue q2 is empty");
    }

    @Test
    void testAppendNonEmptyQueueToEmptyQueue() 
    {
        q2.offer(1);
        q2.offer(2);

        q1.append(q2);

        assertEquals(2, q1.size(), "Queue q1 has size 2");
        assertTrue(q2.isEmpty(), "Queue q2 is empty");
    }

    @Test
    void testAppendTwoNonEmptyQueues()
    {   
        q1.offer(1);
        q1.offer(2);
        q2.offer(3);
        q2.offer(4);

        q1.append(q2);

        assertEquals(4, q1.size(), "Queue q1 has size 4");
        assertTrue(q2.isEmpty(), "Queue q2 is empty");

        assertEquals(Integer.valueOf(1), q1.poll());
        assertEquals(Integer.valueOf(2), q1.poll());
        assertEquals(Integer.valueOf(3), q1.poll());
        assertEquals(Integer.valueOf(4), q1.poll());
    }

    @Test
    void testAppendQueueToItself() 
    {
        q1.offer(1);
        q1.offer(2);

        assertThrows(IllegalArgumentException.class, () -> q1.append(q1));
    }
}
