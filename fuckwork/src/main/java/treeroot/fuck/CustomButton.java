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
        JButton timerButton = new JButton("重设定时");
        alwaysOnTopButton.setFocusable(false);
        alwaysOnTopButton.addActionListener(this::toggleAlwaysOnTop);
        timerButton.addActionListener(this::createUiComponents);
        // 创建工具栏并添加按钮和间隔
        JToolBar toolBar = new JToolBar();
        toolBar.add(alwaysOnTopButton);
        // 添加水平间隔
        toolBar.add(Box.createRigidArea(new Dimension(10, 0)));
        toolBar.add(timerButton);
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.WHITE);
        // 将工具栏放置在框架的北部（上方）
        add(toolBar, BorderLayout.NORTH);
        pack();
        setVisible(true);
    }
    private void toggleAlwaysOnTop(ActionEvent e) {
        setAlwaysOnTop(!isAlwaysOnTop());
    }

    private void createUiComponents(ActionEvent e) {
        this.dispose();
        new FuckInput().startFuck(new JFrame());
    }
}

