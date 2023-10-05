package textproc;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class BookReaderController 
{
    SortedListModel<Map.Entry<String, Integer>> tempModel;

    public BookReaderController(GeneralWordCounter counter) 
    {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 200, 300));
    }

    private void createWindow(GeneralWordCounter counter, String title, int width, int height) 
    {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

        tempModel = new SortedListModel<>(counter.getWordList());
        JList<Map.Entry<String, Integer>> tempList = new JList<>(tempModel);
        JScrollPane tempPlane = new JScrollPane(tempList);

        JPanel tempPanel = new JPanel(new FlowLayout());
        JButton alphButton = new JButton("Alphabetic");
        JButton freqButton = new JButton("Frequency");

        alphButton.addActionListener(x -> { tempModel.sort(Map.Entry.comparingByKey()); });
        freqButton.addActionListener(x -> { tempModel.sort((temp1, temp2) -> temp2.getValue().compareTo(temp1.getValue())); });

        tempPanel.add(alphButton);
        tempPanel.add(freqButton);

        pane.add(tempPlane);
        pane.add(tempPanel);
        
        frame.pack();
        frame.setVisible(true);
    }
}