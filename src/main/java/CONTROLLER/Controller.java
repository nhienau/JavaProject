package CONTROLLER;

import GUI.*;
import BEAN.DanhMucBean;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Controller {
	private JPanel root;
	private String kindSelected = "";
	
	private List<DanhMucBean> listItem = null;
	
	public Controller(JPanel root) {
		this.root = root;
	}
	
	public void setView (JPanel pItem, JLabel lItem) {
		kindSelected = "BanHang";
		
		pItem.setBackground(new Color(96, 100, 191));
		lItem.setBackground(new Color(96, 100, 191));
		
		root.removeAll();
		root.setLayout(new BorderLayout());
		root.add(new BanHangJPanel());
		root.validate();
		root.repaint();
	}
	
	public void setEvent(List<DanhMucBean> listItem) {
		this.listItem = listItem;
		for (DanhMucBean item : listItem) {
			item.getLabel().addMouseListener(new LabelEvent(item.getKind(), item.getPanel(), item.getLabel()));
		}
	}
	
	class LabelEvent implements MouseListener {
		private JPanel node;
		private String kind;
		
		private JPanel pItem;
		private JLabel lItem;
		
		
		
		public LabelEvent(String kind, JPanel pItem, JLabel lItem) {
			super();
			this.kind = kind;
			this.pItem = pItem;
			this.lItem = lItem;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			switch (kind) { 
				case "BanHang":
					node = new BanHangJPanel();
					break;
				case "SanPham":
					node = new SanPhamJPanel();
					break;
				case "LoaiSanPham":
					node = new LoaiSanPhamJPanel();
					break;
				default:
					node = new BanHangJPanel();
			}
			root.removeAll();
			root.setLayout(new BorderLayout());
			root.add(node);
			root.validate();
			root.repaint();
			setChangeBackground(kind);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			kindSelected = kind;
			pItem.setBackground(new Color(96, 100, 191));
			lItem.setBackground(new Color(96, 100, 191));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			pItem.setBackground(new Color(96, 100, 191));
			lItem.setBackground(new Color(96, 100, 191));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (!kindSelected.equalsIgnoreCase(kind)) {
				pItem.setBackground(UIManager.getColor("Button.background"));
				lItem.setBackground(UIManager.getColor("Button.background"));
			}
		}
		
		private void setChangeBackground(String kind) {
			for (DanhMucBean item : listItem) {
				if (item.getKind().equalsIgnoreCase(kind)) {
					item.getPanel().setBackground(new Color(96, 100, 191));
					item.getLabel().setBackground(new Color(96, 100, 191));
				} else {
					item.getPanel().setBackground(UIManager.getColor("Button.background"));
					item.getLabel().setBackground(UIManager.getColor("Button.background"));
				}
			}
		}
	}
}