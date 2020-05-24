# 疫情期间自动打卡小工具
疫情期间学校要求每天打卡，读取txt文件获取cookie，读取json文件获取post所需信息，加上定时运行，可以实现自动打卡。

运行所需读取的文件都放在`resources`文件夹中,可以通过浏览器抓包获取相关个人信息。

在windows中运行前需要：
- 把文件名中的`_demo`去掉，改为`cookie.txt`和`src.json`。
- 修改运行文件`run.bat`，将路径改为自己电脑上的路径。

定时的部分通过windows自动运行jar包的方式实现，详情参见https://blog.csdn.net/projectNo/article/details/81773744