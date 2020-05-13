package getImg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.HtmlUtil;
import util.IntegerUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestThread1 {

    /**
     *
     */
    private static String baseUrl = "http://comic.kkkkdm.com/";
    private static String baseImgUrl = "http://v2.kukudm.com/";


    public static void main(String args[]) throws UnsupportedEncodingException {
//        List<String> idList = HtmlUtil.getIdList(id);
        Document document = HtmlUtil.getDocment("http://comic.kkkkdm.com/comiclist/2549/index.htm", "gbk");

        //这部分就是参考截图中红框框起来的部分 获取dd
        Elements dlEleList = document.select("dl[id='comiclistn'] dd");
//        <a href="/comiclist/2549/66365/1.htm" target="_blank">佐伯同学睡着了 1话</a> a[target='_blank']
//        id="comiclistn"
        //下载该页面
        for (Element postItem : dlEleList) {
            //像jquery选择器一样，获取元素
            //获取dd下面的a标签。
            Elements aEleList = postItem.select("a");
            if (aEleList.size() < 1) {
                continue;
            }
            //获取第一个a标签
            Element temp = aEleList.get(0);
            //获得链接与text
            String ahref = temp.attr("href");
            String title = temp.text();

            String url = baseUrl + ahref;
            String htmlStr = HtmlUtil.getHtmlStr(url, "gbk");
            int pageNum = getPageNum(htmlStr);
            //遍历这话漫画下所有的图片
            for (int i = 0; i < pageNum; i++) {
                int page = i+1;
                String tempHref = ahref.replace("1.htm",page+".htm");
                String tempurl = baseUrl + tempHref;
                String temphtmlStr = HtmlUtil.getHtmlStr(tempurl, "gbk");
                //下载图片
                downliadPage2(temphtmlStr,title,pageNum,page);
            }
            System.out.println();
        }
    }

    /**
     * 功能描述: 获取漫画单页
     *
     * @param: [context]
     * @return: java.lang.String
     * @date: 2018/7/12 0012 11:18
     */
    public static void downliadPage2(String str, String alldir, int pageNum,int i) throws UnsupportedEncodingException {
        Document document = Jsoup.parse(str);
        Elements scriptEleList = document.select("script[language='javascript']");
        for (Element postItem : scriptEleList) {
            //像jquery选择器一样，获取元素
            //获取dd下面的a标签。
            String title = postItem.html();
            if (title.indexOf("document.write(") < 0) {
                continue;
            }
            title = ClearBracket(title);
            Document imgDoc = Jsoup.parse(title);
            Elements imgEleList = imgDoc.select("a IMG");
            if (imgEleList.size() < 1) {
                continue;
            }
            //获取第一个图片标签
            Element temp = imgEleList.get(0);
            String imgPathUrl = temp.attr("src");
            imgPathUrl = ClearQuote(imgPathUrl);
            imgPathUrl = URLEncoder.encode(imgPathUrl, "UTF-8");
            String imgUrl = baseImgUrl + imgPathUrl;
            System.out.println(imgUrl);
            //去空格
            String regEx = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
            alldir = alldir.replaceAll(regEx, "");
            //中文改
//            imgUrl = new String(imgUrl.toString().getBytes("UTF-8"));
            RunnableDemo1 R1 = new RunnableDemo1("Thread-" + alldir, "",
                    "页数" + pageNum + alldir, i, "",imgUrl);
            R1.start();

        }
    }

    /**
     * 功能描述: 获取漫画页数
     *
     * @param: [context]
     * @return: java.lang.String
     * @date: 2018/7/12 0012 11:18
     */
    public static Integer getPageNum(String str) {
        Document document = Jsoup.parse(str);
        Elements tdEleList = document.select("td[valign='top']");
        if (tdEleList.size() < 0) {
            return 0;
        }
        Element tempItem = tdEleList.get(0);
        String text = tempItem.text();
        String pageNumStr = getPageNumByStr(text).trim();
        return IntegerUtil.stringToInt(pageNumStr);
    }


    /**
     * 功能描述: 去掉document.write(前后
     *
     * @param: [context]
     * @return: java.lang.String
     * @date: 2018/7/12 0012 11:18
     */
    private static String ClearBracket(String context) {
        // 修改原来的逻辑，防止右括号出现在左括号前面的位置
        context = context.replace("document.write(\"", "");
        context = context.replace("\");", "");
        return context;
    }

    /**
     * 功能描述: 去掉加号前面且去掉双引号"
     *
     * @param: [context]
     * @return: java.lang.String
     * @date: 2018/7/12 0012 11:18
     */
    private static String ClearQuote(String context) {
        // 修改原来的逻辑，防止右括号出现在左括号前面的位置
        context = context.replace("\"", "");
//        int add = context.lastIndexOf("+");
        context = context.substring(context.lastIndexOf("+") + 1, context.length());
        return context;
    }

    /**
     * 功能描述: 提取共XX页
     *
     * @param: [context]
     * @return: java.lang.String
     * @date: 2018/7/12 0012 11:18
     */
    private static String getPageNumByStr(String context) {
        // 修改原来的逻辑，防止右括号出现在左括号前面的位置
        context = context.replace("\"", "");
//        int add = context.lastIndexOf("+");
        context = context.substring(context.lastIndexOf("共") + 1, context.length());
        context = context.substring(0, context.indexOf("页"));
        return context;
    }

}