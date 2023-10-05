package textproc;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication 
{
    public static void main(String[] args) throws FileNotFoundException
    {
		ArrayList<TextProcessor> tempList = new ArrayList<TextProcessor>();
		Set<String> avoidWords = new HashSet<>();
		Scanner tempScanner = new Scanner(new File("C:\\Users\\eklun\\Documents\\GitHub\\Java-forts.-labbar\\experimental_vscode_workspace_v1\\lab3\\undantagsord.txt"));
        Scanner s = new Scanner(new File("C:\\Users\\eklun\\Documents\\GitHub\\Java-forts.-labbar\\experimental_vscode_workspace_v1\\lab3\\nilsholg.txt"));

		GeneralWordCounter wordCounter = new GeneralWordCounter(avoidWords);
        tempList.add(wordCounter);

		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

		while (tempScanner.hasNext())
		{
			avoidWords.add(tempScanner.next().toLowerCase());
		}
		tempScanner.close();

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

		new BookReaderController(wordCounter);
	}
}