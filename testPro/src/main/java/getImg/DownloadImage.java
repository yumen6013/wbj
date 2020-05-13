package getImg;

import util.HtmlUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage {

    /**
     * @param args
     * @throws Exception
     */
    private static String baseUrl = "http://comic.kkkkdm.com/";
    private static String baseImgUrl = "http://v2.kukudm.com/";

    public static void main(String[] args) throws Exception {
        int length = 300;
        String fileSrcs[] = {
                "185213",
        };
        String fileSrc = "";
        for (int j = 0; j < length; j++) {
            fileSrc = fileSrcs[j];
            String url = "https://123.com" + fileSrc + "/";
            String title = HtmlUtil.getTitle(url);
            title = title.substring(0,title.length()-14);
            String coverUrl = "https://123.com" +
                    fileSrc + ".jpg";
//            download(
//                    coverUrl,
//                    "E:\\upload\\" , title + ".jpg");

            for (int i = 1; i < length; i++) {
                String filename = intStr(i);

                System.out.println(
                       url+ fileSrc + "/" + filename + ".jpg"
//						"https://123.com" + fileSrc + "/" + filename + ".jpg"
                );
                System.out.println("E:\\upload\\" + fileSrc);
                System.out.println(filename);
                try {
                    download(
                           url+ fileSrc + "/" + filename + ".jpg",
                            "E:\\upload\\" + title, filename + ".jpg");
                } catch (Exception e) {
                    break;
                }
            }
        }
    }

    public static String download(String urlPath, String savePath, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlPath);
        // 打开连接
        URLConnection con = url.openConnection();
        // 设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        int randomNo = (int) (Math.random() * 1000000);
        // String
        // filename=urlPath.substring(urlPath.lastIndexOf("/")+1,urlPath.length());//获取服务器上图片的名称
        // filename=new
        // java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new
        // Date())+randomNo+filename;//时间+随机数防止重复
        OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
        String virtualPath = "/upload/SDSPage/" + filename;// 存入数据库的虚拟路径
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
        return virtualPath;
    }

    public static String intStr(int n) {
        String nstr = n + "";
        String str = nstr;
        for (int i = 5; i > nstr.length(); i--) {
            str = "0" + str;
        }
        return str;
    }

}