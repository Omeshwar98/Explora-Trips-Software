package explora.trips;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;

public class AllCustomerPanel extends JPanel{
    
    JPanel panel;
    JLabel l1;
    JTable table;
    JScrollPane tableviewscroll;
    
    AllCustomerPanel(){
        
                setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(Color.WHITE);
		this.setSize(1278, 900);
		setLayout(null); 
        
                panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(7, 7, 1278, 160);
		add(panel);
		panel.setLayout(null);
		
		l1 = new JLabel("All Customers");
		l1.setIcon(null);
		l1.setBounds(5, 55, 224, 45);
		panel.add(l1);
		l1.setBackground(new Color(32, 178, 170));
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Segoe UI", Font.BOLD, 30));
                
                tableviewscroll = new JScrollPane();
		tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableviewscroll.setBounds(7, 172, 1271, 617);
		for(Component c :  tableviewscroll.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(tableviewscroll);
                  
                table = new JTable();
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.getTableHeader().setBackground(new Color(32,178,170));
		table.getTableHeader().setForeground(Color.white);
		table.setSelectionBackground(new Color(240, 255, 255));
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		table.setFont(new Font("Segoe UI",Font.PLAIN,20));
		table.getTableHeader().setPreferredSize(new Dimension(50,40));
		table.setFocusable(false);
		table.setDragEnabled(false);
		table.setRowHeight(40);
		table.setDefaultEditor(Object.class, null);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);
                tableviewscroll.setViewportView(table);
                
                try{
                 Conn conn = new Conn();
                 String sql = "select name as 'Customer Name',username as 'Username',password as 'Password',pancard as 'Pancard No.',phone as 'Phone No.',email as 'Email Id' from customer" ;
                 PreparedStatement ps = conn.c.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery();
		 if(rs!=null){
			table.setModel(DbUtils.resultSetToTableModel(rs));
                 }
		 table.getColumnModel().getColumn(0).setMaxWidth(200);
		 table.getColumnModel().getColumn(1).setMaxWidth(200);
                 table.getColumnModel().getColumn(2).setMaxWidth(190);
                 table.getColumnModel().getColumn(3).setMaxWidth(190);
                 table.getColumnModel().getColumn(4).setMaxWidth(190);
                 table.getColumnModel().getColumn(5).setMaxWidth(301);
            } catch(Exception ae){
            System.out.println(ae);}
    }
}
