package GUI;


import java.awt.*;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class LoaiSanPhamJPanel extends javax.swing.JPanel {
	public LoaiSanPhamJPanel() {
		
		JLabel lblNewLabel = new JLabel("Loai san pham");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addComponent(lblNewLabel)
					.addContainerGap(328, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(120)
					.addComponent(lblNewLabel)
					.addContainerGap(166, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}