package view;

import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import model.IShape;
import model.SnapShot;

/**
 * The type Graphical view.
 */
public class GraphicalView extends JFrame implements IView {
  private final DrawPanel drawPanel;
  private List<SnapShot> snapShots;
  private JPanel jPanelNavigation;
  private GroupLayout jPanelNavigationLayout;
  private SnapShot currentSnapShot;

  private JButton jButtonPrev;
  private JButton jButtonNext;
  private JButton jButtonDel;
  private JComboBox<String> jComboBoxSelectSnapShot;

  /**
   * Instantiates a new Graphical view.
   *
   * @param xMax the x max
   * @param yMax the y max
   */
  public GraphicalView(int xMax, int yMax) {
    setTitle("Photo Album");
    setSize(xMax, yMax);
    this.drawPanel = new DrawPanel(new BorderLayout());
    this.add(drawPanel);
  }

  private void initComponents() {
    setApplicationLookAndFeel();
    this.jPanelNavigation = new JPanel();
    this.jPanelNavigationLayout = new GroupLayout(jPanelNavigation);
    jPanelNavigation.setLayout(jPanelNavigationLayout);
    jButtonPrev = new JButton();
    jButtonPrev.setText("<< Prev");
    jButtonNext = new JButton();
    jButtonNext.setText("Next >>");
    jButtonDel = new JButton();
    jButtonDel.setText("X Close X");
    jButtonDel.setBackground(new Color(255, 0, 0));
    //ComboBox initialization and EventHandling
    jComboBoxSelectSnapShot = new JComboBox<>();
    snapShots.forEach(snap -> jComboBoxSelectSnapShot.addItem(snap.toString()));
    jComboBoxSelectSnapShot.addItemListener(evt -> {
      currentSnapShot = snapShots.get(jComboBoxSelectSnapShot.getSelectedIndex());
      drawPanel.revalidate();
      drawPanel.repaint();
    });
    jButtonPrev.addActionListener(evt -> {
      int currentIndex = jComboBoxSelectSnapShot.getSelectedIndex();
      currentIndex--;
      if (currentIndex < 0) {
        JOptionPane.showMessageDialog(this, "End of PhotoAlbum!");
      } else {
        jComboBoxSelectSnapShot.setSelectedIndex(currentIndex);
      }
    });
    jButtonNext.addActionListener(evt -> {
      int currentIndex = jComboBoxSelectSnapShot.getSelectedIndex();
      currentIndex++;
      if (currentIndex >= snapShots.size()) {
        JOptionPane.showMessageDialog(this, "End of PhotoAlbum!");
      } else {
        jComboBoxSelectSnapShot.setSelectedIndex(currentIndex);
      }
    });
    jButtonDel.addActionListener(evt -> {
      System.exit(0);
    });
    jPanelNavigationLayout.setHorizontalGroup(jPanelNavigationLayout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNavigationLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButtonPrev, GroupLayout.PREFERRED_SIZE,
                            80, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonNext, GroupLayout.PREFERRED_SIZE,
                            80, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jComboBoxSelectSnapShot, GroupLayout.PREFERRED_SIZE,
                            400, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                            100, Short.MAX_VALUE)
                    .addComponent(jButtonDel, GroupLayout.PREFERRED_SIZE,
                            100, GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24)));
    jPanelNavigationLayout.setVerticalGroup(
            jPanelNavigationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNavigationLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanelNavigationLayout
                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonPrev)
                                    .addComponent(jButtonNext)
                                    .addComponent(jComboBoxSelectSnapShot,
                                            GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonDel))
                            .addContainerGap(9, Short.MAX_VALUE))
    );
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING,
                            layout.createSequentialGroup().addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(drawPanel, GroupLayout.DEFAULT_SIZE,
                                                    494, Short.MAX_VALUE)
                                            .addComponent(jPanelNavigation,
                                                    GroupLayout.Alignment.LEADING,
                                                    GroupLayout.DEFAULT_SIZE,
                                                    GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE))
                                    .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanelNavigation, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(drawPanel,
                                    GroupLayout.DEFAULT_SIZE,
                                    303, Short.MAX_VALUE)
                            .addContainerGap())
    );
  }

  public void render(List<SnapShot> snapShots) {
    this.snapShots = snapShots;
    initComponents();
    this.currentSnapShot = this.snapShots.get(0);
    setVisible(Boolean.TRUE);
    setApplicationLookAndFeel();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
  }

  private void setApplicationLookAndFeel() {
    try {
      for (UIManager.LookAndFeelInfo info :
              UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        } else {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
             | UnsupportedLookAndFeelException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * The type Draw panel.
   */
  class DrawPanel extends JPanel {
    /**
     * Instantiates a new Draw panel.
     *
     * @param layout the layout
     */
    DrawPanel(LayoutManager layout) {
      super(layout);
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Map<String, IShape> shapes = currentSnapShot.getShapeDesc();
      this.setBackground(Color.WHITE);
      Graphics2D g2d = (Graphics2D) g;
      for (IShape shape : shapes.values()) {
        g2d = shape.shapeType().drawShape(shape, g2d);
      }
    }

  }

}
