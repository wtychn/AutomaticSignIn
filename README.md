# 疫情期间自动打卡小工具
疫情期间学校要求每天打卡，读取txt文件获取cookie，读取json文件获取post所需信息，加上定时运行，可以实现自动打卡。

运行所需读取的文件都放在`resources`文件夹中,可以通过浏览器抓包获取相关个人信息。

定时的部分通过windows自动运行jar包的方式实现，详情参见https://blog.csdn.net/projectNo/article/details/81773744