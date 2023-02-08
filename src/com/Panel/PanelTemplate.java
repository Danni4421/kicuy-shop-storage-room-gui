package com.Panel;

import com.config.Config;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.sql.ResultSet;
import java.util.ArrayList;

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

  public static void panelSearchData(String title, JPanel mainPanel) {
    JPanel searchPanel = new JPanel();
    searchPanel.setBounds(0, 0, 1024, 624);
    searchPanel.setBackground(new Color(196, 213, 229));
    JLabel titleLabel = new JLabel(title);
    titleLabel.setBounds(20, 0, 400, 100);
    titleLabel.setFont(new Font("Arial", Font.PLAIN, 40));

    JLabel txtFieldSearch = new JLabel("Masukkan keyword");
    txtFieldSearch.setBounds(titleLabel.getX(),
        titleLabel.getY() + 65, 300, 100);
    txtFieldSearch.setFont(new Font("Arial", Font.PLAIN, 18));

    JTextField searchField = new JTextField();
    searchField.setBounds(txtFieldSearch.getX(), txtFieldSearch.getY() + 70,
        400, 40);
    searchField.setMargin(new Insets(10, 10, 10, 10));

    // search icon
    JButton searchBtn = new JButton("Cari");
    searchBtn.setBounds(searchField.getX() + 400, searchField.getY(), 70, 40);

    searchBtn.addActionListener(e -> {

      if (!searchField.getText().equals("")) {

        String[] columnName = { "ID Barang", "Nama Barang",
            "Deskripsi Barang", "Harga Barang" };

        JTable resultSearching = new JTable(getSearchedData(searchField.getText()), columnName);
        JScrollPane scrollTable = new JScrollPane(resultSearching);
        scrollTable.setBounds(searchField.getX(), searchField.getY() + 60,
            700, 300);
        searchPanel.add(scrollTable);

      } else {
        JOptionPane.showMessageDialog(null, "Mohon masukkan keyword pencarian");
      }

    });

    // add
    searchPanel.add(titleLabel);
    searchPanel.add(txtFieldSearch);
    searchPanel.add(searchField);
    searchPanel.add(searchBtn);
    searchPanel.setLayout(null);
    mainPanel.add(searchPanel);
    mainPanel.repaint();
    mainPanel.revalidate();
  }

  private static String[][] getSearchedData(String keyword) {

    ArrayList<Integer> idBarang = new ArrayList<Integer>();
    ArrayList<String> namaBarang = new ArrayList<String>();
    ArrayList<String> deskripsiBarang = new ArrayList<String>();
    ArrayList<Integer> hargaBarang = new ArrayList<Integer>();
    ResultSet result = Config.getSearchedData(keyword);

    try {

      while (result.next()) {
        idBarang.add(result.getInt("idBarang"));
        namaBarang.add(result.getString("namaBarang"));
        deskripsiBarang.add(result.getString("deskripsiBarang"));
        hargaBarang.add(result.getInt("hargaBarang"));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    String[][] data = new String[idBarang.size()][4];

    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[0].length; j++) {
        switch (j) {
          case 0:
            data[i][j] = idBarang.get(i).toString();
            break;
          case 1:
            data[i][j] = namaBarang.get(i);
            break;
          case 2:
            data[i][j] = deskripsiBarang.get(i);
            break;
          case 3:
            data[i][j] = hargaBarang.get(i).toString();
            break;
        }
      }
    }

    return data;
  }
}
