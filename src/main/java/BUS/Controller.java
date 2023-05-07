package BUS;

import GUI.*;
import BEAN.DanhMucBean;
import DTO.ChucVu;
import DTO.NhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Controller {
	private JPanel root;
	private String kindSelected = "";
	private boolean eventSet = false;
	private NhanVien user = null;
	private ChucVu permission = null;

	private List<DanhMucBean> listItem = null;
	
	public Controller(JPanel root) {
		this.root = root;
	}
	
	public void initUser(NhanVien user, ChucVu permission) {
		this.user = user;
		this.permission = permission;
	}

	public NhanVien getUser() {
		return user;
	}

	public void setUser(NhanVien user) {
		this.user = user;
	}

	public ChucVu getPermission() {
		return permission;
	}

	public void setPermission(ChucVu permission) {
		this.permission = permission;
	}

	public void setView (String kind, JPanel pItem, JLabel lItem) throws InvocationTargetException {
		kindSelected = kind;
		
		String className = "GUI." + kind + "JPanel";
		Object instance = null;
		
		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getDeclaredConstructor(NhanVien.class, ChucVu.class);
			instance = constructor.newInstance(this.user, this.permission);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		pItem.setBackground(new Color(55, 55, 61));
		lItem.setBackground(new Color(55, 55, 61));
		lItem.setForeground(new Color(245, 245, 245));
		
		root.removeAll();
		root.setLayout(new BorderLayout());
		root.add((JPanel) instance);
		root.validate();
		root.repaint();
		changeBackground(kindSelected);
	}
	
	public void setEvent(List<DanhMucBean> listItem) {
		if (eventSet) {
			return;
		}
		this.listItem = listItem;
		for (DanhMucBean item : listItem) {
			item.getPanel().addMouseListener(new LabelEvent(item.getKind(), item.getPanel(), item.getLabel()));
		}
		eventSet = true;
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
//			String className = "GUI." + kind + "JPanel";
//			Object instance = null;
//
//			try {
//				Class<?> clazz = Class.forName(className);
//				instance = clazz.getDeclaredConstructor().newInstance();
//				
//				node = (JPanel) instance;
//			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
//				e1.printStackTrace();
//			}
//
//			root.removeAll();
//			root.setLayout(new BorderLayout());
//			root.add(node);
//			root.validate();
//			root.repaint();
//			changeBackground(kind);
			try {
				setView(kind, pItem, lItem);
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			kindSelected = kind;
			pItem.setBackground(new Color(31, 31, 31));
			lItem.setBackground(new Color(31, 31, 31));
			lItem.setForeground(new Color(245, 245, 245));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (kind.equals(kindSelected)) return; 
			pItem.setBackground(new Color(42, 45, 46));
			lItem.setBackground(new Color(42, 45, 46));
			lItem.setForeground(new Color(245, 245, 245));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (!kindSelected.equalsIgnoreCase(kind)) {
				pItem.setBackground(new Color(51, 51, 51));
				lItem.setBackground(new Color(51, 51, 51));
				lItem.setForeground(new Color(245, 245, 245));
			}
		}
		
		private void setChangeBackground(String kind) {
			changeBackground(kind);
		}
	}

	private void changeBackground(String kind) {
		for (DanhMucBean item : listItem) {
			if (item.getKind().equalsIgnoreCase(kind)) {
				item.getPanel().setBackground(new Color(31, 31, 31));
				item.getLabel().setBackground(new Color(31, 31, 31));
				item.getLabel().setForeground(new Color(245, 245, 245));
			} else {
				item.getPanel().setBackground(new Color(51, 51, 51));
				item.getLabel().setBackground(new Color(51, 51, 51));
				item.getLabel().setForeground(new Color(245, 245, 245));
			}
		}
	}
	
	public String getKindSelected() {
		return kindSelected;
	}

	public void setKindSelected(String kindSelected) {
		this.kindSelected = kindSelected;
	}
	
}