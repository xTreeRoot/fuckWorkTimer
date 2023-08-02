package treeroot.fuck;

import java.io.*;
import java.net.URL;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author xugenyin
 */
public class IoUtils {
    /**
     * 读取下班时间
     *
     * @return 下班时间
     */
    public LocalTime readClosingTimeFromDisk() {
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("closing_time.txt");
            if (resourceAsStream==null){
                new File(Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath(), "closing_time.txt");
            }
            assert resourceAsStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            String line = reader.readLine();
            if (line != null) {
                String[] timeParts = line.split(":");
                int hour = Integer.parseInt(timeParts[0]);
                int minute = Integer.parseInt(timeParts[1]);
                return LocalTime.of(hour, minute);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入下班时间
     *
     * @param hour   小时
     * @param minute 分钟
     */
    public void saveClosingTimeToDisk(int hour, int minute) {
        URL resource = getClass().getClassLoader().getResource("closing_time.txt");
        assert resource != null;
        String path = resource.getPath();
        System.out.println(path);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(hour + ":" + minute);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
