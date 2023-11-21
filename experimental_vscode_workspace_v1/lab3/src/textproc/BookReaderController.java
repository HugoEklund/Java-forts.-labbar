package textproc;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BookReaderController 
{
    SortedListModel<Map.Entry<String, Integer>> aModel;
    KeyEvent tempEvent;

    public BookReaderController(GeneralWordCounter counter) 
    {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 300, 200));
    }

    private void createWindow(GeneralWordCounter counter, String title, int width, int height) 
    {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

        List<Map.Entry<String, Integer>> wordCount = counter.getWordList();

        wordCount.removeIf(x -> x.getKey().chars().anyMatch(Character::isDigit));
        aModel = new SortedListModel<>(wordCount);

        JList<Map.Entry<String, Integer>> aList = new JList<>(aModel);
        JScrollPane scrollPlane = new JScrollPane(aList);

        JPanel viewPanel = new JPanel(new FlowLayout());
        JPanel searchPanel = new JPanel(new FlowLayout());
        JRadioButton alphButton = new JRadioButton("Alphabetic");
        JRadioButton freqButton = new JRadioButton("Frequency");
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(alphButton);
        buttonGroup.add(freqButton);

        alphButton.addActionListener(x -> { aModel.sort(Map.Entry.comparingByKey()); });
        freqButton.addActionListener(x -> { aModel.sort((temp1, temp2) -> temp2.getValue().compareTo(temp1.getValue())); });
        searchButton.addActionListener(e -> 
        { 
            boolean wasFound = false;
            for (int i = 0; i < aModel.getSize(); i++)
            {
                if (aModel.getElementAt(i).getKey().equalsIgnoreCase(searchField.getText()))
                {
                    aList.setSelectedValue(aModel.getElementAt(i), true);
                    aList.ensureIndexIsVisible(aList.getSelectedIndex());
                    wasFound = true;
                }
            }

            if (!wasFound)
            {
                JOptionPane.showMessageDialog(null, "Ordet hittades ej.", "Missing", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        searchField.addActionListener(e -> 
        {
            searchButton.doClick();
        });

        pane.add(scrollPlane);
        pane.add(viewPanel, BorderLayout.SOUTH);
        pane.add(searchPanel, BorderLayout.NORTH);

        viewPanel.add(alphButton);
        viewPanel.add(freqButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        frame.pack();
        frame.setVisible(true);
    }
}