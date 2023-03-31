package GUI;


import java.awt.*;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class BanHangJPanel extends javax.swing.JPanel {
	public BanHangJPanel() {
		
		JLabel lblNewLabel = new JLabel("Panel ban hang");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(lblNewLabel)
					.addContainerGap(333, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(lblNewLabel)
					.addContainerGap(243, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
}