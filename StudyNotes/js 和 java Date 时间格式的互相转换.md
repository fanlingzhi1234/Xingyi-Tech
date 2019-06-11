### js 和 java Date 时间格式的互相转换



java.util.date类：[http://www.runoob.com/java/java-date-time.html](http://www.runoob.com/java/java-date-time.html)



同样是Date格式，在Java.util.Date里，是一串包含了星期几，几月几号，时间，年份的字符串，像这样 `Mon May 04 09:51:52 CDT 2013`

但是在js前端的type = "Date"类型时，返回的数据是单纯的日期，像这样 `2019-05-23`, 

两者不能直接转换，会报错.



具体转换方式：https://blog.csdn.net/wangshu696/article/details/44155605

将 java后台返回date，转化为日期格式

    function timeStamp2String(time){
                var datetime = new Date();
                datetime.setTime(time);
                var year = datetime.getFullYear();
                var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
                var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
                var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
                var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
                var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
                return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
            }
            
           调用
    timeStamp2String(subobj.createTime)
    timeStamp3String(new Date().getTime() 
