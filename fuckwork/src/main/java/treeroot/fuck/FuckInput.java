package treeroot.fuck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author xugenyin
 */
public class FuckInput {
    void showInput(JFrame inputFrame) {
        if (new IoUtils().readClosingTimeFromDisk() != null) {
            new FuckTimer();
        } else {
          this.startFuck(inputFrame);
        }

    }
    public void startFuck(JFrame inputFrame){
        inputFrame.setTitle("输入下班时间");
        inputFrame.setSize(300, 200);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField hourField = new JTextField();
        hourField.setBounds(30, 20, 50, 30);
        hourField.setText(String.valueOf(10));
        inputFrame.add(hourField);
        JTextField minuteField = new JTextField();
        minuteField.setText(String.valueOf(10));
        minuteField.setBounds(90, 20, 50, 30);
        inputFrame.add(minuteField);
        JButton saveButton = new JButton("保存");
        saveButton.setBounds(150, 20, 80, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hour = Integer.parseInt(hourField.getText());
                int minute = Integer.parseInt(minuteField.getText());
                IoUtils ioUtils = new IoUtils();
                ioUtils.saveClosingTimeToDisk(hour, minute);
                inputFrame.dispose();
                new FuckTimer();
            }
        });
        inputFrame.add(saveButton);
        inputFrame.setLayout(null);
        // 获取屏幕的尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 设置窗口位置为屏幕中央
        int x = (screenSize.width - inputFrame.getWidth()) / 2;
        int y = (screenSize.height - inputFrame.getHeight()) / 2;
        inputFrame.setLocation(x, y);
        inputFrame.setVisible(true);
    }
}
