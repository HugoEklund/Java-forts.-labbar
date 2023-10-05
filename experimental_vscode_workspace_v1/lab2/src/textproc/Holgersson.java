package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Holgersson 
{
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException 
	{
		long t0 = System.nanoTime();

		ArrayList<TextProcessor> tempList = new ArrayList<TextProcessor>();
		Set<String> avoidWords = new HashSet<>();
		Scanner tempScanner = new Scanner(new File("C:\\Users\\eklun\\Documents\\GitHub\\Java-forts.-labbar\\experimental_vscode_workspace_v1\\lab2\\undantagsord.txt"));
		Scanner s = new Scanner(new File("C:\\Users\\eklun\\Documents\\GitHub\\Java-forts.-labbar\\experimental_vscode_workspace_v1\\lab2\\nilsholg.txt"));

		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

		while (tempScanner.hasNext())
		{
			avoidWords.add(tempScanner.next().toLowerCase());
		}
		tempScanner.close();

		tempList.add(new GeneralWordCounter(avoidWords));

		while (s.hasNext())
		{
			String word = s.next().toLowerCase();

			for (TextProcessor i : tempList)
			{
				i.process(word);
			}
		}
		s.close();

		for (TextProcessor i : tempList)
		{
			i.report();
		}

			long t1 = System.nanoTime();
			System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
	}
}