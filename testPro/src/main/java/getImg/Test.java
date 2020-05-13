package getImg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

    public static void main(String[] args) {
        String str = "<html>\n" +
                "    <head>\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\">\n" +
                "        <title>佐伯同学睡着了 1话</title>\n" +
                "        <link href=\"/style.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "        <script language=\"javascript\" src=\"/js2/js0.js\"></script>\n" +
                "        <script language=\"javascript\" src=\"/js2/js4.js\"></script>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <table width=\"760\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <iframe src=\"/top.htm\" width=\"760\" height=\"96\" marginwidth=\"0\" marginheight=\"0\" hspace=\"0\"\n" +
                "                                vspace=\"0\" frameborder=\"0\" scrolling=\"no\"></iframe>\n" +
                "                        <br>\n" +
                "                        <script src=\"/js/play-d1.js\"></script>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <table width=\"760\" border=\"1\" align=\"center\" cellspacing=\"1\" bgcolor=\"#FFFFFF\">\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td width=\"760\" valign=\"top\" align=\"center\">佐伯同学睡着了 1话 | 共26页 | 当前第1页 | 跳转至第\n" +
                "                        <input name=\"page1\" id=\"page1\" value=\"1\" type=\"text\" class=\"bottom\" size=\"3\" maxlength=\"3\">\n" +
                "                        页\n" +
                "                        <input type=\"button\" class=\"bottom\" value=\"确定\"\n" +
                "                               onclick=\"window.location.href='' + document.getElementById('page1').value + '.htm';\">\n" +
                "                        <br>\n" +
                "                        <script language=\"javascript\">\n" +
                "                            document.write(\"\n" +
                "                            <a href='/comiclist/2549/66365/2.htm'>\n" +
                "                                <IMG SRC='\"+m201304d+\"newkuku/2018/10/16/佐伯同学睡着了_第01话/00012KC.jpg'>\n" +
                "                            </a>\n" +
                "                            <span style='display:none'>\n" +
                "                                <img src='\"+m201304d+\"newkuku/2018/10/16/佐伯同学睡着了_第01话/00022GU.jpg'>\n" +
                "                            </span>\n" +
                "                            \");\n" +
                "                        </script>\n" +
                "                        <br>\n" +
                "                        <script src=\"/js/play-d2.js\"></script>\n" +
                "                        <img src=\"/images/t2.gif\" border=\"0\" width=\"52\" height=\"19\">&nbsp; 共26页 | <script\n" +
                "                                src=\"/ad/sc_soso.js\"></script> | 第1页 &nbsp;<a href=\"/comiclist/2549/66365/2.htm\">\n" +
                "                            <img src=\"/images/d.gif\" border=\"0\" width=\"52\" height=\"19\">\n" +
                "                        </a>\n" +
                "                        <script src=\"/js/play-d3.js\"></script>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <table width=\"760\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td colspan=\"0\" align=\"center\">\n" +
                "                        <script src=\"/js/play-d4.js\"></script>\n" +
                "                        <br>\n" +
                "                        <script src=\"/js/stat.js\"></script>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </body>\n" +
                "</html>";
        getPageNum(str);
    }

    public static void getPageNum(String str) {
        Document document = Jsoup.parse(str);
        Elements tdEleList = document.select("td[valign='top']");
        if (tdEleList.size() < 0) {
            return;
        }
        Element tempItem = tdEleList.get(0);
        String text = tempItem.text();
        String pageNumStr = getPageNumByStr(text).trim();
        System.out.println();
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
        context = context.substring(0,context.indexOf("页") );
        return context;
    }


}
