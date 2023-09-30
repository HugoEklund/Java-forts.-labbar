package textproc;
import java.util.*;

public class GeneralWordCounter implements TextProcessor
{
    private Map<String, Integer> tempMap;
    private Set<String> tempSet;

    public GeneralWordCounter(Set<String> tempSet)
    {
        tempMap = new HashMap<>();
        this.tempSet = tempSet;
    }

    public void process(String w)
    {
		if (tempMap.containsKey(w) && !tempSet.contains(w))
		{
			tempMap.put(w, tempMap.get(w) + 1);
		}
    }

    public void report()
    {
        for (Map.Entry<String, Integer> i : tempMap.entrySet())
        {
            if (i.getValue() >= 200)
            {
                System.out.println(i.getKey() + ": " + i.getValue());
            }
        }
    }
}
