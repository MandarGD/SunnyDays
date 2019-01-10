package sunnydays;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class Notepad {
	JFrame frame;
	JMenuBar menuBar;
	JMenu file;
	JMenuItem open, save, exit;
	JFileChooser fileChooser;
	JTextArea textArea;
	
	Notepad() {
		frame = new JFrame("Notepad Application");
		file = new JMenu("File");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		textArea = new JTextArea();
		fileChooser = new JFileChooser();
		menuBar = new JMenuBar();
		
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(textArea);
		file.add(open);
		file.add(save);
		file.add(exit);
		menuBar.add(file);
		frame.setJMenuBar(menuBar);
		
		OpenListener openL = new OpenListener();
		SaveListener saveL = new SaveListener();
		ExitListener exitL = new ExitListener();
		open.addActionListener(openL);
		save.addActionListener(saveL);
		exit.addActionListener(exitL);
		
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
	
	class OpenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(frame)) {
				File file = fileChooser.getSelectedFile();
				textArea.setText("");
				Scanner in = null;
				try {
					in = new Scanner(file);
					while(in.hasNext()) {
						String line = in.nextLine();
						textArea.append(line+"\n");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					in.close();
				}
			}
		}
	}
	
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(frame)) {
				File file = fileChooser.getSelectedFile();
				PrintWriter out = null;
				try {
					out = new PrintWriter(file);
					String output = textArea.getText();
					System.out.println(output);
					out.println(output);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {out.flush();} catch(Exception ex1) {}
					try {out.close();} catch(Exception ex1) {}
				}
			}
		}
	}
	
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public static void main(String args[]) {
		Notepad n = new Notepad();
	}
}

