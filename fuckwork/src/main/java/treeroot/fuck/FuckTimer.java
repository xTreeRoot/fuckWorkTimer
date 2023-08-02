package treeroot.fuck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.LocalTime;

/**
 * @author xugenyin
 */
public class FuckTimer {
    private final JLabel countdownLabel;
    public FuckTimer() {
//        CustomButton frame = new CustomButton();
        JFrame frame = new JFrame();
        frame.setTitle("下班下班");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        countdownLabel = new JLabel();
        countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        countdownLabel.setFont(countdownLabel.getFont().deriveFont(32.0f));
        frame.add(countdownLabel);
        IoUtils ioUtils = new IoUtils();
        // 从磁盘中读取小时和分钟
        LocalTime localTime = ioUtils.readClosingTimeFromDisk();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalTime currentTime = LocalTime.now();
                Duration remainingDuration = Duration.between(currentTime, localTime);
                if (remainingDuration.isNegative()) {
                    countdownLabel.setText("已下班");
                    ((Timer) e.getSource()).stop();
                } else {
                    long remainingSeconds = remainingDuration.getSeconds();
                    String remainingTime = String.format("%02d:%02d:%02d", remainingSeconds / 3600,
                            (remainingSeconds % 3600) / 60,
                            remainingSeconds % 60);
                    countdownLabel.setText(remainingTime);
                }
            }
        });
        timer.start();
        // 获取屏幕的尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 设置 JFrame 位置为屏幕右下角
        int x = screenSize.width - frame.getWidth();
        int y = screenSize.height - frame.getHeight();
        frame.setLocation(x, y);
        frame.setVisible(true);
    }


}
