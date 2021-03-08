#!/bin/sh
# function:自动监控tomcat进程，宕机就执行重启
# */2 * * * * sh /usr/local/tomcat-report/report.sh
# DEFINE

tomcat_home=/web/apache-tomcat-8.5.20
# 获取tomcat PPID
TomcatID=$(ps -ef |grep tomcat-report |grep -w 'tomcat'|grep -v 'grep'|awk '{print $2}')

# tomcat_startup
StartTomcat=/usr/local/tomcat-report/bin/startup.sh
ShutdownTomcat=/usr/local/tomcat-report/bin/shutdown.sh
TomcatLog=/usr/local/tomcat-report/logs
TomcatWork=/usr/local/tomcat-report/work
#TomcatCache=/web/apache-tomcat-8.5.20/work

# 定义要监控的页面地址
WebUrl=http://127.0.0.1/WebReport/ReportServer

# 日志输出
GetPageInfo=/dev/null
TomcatMonitorLog=/usr/local/tomcat-report/TomcatMonitor.log




    echo "[info]开始监控tomcat...[$(date +'%F %H:%M:%S')]"
    if [ "$TomcatID" != "" ];then
    echo "[info]tomcat进程ID为:$TomcatID.]"
    # 获取返回状态码
    TomcatServiceCode=$(curl -s -o $GetPageInfo -m 10 --connect-timeout 10 $WebUrl -w %{http_code})
    if [ $TomcatServiceCode -eq 200 ];then
        echo "[info]返回码为$TomcatServiceCode,tomcat启动成功,页面正常."
    else
        echo "[error]访问出错，状态码为$TomcatServiceCode,错误日志已输出到$GetPageInfo"

        $ShutdownTomcat

        rm $TomcatLog/* -rf
        rm $TomcatWork/* -rf
        sleep 10

        #rm -rf $TomcatCache # 清理tomcat缓存
        $StartTomcat
    fi
    else
    echo "[error]进程不存在!tomcat自动重启..."
    echo "[info]$StartTomcat,请稍候......"
    #rm -rf $TomcatCache
    $StartTomcat
    fi
    echo "------------------------------"

