package getImg;

import util.HtmlUtil;
import util.IntegerUtil;

public class RunnableDemo1 implements Runnable {
    private Thread t;
    private String threadName;
    private int pageNum;
    private String title;
    private String fileSrc;
    private String alldir;
    private String imgUrl;

    RunnableDemo1(String name, String fileSrc, String title, int pageNum, String alldir, String imgUrl) {
        this.threadName = name;
        this.title = title;
        this.pageNum = pageNum;
        this.fileSrc = fileSrc;
        this.alldir = alldir;
        this.imgUrl = imgUrl;
        System.out.println("Creating " + threadName + title + pageNum);
    }

    public void run() {

        String titleImg = IntegerUtil.intStr(1);
        String filename = IntegerUtil.intStr(pageNum);
        //输网址
        String url = imgUrl;
        String filePath = "D:\\upload\\";
        if (pageNum == 1) {
            try {
                System.out.println(imgUrl);
                System.out.println(filePath);
                System.out.println(filePath + alldir);
                HtmlUtil.download(
                        imgUrl,
                        filePath, fileSrc + title + ".jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            HtmlUtil.download(
                    imgUrl,
                    filePath + alldir + fileSrc + title, filename + ".jpg");
        } catch (Exception e) {

        }
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}