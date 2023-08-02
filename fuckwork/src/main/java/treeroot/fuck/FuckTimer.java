package treeroot.fuck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.Duration;
import java.time.LocalTime;

/**
 * @author xugenyin
 */
public class FuckTimer {

    public FuckTimer() {
        CustomButton frame = new CustomButton();
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel countdownLabel = new JLabel();
        countdownLabel.setBackground(Color.WHITE);
        countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        countdownLabel.setFont(countdownLabel.getFont().deriveFont(25.0f));
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
                    countdownLabel.setText("蟹老板，我下班了~~");
                    ((Timer) e.getSource()).stop();
                    //弹出来
                    frame.setAlwaysOnTop(true);
                } else {
                    long remainingSeconds = remainingDuration.getSeconds();
                    String titleTime = String.format(
                            "%02d:%02d:%02d",
                            remainingSeconds / 3600,
                            (remainingSeconds % 3600) / 60,
                            remainingSeconds % 60);
                    String remainingTime = String.format(
                            "<html> <p align=\"center\"><span>--</span> %02d:%02d:%02d--</p></html/>",
                            remainingSeconds / 3600,
                            (remainingSeconds % 3600) / 60,
                            remainingSeconds % 60);
                    countdownLabel.setText(titleTime);
                    frame.setTitle("下班下班下班~~剩余" + titleTime);
                }
            }
        });
        timer.start();

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newFontSize = calculateNewFontSize(frame.getWidth());
                countdownLabel.setFont(countdownLabel.getFont().deriveFont((float) newFontSize));
                // 其他需要在尺寸变化时更新的操作
            }
        });
        // 获取屏幕的尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 设置 JFrame 位置为屏幕右下角
        int x = screenSize.width - frame.getWidth() - 10;
        int y = screenSize.height - frame.getHeight() - 45;
        frame.setLocation(x, y);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
    }

    /**
     * 计算新的字体大小
     *
     * @param frameWidth 窗口宽度
     * @return 新的字体大小
     */
    private int calculateNewFontSize(int frameWidth) {
        // 将字体大小设置为框架宽度的 1/15
        return frameWidth / 15;
    }

}
