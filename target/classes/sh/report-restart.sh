#!/bin/bash
./etc/profile
tomcatPath="/app/tomcat-report"
binPath="$tomcatPath/bin"

 PATH=$JAVA_HOME/bin:$PATH
 CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
 export JAVA_HOME
 export PATH
 source /etc/profile

shellLog=/app/sh/shell.log

Monitor()  
 {  

echo "[info][$(date +'%F %H:%M:%S')]正在查询tomcat，路径：$tomcatPath"
pid=`ps -ef | grep tomcat | grep -w $tomcatPath | grep -v 'grep' | awk '{print $2}'`
if [ -n "$pid" ]; then
echo "[info][$(date +'%F %H:%M:%S')]tomcat进程存在，准备使用kill命令PID：$pid"
kill  $pid
sleep 2
pid=`ps -ef | grep tomcat | grep -w $tomcatPath | grep -v 'grep' | awk '{print $2}'`
if [ -n "$pid" ]; then
echo "[info]使用kill命令关闭失败，准备kill -9进程..."
kill -9 $pid
echo "[info]kill -9进程完毕！"
sleep 1
else
echo "[info]使用kill命令关闭成功！"
fi
else
echo "[info][$(date +'%F %H:%M:%S')]tomcat未启动！"
fi
echo "[info][$(date +'%F %H:%M:%S')]准备启动tomcat..."
$binPath"/startup.sh"
}
Monitor >>$shellLog
echo "[info][$(date +'%F %H:%M:%S')]执行完成..."
