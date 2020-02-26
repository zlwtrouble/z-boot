#!/bin/sh
# 137环境:自动监控tomcat进程，重启

tomcat_home=/web/apache-tomcat-8.5.20
# 获取tomcat PPID
TomcatID=$(ps -ef |grep tomcat-report |grep -w 'tomcat'|grep -v 'grep'|awk '{print $2}')

# tomcat_startup
StartTomcat=/app/tomcat-report/bin/startup.sh
ShutdownTomcat=/app/tomcat-report/bin/shutdown.sh
TomcatLog=/app/tomcat-report/logs
TomcatWork=/app/tomcat-report/work
#TomcatCache=/web/apache-tomcat-8.5.20/work

# 定义要监控的页面地址
WebUrl=http://127.0.0.1/WebReport/ReportServer

# 日志输出
GetPageInfo=/dev/null
TomcatMonitorLog=/app/sh/reportMonitor.log



Monitor()
{
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
}
Monitor>>$TomcatMonitorLog