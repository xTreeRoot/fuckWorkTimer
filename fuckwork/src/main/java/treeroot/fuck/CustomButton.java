package treeroot.fuck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 * @author xugenyin
 */
public class CustomButton extends JFrame {
    public CustomButton() {
        // 创建自定义按钮
        JButton alwaysOnTopButton = new JButton("置顶//取消");
        JButton timerButton = new JButton("重新定时");
        alwaysOnTopButton.setFocusable(false);
        alwaysOnTopButton.addActionListener(this::toggleAlwaysOnTop);
        timerButton.addActionListener(this::createUiComponents);
        // 创建自定义标题栏面板
        JPanel titleBarPanel = new JPanel();
        titleBarPanel.setLayout(new BorderLayout());

        titleBarPanel.add(alwaysOnTopButton, BorderLayout.EAST);
        titleBarPanel.add(timerButton, BorderLayout.WEST);

        JPanel decorationsLayer = new JPanel(new BorderLayout());
        decorationsLayer.add(titleBarPanel, BorderLayout.NORTH);

        Container contentPane = getContentPane();
        contentPane.add(decorationsLayer, BorderLayout.NORTH);
    }
    private void toggleAlwaysOnTop(ActionEvent e) {
        setAlwaysOnTop(!isAlwaysOnTop());
    }

    private void createUiComponents(ActionEvent e) {
        this.dispose();
        new FuckInput().startFuck(new JFrame());
    }
}

