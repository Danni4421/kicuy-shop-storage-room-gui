package com.Panel;

import com.config.Config;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    JTable tableData = new JTable(Config.getAllData(false, ""), columnName);
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

  private static int idBarang = 0;

  public static void panelUpdateData(String title, JPanel mainPanel) {

    JPanel panelUpdate = new JPanel();
    panelUpdate.setBounds(0, 0, 1024, 624);
    panelUpdate.setBackground(new Color(196, 213, 229));

    JLabel panelLabel = new JLabel("Tambah & Update Data");
    panelLabel.setBounds(20, 0, 400, 50);
    panelLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    panelUpdate.add(panelLabel);

    // txtField name
    JLabel nameLabel = new JLabel("Nama Barang");
    nameLabel.setBounds(20, panelLabel.getY() + 50, 150, 50);
    nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    panelUpdate.add(nameLabel);
    JTextField txtName = new JTextField();
    txtName.setMargin(new Insets(0, 10, 0, 10));
    txtName.setBounds(nameLabel.getX() + 120, nameLabel.getY(), 300, 40);
    txtName.setText("");
    panelUpdate.add(txtName);

    // txtField harga
    JLabel hargaLabel = new JLabel("Harga Barang");
    hargaLabel.setBounds(nameLabel.getX(), nameLabel.getY() + 50, 150, 50);
    hargaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    panelUpdate.add(hargaLabel);
    JTextField textHarga = new JTextField();
    textHarga.setMargin(new Insets(0, 10, 0, 10));
    textHarga.setBounds(hargaLabel.getX() + 120, hargaLabel.getY(), 300, 40);
    textHarga.setText("");
    panelUpdate.add(textHarga);

    // txtArea deskripsi
    JLabel deskripsiLabel = new JLabel("Deskripsi Barang");
    deskripsiLabel.setBounds(hargaLabel.getX(), hargaLabel.getY() + 50, 150, 50);
    deskripsiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    panelUpdate.add(deskripsiLabel);
    JTextArea deskripsiTxt = new JTextArea();
    deskripsiTxt.setMargin(new Insets(10, 10, 10, 10));
    deskripsiTxt.setLineWrap(true);
    deskripsiTxt.setWrapStyleWord(true);
    deskripsiTxt.setText("");
    JScrollPane scrollDeskripsi = new JScrollPane(deskripsiTxt);
    scrollDeskripsi.setBounds(deskripsiLabel.getX() + 120, deskripsiLabel.getY(), 300, 100);
    panelUpdate.add(scrollDeskripsi);

    // table barang
    String column[] = { "ID BARANG", "NAMA BARANG", "DESKRIPSI BARANG", "HARGA BARANG", "AKSI" };
    JTable tableData = new JTable(Config.getAllData(true, "Pilih"), column);
    JScrollPane scrollTable = new JScrollPane(tableData);
    scrollTable.setBounds(nameLabel.getX() + 430, nameLabel.getY(), 520, 200);
    panelUpdate.add(scrollTable);

    JButton updateButton = new JButton("UPDATE DATA");
    updateButton.setBounds(deskripsiLabel.getX(), deskripsiLabel.getY() + 150, 140, 50);
    panelUpdate.add(updateButton);

    JButton btnTambahData = new JButton("TAMBAH DATA");
    btnTambahData.setBounds(deskripsiLabel.getX() + updateButton.getWidth() + 10, deskripsiLabel.getY() + 150, 140, 50);
    panelUpdate.add(btnTambahData);

    tableData.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        idBarang = Integer.parseInt(tableData.getValueAt(tableData.getSelectedRow(), 0).toString());
        txtName.setText(tableData.getValueAt(tableData.getSelectedRow(), 1).toString());
        deskripsiTxt.setText(tableData.getValueAt(tableData.getSelectedRow(), 2).toString());
        textHarga.setText(tableData.getValueAt(tableData.getSelectedRow(), 3).toString());
      }
    });

    updateButton.addActionListener(e -> {
      String value[] = { txtName.getText(), deskripsiTxt.getText(), textHarga.getText() };
      if ((txtName.getText().equals("")) && (deskripsiTxt.getText().equals(""))
          && (textHarga.getText().equals(""))) {
        JOptionPane.showMessageDialog(null, "Mohon untuk memilih data yang ingin Anda update pada tabel di samping");
      } else {
        if (Config.updateData(value, 4, idBarang)) {
          JOptionPane.showMessageDialog(null, "Berhasil Melakukan Update Data");
        }
      }

      mainPanel.removeAll();
      mainPanel.repaint();
      mainPanel.revalidate();

      panelUpdateData(title, mainPanel);
    });

    panelUpdate.setLayout(null);
    mainPanel.add(panelUpdate);
    mainPanel.repaint();
    mainPanel.revalidate();
  }

  public static void deletePanel(String title, JPanel mainPanel) {
    JPanel deletePanel = new JPanel();
    deletePanel.setBounds(0, 0, 1024, 624);
    deletePanel.setBackground(new Color(196, 213, 229));

    JLabel deletePanelLabel = new JLabel(title);
    deletePanelLabel.setBounds(20, 20, 300, 50);
    deletePanelLabel.setFont(new Font("Arial", Font.PLAIN, 40));
    deletePanel.add(deletePanelLabel);

    String column[] = { "ID BARANG", "NAMA BARANG", "DESKRIPSI BARANG", "HARGA BARANG", "AKSI" };
    JTable tableDelete = new JTable(Config.getAllData(true, "Delete?"), column);
    JScrollPane scrollTable = new JScrollPane(tableDelete);
    scrollTable.setBounds(deletePanelLabel.getX(), deletePanelLabel.getY() + 50, 600, 300);
    deletePanel.add(scrollTable);

    JPanel deletedItem = new JPanel();
    deletedItem.setBounds(scrollTable.getX() + 610, scrollTable.getY(), 350, 350);
    deletedItem.setLayout(null);
    deletePanel.add(deletedItem);

    JLabel deletedItemLabel = new JLabel("ITEM");
    deletedItemLabel.setBounds(150, 20, 100, 40);
    deletedItemLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    deletedItem.add(deletedItemLabel);

    JLabel labelName = new JLabel("Nama Barang");
    labelName.setBounds(10, deletedItemLabel.getY() + 10, 200, 50);
    deletedItem.add(labelName);

    JTextField nameField = new JTextField();
    nameField.setBounds(10, labelName.getY() + 35, 330, 30);
    nameField.setBackground(new Color(255, 255, 255));
    deletedItem.add(nameField);

    JLabel labelDeskripsi = new JLabel("Deskripsi Barang");
    labelDeskripsi.setBounds(10, nameField.getY() + 30, 200, 50);
    deletedItem.add(labelDeskripsi);

    JTextArea deskripsiArea = new JTextArea();
    deskripsiArea.setBounds(10, labelDeskripsi.getY() + 35, 330, 60);
    deskripsiArea.setEditable(false);
    deletedItem.add(deskripsiArea);

    JLabel labelHarga = new JLabel("Harga Barang");
    labelHarga.setBounds(10, deskripsiArea.getY() + 60, 200, 50);
    deletedItem.add(labelHarga);

    JTextField hargaField = new JTextField();
    hargaField.setBounds(10, labelHarga.getY() + 35, 330, 30);
    hargaField.setBackground(new Color(255, 255, 255));
    deletedItem.add(hargaField);

    JButton btnDelete = new JButton("DELETE ITEM");
    btnDelete.setBounds(10, hargaField.getY() + 50, 330, 50);
    btnDelete.setBackground(new Color(217, 83, 79));
    deletedItem.add(btnDelete);

    btnDelete.addActionListener(e -> {
      if ((!nameField.getText().equals("")) && (!deskripsiArea.getText().equals(""))
          && (!hargaField.getText().equals(""))) {
        int result = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus barang?", "DELETE?",
            JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_NO_OPTION) {
          Config.removeDataTemporary(idBarang);
          deletePanel(title, mainPanel);
        }
      }
    });

    tableDelete.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent Event) {
        idBarang = Integer.parseInt(tableDelete.getValueAt(tableDelete.getSelectedRow(), 0).toString());
        nameField.setText(tableDelete.getValueAt(tableDelete.getSelectedRow(), 1).toString());
        nameField.setEditable(false);
        deskripsiArea.setText(tableDelete.getValueAt(tableDelete.getSelectedRow(), 2).toString());
        hargaField.setText(tableDelete.getValueAt(tableDelete.getSelectedRow(), 3).toString());
        hargaField.setEditable(false);
        deletePanel(title, mainPanel);
      }
    });

    deletePanel.setLayout(null);
    mainPanel.add(deletePanel);
    mainPanel.repaint();
    mainPanel.revalidate();
  }
}
