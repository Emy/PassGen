package eu.devem.passgen;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame implements MouseListener, ChangeListener, ItemListener, ActionListener {
	private static final long serialVersionUID = 5171539746112047596L;

	private Controller controller;
	
	private JFrame frame;
	private JPanel panel;
	private JSlider sliderPasswordLength;
	private JCheckBox checkBoxUppercaseLetters, checkBoxLowercaseLetters, checkBoxSpecialCharacters, checkBoxNumbers;
	private JTextField textFieldPassword;
	private JButton buttonRegenerate, buttonCopyToClipboard;

	public GUI(Controller controller) {
		this.controller = controller;
	}

	public void initGUI() {
		initFrame();
	}
	
	private void initFrame() {
		frame = new JFrame("PassGen");
		frame.setSize(500, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		initPanel();
	}
	
	private void initPanel() {
		panel = new JPanel();
		panel.setBounds(0, 0, 500, 250);
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.addMouseListener(this);
		frame.add(panel);
		
		sliderPasswordLength = new JSlider(JSlider.HORIZONTAL,0,40,20);
		sliderPasswordLength.setBounds(10, 0, 470, 60);
		sliderPasswordLength.setMajorTickSpacing(10);
		sliderPasswordLength.setMinorTickSpacing(1);
		sliderPasswordLength.setPaintTicks(true);
		sliderPasswordLength.setPaintLabels(true);
		sliderPasswordLength.setBackground(Color.WHITE);
		sliderPasswordLength.addChangeListener(this);
		sliderPasswordLength.setBorder(BorderFactory.createTitledBorder("Password length"));
		sliderPasswordLength.setFocusable(false);
		panel.add(sliderPasswordLength);
		
		checkBoxUppercaseLetters = new JCheckBox("Uppercase letters");
		checkBoxUppercaseLetters.setBounds(10, 65, 150, 15);
		checkBoxUppercaseLetters.setBackground(Color.WHITE);
		checkBoxUppercaseLetters.addItemListener(this);
		checkBoxUppercaseLetters.setFocusable(false);
		checkBoxUppercaseLetters.setSelected(true);
		panel.add(checkBoxUppercaseLetters);
		
		checkBoxLowercaseLetters = new JCheckBox("Lowercase letters");
		checkBoxLowercaseLetters.setBounds(10, 80, 150, 15);
		checkBoxLowercaseLetters.setBackground(Color.WHITE);
		checkBoxLowercaseLetters.addItemListener(this);
		checkBoxLowercaseLetters.setFocusable(false);
		checkBoxLowercaseLetters.setSelected(true);
		panel.add(checkBoxLowercaseLetters);
		
		checkBoxSpecialCharacters = new JCheckBox("Special characters");
		checkBoxSpecialCharacters.setBounds(10, 95, 150, 15);
		checkBoxSpecialCharacters.setBackground(Color.WHITE);
		checkBoxSpecialCharacters.addItemListener(this);
		checkBoxSpecialCharacters.setFocusable(false);
		checkBoxSpecialCharacters.setSelected(true);
		panel.add(checkBoxSpecialCharacters);
		
		checkBoxNumbers = new JCheckBox("Numbers");
		checkBoxNumbers.setBounds(10, 110, 150, 15);
		checkBoxNumbers.setBackground(Color.WHITE);
		checkBoxNumbers.addItemListener(this);
		checkBoxNumbers.setFocusable(false);
		checkBoxNumbers.setSelected(true);
		panel.add(checkBoxNumbers);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(10, 130, 470, 40);
		textFieldPassword.setEditable(false);
		textFieldPassword.setBackground(Color.WHITE);
		textFieldPassword.setBorder(BorderFactory.createTitledBorder("Password"));
		panel.add(textFieldPassword);
		
		buttonRegenerate = new JButton("Regenerate");
		buttonRegenerate.setBounds(10, 180, 150, 20);
		buttonRegenerate.addActionListener(this);
		buttonRegenerate.setFocusable(false);
		panel.add(buttonRegenerate);
		
		buttonCopyToClipboard = new JButton("Copy to clipboard");
		buttonCopyToClipboard.setBounds(170, 180, 150, 20);
		buttonCopyToClipboard.addActionListener(this);
		buttonCopyToClipboard.setFocusable(false);
		panel.add(buttonCopyToClipboard);
		
		panel.repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void stateChanged(ChangeEvent e) {
//		if(e.getSource().equals(sliderPasswordLength)) {
//			JSlider slider = (JSlider) e.getSource();
//			if(!slider.getValueIsAdjusting()) {
//				controller.generatePassword();
//			}
//		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		 if(e.getSource().equals(checkBoxUppercaseLetters)) {
			// controller.generatePassword();
		 }
		 
		 if(e.getSource().equals(checkBoxLowercaseLetters)) {
			// controller.generatePassword();
		 }
		 
		 if(e.getSource().equals(checkBoxSpecialCharacters)) {
			// controller.generatePassword();
		 }
		 
		 if(e.getSource().equals(checkBoxNumbers)) {
			// controller.generatePassword();
		 }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Regenerate")) {
			if(checkBoxUppercaseLetters.isSelected() || checkBoxLowercaseLetters.isSelected() || checkBoxSpecialCharacters.isSelected() || checkBoxNumbers.isSelected()) {
				if(sliderPasswordLength.getValue()>0) {
					controller.generatePassword();
				}
			}
		}
		if(e.getActionCommand().equals("Copy to clipboard")) {
			StringSelection stringSelection = new StringSelection(getTextFieldPassword());
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}
	}

	public int getSliderPasswordLength() {
		return sliderPasswordLength.getValue();
	}

	public boolean getCheckBoxUppercaseLetters() {
		return checkBoxUppercaseLetters.isSelected();
	}

	public boolean getCheckBoxLowercaseLetters() {
		return checkBoxLowercaseLetters.isSelected();
	}

	public boolean getCheckBoxSpecialCharacters() {
		return checkBoxSpecialCharacters.isSelected();
	}

	public boolean getCheckBoxNumbers() {
		return checkBoxNumbers.isSelected();
	}

	public void setTextFieldPassword(String s) {
		textFieldPassword.setText(s);
	}
	
	public String getTextFieldPassword() {
		return textFieldPassword.getText();
	}

}
