package com.PintsizedSix40.CustomCrypt;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;

public class FrameHandler {

	public static JFrame frame;
	
	public static void setup() {
		HashMap<String, CryptHandler> handlers = new HashMap<String, CryptHandler>();
		
		handlers.put("PASS", Algorithms.Pass);
		handlers.put("Custom", Algorithms.Custom);
		handlers.put("AES", Algorithms.AES);
		handlers.put("3DES", Algorithms.DES);
		handlers.put("Blowfish", Algorithms.Blowfish);
		handlers.put("BDS", Algorithms.BDS);
		handlers.put("BAS", Algorithms.BAS);
		
		
		JTextPane cc = new JTextPane();
		cc.setEditable(false);
		cc.setText("CustomCrypt");
		cc.setBounds(100, 0, 500, 100); 
		cc.setOpaque(false);
		cc.setFont(new Font(Font.SERIF, 3, 50));
		
		JTextPane in = new JTextPane();
		in.setEditable(false);
		in.setText("Input:");
		in.setBounds(0, 150, 100, 50); 
		in.setOpaque(false);
		in.setFont(new Font(Font.SERIF, 3, 20));
		
		JTextField input = new JTextField();
		input.setEditable(true);
		input.setBounds(70, 157, 100, 25);
		
		JComboBox<String> o1 = makeBox(1);
		JComboBox<String> o2 = makeBox(2);
		JComboBox<String> o3 = makeBox(3);
		JComboBox<String> o4 = makeBox(4);
		JComboBox<String> o5 = makeBox(5);
		JComboBox<String> o6 = makeBox(6);
		JComboBox<String> o7 = makeBox(7);
		JComboBox<String> o8 = makeBox(8);
		JComboBox<String> o9 = makeBox(9);
		JComboBox<String> o10 = makeBox(10);
		JComboBox<String> o11 = makeBox(11);
		JComboBox<String> o12 = makeBox(12);
		JComboBox<String> o13 = makeBox(13);
		JComboBox<String> o14 = makeBox(14);
		JComboBox<String> o15 = makeBox(15);
		JComboBox<String> o16 = makeBox(16);
		JComboBox<String> o17 = makeBox(17);
		JComboBox<String> o18 = makeBox(18);
		
		JComboBox[] combos = {o1,o2,o3,o4,o5,o6,o7,o8,o9,o10,o11,o12,o13,o14,o15,o16,o17,o18};
		
		JTextField f1 = makeText(1);
		JTextField f2 = makeText(2);
		JTextField f3 = makeText(3);
		JTextField f4 = makeText(4);
		JTextField f5 = makeText(5);
		JTextField f6 = makeText(6);
		JTextField f7 = makeText(7);
		JTextField f8 = makeText(8);
		JTextField f9 = makeText(9);
		JTextField f10 = makeText(10);
		JTextField f11 = makeText(11);
		JTextField f12 = makeText(12);
		JTextField f13 = makeText(13);
		JTextField f14 = makeText(14);
		JTextField f15 = makeText(15);
		JTextField f16 = makeText(16);
		JTextField f17 = makeText(17);
		JTextField f18 = makeText(18);
		
		JTextField[] text = {f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18};
		
		JTextPane out = new JTextPane();
		out.setEditable(false);
		out.setOpaque(false);
		out.setBounds(50, 675, 400, 400);
		out.setFont(new Font(Font.SERIF, 1, 12));
		
		
		
		JButton encrypt = new JButton();
		encrypt.setText("Encrypt");
		encrypt.setFont(new Font(Font.SERIF, 3, 20));
		encrypt.setBounds(50, 800, 100, 50);
		encrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String val = input.getText();
				for(int i = 0; i < 18; i++) {
					val = handlers.get(combos[i].getSelectedItem().toString()).encrypt(text[i].getText(), val);
				}
				out.setText(val.replaceAll("\\n", ""));
				
			}
			
		});
		
		JButton save = new JButton();
		save.setText("Save");
		save.setFont(new Font(Font.SERIF, 3, 20));
		save.setBounds(155, 800, 85, 50);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame s = new JFrame("Save");
				
				JTextPane p = new JTextPane();
				p.setFont(new Font(Font.SERIF, 3, 20));
				p.setOpaque(false);
				p.setText("Pass: ");
				p.setEditable(false);
				p.setBounds(50, 25, 50, 50);
				
				JTextField f = new JTextField();
				f.setEditable(true);
				f.setBounds(100, 30, 125, 25);
				
				JButton sa = new JButton();
				sa.setText("Save");
				sa.setBounds(90, 100, 100, 50);
				
				s.add(p);
				s.add(f);
				s.add(sa);
				
				s.setResizable(false);
				s.setLayout(null);
				s.setSize(300, 200);
				s.setVisible(true);
				s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
		});
		
		JButton load = new JButton();
		load.setText("Load");
		load.setFont(new Font(Font.SERIF, 3, 20));
		load.setBounds(255, 800, 85, 50);
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame s = new JFrame("Load");
				
				JTextPane p = new JTextPane();
				p.setFont(new Font(Font.SERIF, 3, 20));
				p.setOpaque(false);
				p.setText("Pass: ");
				p.setEditable(false);
				p.setBounds(50, 25, 50, 50);
				
				JTextField f = new JTextField();
				f.setEditable(true);
				f.setBounds(100, 30, 125, 25);
				
				JButton sa = new JButton();
				sa.setText("Load");
				sa.setBounds(90, 100, 100, 50);
				
				s.add(p);
				s.add(f);
				s.add(sa);
				
				s.setResizable(false);
				s.setLayout(null);
				s.setSize(300, 200);
				s.setVisible(true);
				s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
		});
		
		
		JButton decrypt = new JButton();
		decrypt.setText("Decrypt");
		decrypt.setFont(new Font(Font.SERIF, 3, 20));
		decrypt.setBounds(345, 800, 100, 50);
		decrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String val = input.getText();
				for(int i = 17; i > -1; i--) {
					val = handlers.get(combos[i].getSelectedItem().toString()).decrypt(text[i].getText(), val);
				}
				out.setText(val.replaceAll("\\n", ""));			
			}
			
		});
		
		
		
		
		frame=new JFrame("CustomCrypt");
		frame.add(cc);
		frame.add(in);
		frame.add(input);
		frame.add(o1);
		frame.add(o2);
		frame.add(o3);
		frame.add(o4);
		frame.add(o5);
		frame.add(o6);
		frame.add(o7);
		frame.add(o8);
		frame.add(o9);
		frame.add(o10);
		frame.add(o11);
		frame.add(o12);
		frame.add(o13);
		frame.add(o14);
		frame.add(o15);
		frame.add(o16);
		frame.add(o17);
		frame.add(o18);
		
		frame.add(f1);
		frame.add(f2);
		frame.add(f3);
		frame.add(f4);
		frame.add(f5);
		frame.add(f6);
		frame.add(f7);
		frame.add(f8);
		frame.add(f9);
		frame.add(f10);
		frame.add(f11);
		frame.add(f12);
		frame.add(f13);
		frame.add(f14);
		frame.add(f15);
		frame.add(f16);
		frame.add(f17);
		frame.add(f18);
		
		genText();
		
		frame.add(encrypt);
		frame.add(save);
		frame.add(load);
		frame.add(decrypt);
		frame.add(out);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(500, 900); 
		frame.setLayout(null); 
		frame.setResizable(false);
		frame.setVisible(true); 
	}
	
	private static JComboBox<String> makeBox(int num){
		JComboBox<String> algos = new JComboBox<String>();
		algos.setOpaque(false);
		algos.addItem("PASS");
		algos.addItem("AES");
		algos.addItem("BDS");
		algos.addItem("3DES");
		algos.addItem("Blowfish");
		algos.addItem("BAS");
		algos.addItem("Custom");
		algos.setBounds(30, 175+(25*num), 100, 25);
		return algos;
	}
	
	private static JTextField makeText(int num) {
		JTextField e = new JTextField();
		e.setBounds(200,175+(25*num) , 200, 25);
		return e;
	}
	
	private static void genText() {
		for(int i = 0; i < 18; i++) {
			JTextPane t = new JTextPane();
			t.setText("Key: ");
			t.setOpaque(false);
			t.setFont(new Font(Font.SERIF, 3, 15));
			t.setEditable(false);
			t.setBounds(150,175+(25*(i+1)) , 50, 50);
			frame.add(t);
		}
	}
}
