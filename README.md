# yumen #
!!!首先FBI Warning：仅供学习使用。话说如果传播起来，这个网站到底是感谢我呢，还是要告我擅用他的网站做实验呢。

这是个正常的漫画网站的爬虫。
我不小心改成可以下载整部漫画了。

吐槽：因为每次都要访问网址，使用Jsoup解析获取图片下载地址，导致这玩意的下载速度贼慢。
有优化的空间，比如获取全部44话网页的地址后，对这44话做多线程。也就是分为44个线程处理这件事。
然后在这44个线程中每个再开多线程下载图片。这样就行了。因为解析慢的主要原因是网络不稳定(这个问题使用:注1:中解决)，
而不是网速不够快，也不是电脑性能不行。
这个思想后面还可以推导到对大数据的处理。这个已经不属于这个爬虫的范围了。

然后这个项目没有太多优化没有太多深入浅出的教学模式。这个做起来太麻烦了。有意见我也不听。上面我已经给出了优化方案。

1.1使用idea最新版
1.2使用java1.8
1.3使用最新版maven。 
2.POM install运行一下
3.运行
\testPro\src\main\java\getImg\TestThread1
这个就行了。

注1：HtmlUtil第287行 
//检测原文件是否存在 且 大小是否大于某个数值
做了一个简单的防止重复+修补为下载完成的图片的校验。可以根据实际需要修改


基本就是httpclient、Runnable、Jsoup啥的
主要需要自己分析如何取到自己想要的数据。

Pages.xml
aPage.xml
这两个就是网页源代码，配合浏览器的检查分析这些源代码，找出需要的标签。获取需要的数据即可。很简单。
暂时未没有碰到被限制访问的情况。

该程序多线程没有做限制，不过由于网速等问题，估计线程也不会很多，这个漫画也才40多话，也就1000-2000张的图片。所以不必担心线程被爆。




