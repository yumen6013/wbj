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

    RunnableDemo1(String name, String fileSrc, String title, int pageNum,String alldir) {
        this.threadName = name;
        this.title = title;
        this.pageNum = pageNum;
        this.fileSrc = fileSrc;
        this.alldir = alldir;
        System.out.println("Creating " + threadName + title + pageNum);
    }

    public void run() {

        String titleImg = IntegerUtil.intStr(1);
        String filename = IntegerUtil.intStr(pageNum);
        //输网址
        String url = "https://123.com";
        if(pageNum == 1){
            try {
                System.out.println(url + fileSrc + "/" +
                        titleImg + ".jpg");
                HtmlUtil.download(
                        url + fileSrc + "/" +
                                titleImg + ".jpg",
                        "D:\\upload\\"+alldir, fileSrc + title + ".jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            HtmlUtil.download(
                    url + fileSrc + "/" + filename + ".jpg",
                    "D:\\upload\\"  + alldir + fileSrc + title, filename + ".jpg");
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