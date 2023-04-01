package GUI;


import java.awt.*;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SanPhamJPanel extends javax.swing.JPanel {
	public SanPhamJPanel() {
		
		JLabel lblNewLabel = new JLabel("San pham");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel)
					.addContainerGap(356, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(47)
					.addComponent(lblNewLabel)
					.addContainerGap(239, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	
	
}