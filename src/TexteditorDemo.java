import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TexteditorDemo extends JFrame {
    JFrame frame = new JFrame();
    JPanel panel1 = new JPanel();
    JLabel filnamn = new JLabel("Filnamn:");
    JTextField fileTextField = new JTextField(10);
    JButton open = new JButton("Öppna");
    JButton save = new JButton("Spara");
    JButton print = new JButton("Skriv ut");
    JButton quit = new JButton("Avsluta");
    JTextArea textArea = new JTextArea(20, 45);
    JScrollPane scrollPane = new JScrollPane(textArea);

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quit) {
                System.exit(0);
            } else if (e.getSource() == open) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(frame);
                fileChooser.setAcceptAllFileFilterUsed(false);
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    FileReader fileReader = new FileReader(selectedFile);
                    textArea.read(fileReader, null);
                    fileTextField.setText(selectedFile.getAbsolutePath());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == print) {
                try {
                    textArea.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == save) {
                try {
                    FileWriter fileWriter = new FileWriter(fileTextField.getText());
                    textArea.write(fileWriter);
                    fileWriter.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                fileTextField.getText();
            }
        }
    }

    TexteditorDemo() {
        add(panel1);
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel1.setLayout(new FlowLayout());
        panel1.add(filnamn);
        panel1.add(fileTextField);
        panel1.add(open);
        panel1.add(save);
        panel1.add(print);
        panel1.add(quit);
        panel1.add(scrollPane);
        setResizable(false);
        textArea.setLineWrap(true);
        MyListener myListener = new MyListener();
        open.addActionListener(myListener);
        save.addActionListener(myListener);
        print.addActionListener(myListener);
        quit.addActionListener(myListener);

    }

    public static void main(String[] args) {
        TexteditorDemo teD = new TexteditorDemo();

    }

}
