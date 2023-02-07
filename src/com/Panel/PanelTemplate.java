package com.Panel;

import com.config.Config;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;

public class PanelTemplate {
 public static void panelGetAllData(String title, JPanel mainPanel) {
  JPanel getDataPanel = new JPanel();
  getDataPanel.setBounds(0, 0, 1024, 624);
  getDataPanel.setBackground(new Color(196, 213, 229));
  JLabel titleLabel = new JLabel(title);
  titleLabel.setBounds(20, 0, 400, 100);
  titleLabel.setFont(new Font("Arial", Font.PLAIN, 40));

  String[] columnName = { "ID Barang", "Nama Barang",
    "Deskripsi Barang", "Harga Barang" };
  JTable tableData = new JTable(Config.getAllData(), columnName);
  JScrollPane tableScroll = new JScrollPane(tableData);
  tableScroll.setBounds(20, 80, 700, 300);

  getDataPanel.add(titleLabel);
  getDataPanel.add(tableScroll);
  getDataPanel.setLayout(null);
  mainPanel.add(getDataPanel);
  mainPanel.repaint();
  mainPanel.revalidate();
 }
}
