package BEAN;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMucBean {
	private String kind;
	private JPanel panel;
	private JLabel label;
	public DanhMucBean(String kind, JPanel panel, JLabel label) {
		this.kind = kind;
		this.panel = panel;
		this.label = label;
	}
	public DanhMucBean() {
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	
}