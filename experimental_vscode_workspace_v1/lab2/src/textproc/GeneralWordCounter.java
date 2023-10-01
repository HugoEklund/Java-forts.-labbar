package textproc;
import java.util.*;

public class GeneralWordCounter implements TextProcessor
{
    private Map<String, Integer> tempMap;
    private Set<String> tempSet;

    public GeneralWordCounter(Set<String> tempSet)
    {
        tempMap = new TreeMap<>();
        this.tempSet = tempSet;
    }

    public void process(String w)
    {
		if (!tempSet.contains(w))
		{
			tempMap.put(w, tempMap.getOrDefault(w, 0) + 1);
		}
    }

    public void report()
    {
        Set<Map.Entry<String, Integer>> wordSet = tempMap.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

        wordList.sort((temp1, temp2) -> 
        {
            if (temp2.getValue().compareTo(temp1.getValue()) == 0)
            {
                return temp1.getKey().compareTo(temp2.getKey());
            }
            return temp2.getValue().compareTo(temp1.getValue());
        });

        for (Map.Entry<String, Integer> i : wordList)
        {
            if (i.getValue() > 150)
            {
                System.out.println(i.getKey() + ": " + i.getValue());
            }
        }
    }
}
