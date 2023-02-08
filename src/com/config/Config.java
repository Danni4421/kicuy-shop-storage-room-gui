// class package
package com.config;

// import
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

public class Config {
  // konfigurasi
  private static String dbURL = "jdbc:mysql://localhost:3306/dbkicuyshop";
  private static String user = "root";
  private static String pass = "";

  // instansiasi object sql class
  private static Connection conn;
  private static Statement state;
  private static ResultSet result;

  // connection config
  private static void configConnection() {
    try {
      conn = DriverManager.getConnection(dbURL, user, pass);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // get all data
  public static String[][] getAllData() {
    configConnection();
    ArrayList<Integer> idBarang = new ArrayList<Integer>();
    ArrayList<String> namaBarang = new ArrayList<String>();
    ArrayList<String> deskripsiBarang = new ArrayList<String>();
    ArrayList<Integer> hargaBarang = new ArrayList<Integer>();

    try {
      state = conn.createStatement();
      String query = "SELECT * FROM tbl_barang WHERE statusBarang = 1";
      result = state.executeQuery(query);

      while (result.next()) {
        idBarang.add(result.getInt("idBarang"));
        namaBarang.add(result.getString("namaBarang"));
        deskripsiBarang.add(result.getString("deskripsiBarang"));
        hargaBarang.add(result.getInt("hargaBarang"));
      }

      state.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    String[][] resultBarang = new String[idBarang.size()][4];
    for (int i = 0; i < resultBarang.length; i++) {
      for (int j = 0; j < resultBarang[0].length; j++) {
        switch (j) {
          case 0:
            resultBarang[i][j] = idBarang.get(i).toString();
            break;
          case 1:
            resultBarang[i][j] = namaBarang.get(i);
            break;
          case 2:
            resultBarang[i][j] = deskripsiBarang.get(i);
            break;
          case 3:
            resultBarang[i][j] = hargaBarang.get(i).toString();
            break;
        }
      }
    }

    return resultBarang;
  }

  public static String getDetailData(int id) {
    configConnection();
    String data = "Data tidak ditemukan!!";
    try {
      state = conn.createStatement();
      String query = "SELECT * FROM tbl_barang WHERE idBarang = " + id;
      result = state.executeQuery(query);

      while (result.next()) {
        data += "\nID BARANG : " + (result.getInt("idBarang")) +
            "\nNAMA BARANG : " + (result.getString("namaBarang")) +
            "\nHARGA BARANG : " + (result.getInt("hargaBarang")) +
            "\nDESKRPSI BARANG : " + (result.getString("deskripsiBarang"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public static ResultSet getSearchedData(String keyword) {
    configConnection();
    try {
      state = conn.createStatement();
      String query = "SELECT * FROM tbl_barang WHERE idBarang LIKE '%" + keyword +
          "%' OR namaBarang LIKE '%" + keyword + "%'  OR hargaBarang LIKE '%" + keyword +
          "%' OR deskripsiBarang LIKE '%" + keyword + "%' AND statusBarang = 1";

      result = state.executeQuery(query);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  public static boolean updateData(String[] value, int choice, int id) {
    configConnection();
    boolean data = false;
    String query = "UPDATE tbl_barang ";
    if (value.length == 1) {
      switch (choice) {
        // ganti nama
        case 1:
          query += "SET (`namaBarang` = '" + value[0] + "') WHERE idBarang = " + id;
          break;
        // ganti harga
        case 2:
          query += "SET (`hargaBarang` = '" + Integer.parseInt(value[0]) +
              "') WHERE idBarang = " + id;
          break;
        // ganti deskripsi
        case 3:
          query += "SET (`deskripsiBarang` = '" + value[0] + "') WHERE idBarang = " + id;
          break;
      }
    } else if (value.length > 1) {
      query += "SET (`namaBarang` = '" + value[0] + "', `hargaBarang` = '" + value[1] +
          "', `deskripsiBarang` = '" + value[2] + "') WHERE idBarang = " + id;
    }

    try {
      state = conn.createStatement();
      if (!state.execute(query)) {
        System.out.println("Berhasil merubah data!!");
        data = true;
      } else {
        System.out.println("Gagal merubah data!!");
        data = false;
      }

      state.close();
      conn.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public static boolean removeDataTemporary(int id) {
    configConnection();
    boolean result = false;

    try {
      state = conn.createStatement();
      String query = "UPDATE tbl_barang SET `statusBarang` = 0 WHERE idBarang = " + id;
      if (!state.execute(query)) {
        System.out.println("Berhasil memindahkan ke dalam recycle bin!!");
        result = true;
      } else {
        System.out.println("Gagal menghapus!!");
        result = false;
      }

      state.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  public static boolean returnBackTemporaryData(int id) {
    configConnection();
    boolean result = false;

    try {
      state = conn.createStatement();
      String query = "UPDATE tbl_barang SET `statusBarang` = '1' WHERE idBarang = " + id;
      if (!state.execute(query)) {
        System.out.println("Berhasil memindahkan ke dalam gudang!!");
        result = true;
      } else {
        System.out.println("Gagal memindahkan!!");
        result = false;
      }

      state.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  public static boolean removePermanent(int id) {
    configConnection();
    boolean result = false;

    try {
      state = conn.createStatement();
      String query = "DELETE FROM tbl_barang WHERE idBarang = " + id;
      if (!state.execute(query)) {
        result = true;
      } else {
        result = false;
      }

      state.close();
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
