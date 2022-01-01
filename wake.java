/*
This code moves your mouse automatically after a predefined time if there is inactivity and this prevents your system from going to sleep or from locking.
You can customize the settings.
*/
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class wake {
    private static String date(){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        return timeStamp;
    }
    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot hal = new Robot();
        boolean on = true;
        while(true){
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x1 = (int) b.getX();
            int y1 = (int) b.getY();
            Thread.sleep(1000*10*60);
            a = MouseInfo.getPointerInfo();
            b = a.getLocation();
            int x2 = (int) b.getX();
            int y2 = (int) b.getY();
            if(x1==x2 && y1==y2){
                System.out.println(date()+" :  Inactive for 10mins , waking sleep check");
                sleep slp = new sleep();
                slp.main(null);
            }
        }
    }
}
class sleep {
    private static String date(){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        return timeStamp;
    }
    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot hal = new Robot();
        boolean on = true;
        out:
        while (true) {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            if (on) {
                on = !on;
                hal.mouseMove(x + 1, y + 1);
            } else {
                on = !on;
                hal.mouseMove(x - 1, y - 1);
            }
            System.out.println(date()+" :  Mouse not moved so moving automatically");
            int time = 0;
            while (time++ < 60) {
                a = MouseInfo.getPointerInfo();
                b = a.getLocation();
                int x1 = (int) b.getX();
                int y1 = (int) b.getY();
                Thread.sleep(1000*5);
                a = MouseInfo.getPointerInfo();
                b = a.getLocation();
                int x2 = (int) b.getX();
                int y2 = (int) b.getY();
                if (x1 != x2 || y1 != y2) {
//                    try {
//                        Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(date()+" :  Mouse moved exiting sleep check");
                    break out;
                }
            }
        }
    }
}
