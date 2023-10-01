package textproc;
import java.util.*;

public class MultiWordCounter implements TextProcessor
{
    private Map<String, Integer> tempMap;

    public MultiWordCounter(String[] tempArray)
    {
        tempMap = new TreeMap<>();

        for (String i : tempArray)
        {
            tempMap.put(i, 0);
        }
    }

    public void process(String w)
    {
		if (tempMap.containsKey(w))
		{
			tempMap.put(w, tempMap.get(w) + 1);
		}
    }

    public void report()
    {
        for (Map.Entry<String, Integer> i : tempMap.entrySet())
        {
            System.out.println(i.getKey() + ": " + i.getValue());
        }
    }
}
