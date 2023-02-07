import com.config.Config;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// pallete
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class App {
        public static void main(String[] args) throws Exception {
                // code start from here
                JFrame frameWindow = new JFrame();
                frameWindow.setSize(1280, 720);
                frameWindow.setTitle("Kicuy Shop");
                frameWindow.setResizable(false);
                frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameWindow.setLayout(null);

                // Panel background
                JPanel bg = new JPanel();
                bg.setSize(1280, 720);
                bg.setBackground(new Color(255, 255, 255));
                bg.setLayout(null);
                frameWindow.add(bg);

                JPanel sideBar = new JPanel();
                sideBar.setSize(256, 720);
                sideBar.setBackground(new Color(59, 59, 59));
                sideBar.setLayout(null);
                bg.add(sideBar);

                // panel
                JPanel mainPanel = new JPanel();
                mainPanel.setBounds(256, 96, 1024, 624);
                mainPanel.setBackground(new Color(196, 213, 229));
                JLabel txtDashboard = new JLabel("Dashboard");
                txtDashboard.setFont(new Font("Arial", Font.PLAIN, 40));
                txtDashboard.setBounds(20, 20, 300, 50);
                mainPanel.add(txtDashboard);
                mainPanel.setLayout(null);
                bg.add(mainPanel);

                // Panel Navbar
                JPanel navbar = new JPanel();
                navbar.setSize(1024, 96);
                navbar.setBackground(new Color(194, 194, 194));
                navbar.setLocation(256, 0);
                navbar.setLayout(null);
                bg.add(navbar);

                // button get all data
                JButton btnGetAllData = new JButton("Query All Data");
                btnGetAllData.setBounds(10, 100, 230, 80);
                btnGetAllData.setBackground(new Color(120, 120, 120));
                sideBar.add(btnGetAllData);

                btnGetAllData.addActionListener(e -> {
                        mainPanel.removeAll();
                        mainPanel.repaint();
                        mainPanel.revalidate();

                        panelGetAllData("DATA BARANG", mainPanel);
                });

                // btn search data
                JButton btnSearchData = new JButton("Search Data");
                btnSearchData.setBounds(10, (int) btnGetAllData.getLocation().getY() +
                                btnGetAllData.getHeight() + 30, 230, 80);
                btnSearchData.setBackground(new Color(120, 120, 120));
                sideBar.add(btnSearchData);

                // btn update data
                JButton btnUpdateData = new JButton("Update Data");
                btnUpdateData.setBounds(10, (int) btnSearchData.getLocation().getY() +
                                btnSearchData.getHeight() + 30, 230, 80);
                btnUpdateData.setBackground(new Color(120, 120, 120));
                sideBar.add(btnUpdateData);

                // btn delete data
                JButton btnDeleteData = new JButton("Delete Data");
                btnDeleteData.setBounds(10, (int) btnUpdateData.getLocation().getY() +
                                btnUpdateData.getHeight() + 30, 230, 80);
                btnDeleteData.setBackground(new Color(120, 120, 120));
                sideBar.add(btnDeleteData);

                // btn logout
                JButton btnOut = new JButton("Keluar");
                btnOut.setBounds(10, (int) btnDeleteData.getLocation().getY() +
                                btnDeleteData.getHeight() + 30, 230, 80);
                btnOut.setBackground(new Color(120, 120, 120));
                btnOut.addActionListener(e -> {
                        System.exit(0);
                });
                sideBar.add(btnOut);

                // navbar
                JLabel logo = new JLabel("KICUY SHOP MANAGEMENT");
                logo.setBounds(20, 0, 400, 100);
                logo.setFont(new Font("Arial", Font.BOLD, 20));
                navbar.add(logo);

                // setVisible to set visibility (true) -> visible, (false) -> hidden
                frameWindow.setVisible(true);
        }

        private static void panelGetAllData(String title, JPanel mainPanel) {
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
