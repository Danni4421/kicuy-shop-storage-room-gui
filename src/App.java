import com.config.Config;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.Color;
import javax.swing.BorderFactory;

public class App {
    public static void main(String[] args) throws Exception {
        // code start from here
        JFrame frameWindow = new JFrame();

        // setSize(Dimension d), setSize(int width, int height) -> inherited from class
        // java.awt.Window
        frameWindow.setSize(1280, 720);

        // setTitle("Title") -> inherited from class java.awt.Frame
        frameWindow.setTitle("Kicuy Shop");

        // EXIT PROGRAM when close button clicked
        frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameWindow.setLayout(null);

        // Panel background
        JPanel bg = new JPanel();

        // inherited from class java.awt.Component
        bg.setSize(1280, 720);

        // setBackground(Dimension / width & height) -> inherited javax.swing.JComponent
        // throw argumen object Color
        bg.setBackground(new Color(255, 255, 255));

        // setLayout
        bg.setLayout(null);

        // add bg to frame
        frameWindow.add(bg);

        // Panel SideBar
        JPanel sideBar = new JPanel();
        sideBar.setSize(256, 720);
        sideBar.setBackground(new Color(59, 59, 59));
        sideBar.setLayout(null);
        bg.add(sideBar);

        // Panel Navbar
        JPanel navbar = new JPanel();
        navbar.setSize(1024, 96);
        navbar.setBackground(new Color(194,194,194));
        navbar.setLocation(256, 0);
        navbar.setLayout(null);
        bg.add(navbar);

        // setVisible to set visibility (true) -> visible, (false) -> hidden
        frameWindow.setVisible(true);
    }
}
