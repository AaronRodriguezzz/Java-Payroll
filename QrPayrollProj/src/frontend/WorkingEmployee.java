package frontend;

import java.awt.Color;
import db.*;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WorkingEmployee {
	
	Object workingEmpcolumns[]= {"EMPLOYEE ID", "LAST NAME", "FIRST NAME", "POSITION"};
	DefaultTableModel workingEmpmodel;
	JScrollPane workingEmpscrollPane;
	JTable workingEmptable;
	JPanel workingEmpPanel;
	JButton refresh;

	Dbclass db = new Dbclass();
	WorkingEmployee(){
		
		workingEmpmodel = new	DefaultTableModel();
		workingEmpscrollPane = new JScrollPane();
		workingEmptable = new JTable();
		
		workingEmpmodel.setColumnIdentifiers(workingEmpcolumns);
		workingEmptable.setModel(workingEmpmodel);
		workingEmptable.getTableHeader().setBackground(Color.white);
		workingEmptable.getTableHeader().setForeground(Color.black);
		workingEmptable.setSelectionBackground(Color.black);
		workingEmptable.setSelectionForeground(Color.white);
		workingEmptable.getTableHeader().setFont(new Font("Arial",Font.BOLD,13));
		workingEmptable.getTableHeader().setEnabled(false);
		workingEmptable.getTableHeader().setPreferredSize(new Dimension(0,20));
		workingEmptable.setRowHeight(30);
		
		workingEmpscrollPane = new JScrollPane(workingEmptable);
		workingEmpscrollPane.getViewport().setBackground(Color.white);
		workingEmpscrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		workingEmpscrollPane.setBounds(50,0,950,450);
		workingEmpscrollPane.setVisible(true);
		
		refresh = new JButton("REFRESH");
		refresh.setBounds(900, 460, 80, 40);
		refresh.setFocusable(false);
		refresh.setBackground(Color.GREEN);
		refresh.setForeground(Color.BLACK);
		refresh.setFont(new Font("Arial",Font.PLAIN,13));
		refresh.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		refresh.addActionListener( e -> {
			workingEmpmodel.setRowCount(0);
			db.workingEmploeyees(workingEmpmodel);
		});
		
		workingEmpmodel.setRowCount(0);
		db.workingEmploeyees(workingEmpmodel);
		
		workingEmpPanel = new JPanel();
		workingEmpPanel.setBackground(Color.white);
		workingEmpPanel.setBounds(10,80,1050,500);
		workingEmpPanel.setVisible(false);
		workingEmpPanel.setLayout(null);
		workingEmpPanel.add(workingEmpscrollPane);
		workingEmpPanel.add(refresh);
		
	}
}
