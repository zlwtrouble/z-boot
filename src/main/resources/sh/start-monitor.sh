 #! /bin/bash
  PORT=55555
  #环境名
  profile=dev
  #启动命令所在目录  
  HOME=$(cd `dirname $0`; pwd)

 jar_name=$3
 
 XmxSize=$5

 this_dir="$( cd "$( dirname "$0"  )" && pwd )"
 parent_dir=`dirname "${this_dir}"`
 log_dir="${parent_dir}/shlogs"
 log_file="${log_dir}/catalina.txt"
 jar_file="${parent_dir}/${jar_name}"
  #查询出监听了PORT端口TCP协议的程序  
 pid=`netstat -anp|grep $PORT|awk '{printf $7}'|cut -d/ -f1`

 PATH=$JAVA_HOME/bin:$PATH
 CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
 export JAVA_HOME
 export PATH
 source /etc/profile

 start(){
    eureka_url=""
    apollo_url=""
    label=${profile}
    #日志文件夹不存在，则创建
   if [ ! -d "${log_dir}" ]; then
        echo '创建目录..'
        mkdir "${log_dir}"
   fi


		eureka_url="http://192.168.13.132:9050/eureka/,http://192.168.13.136:9050/eureka/"
	

    #进入命令所在
    pwd

    echo "jar名字: ${jar_name}"
    echo "环境: ${profile}"

    cd /home/

    nohup java -Xmx1024m -jar monitor-1.0-SNAPSHOT.jar --spring.cloud.config.profile=${label}> /dev/null 2>&1 &
    #记录当前操作日志
    log
    echo "start at port:$PORT"

    return 0
 }

 stop(){
    if [ -z "$pid" ]; then
       echo "not find program on port:$PORT"  
       return 0
    fi
    #结束程序，使用讯号2，如果不行可以尝试讯号9强制结束  
    kill -9 $pid
    rm -rf $pid
    echo "kill program use signal 2,pid:$pid"  
 }
 status(){
    if [ -z "$pid" ]; then
       echo "not find program on port:$PORT"  
    else
       echo "program is running,pid:$pid"  
    fi
 }

LOGFILE='start-monitor.sh.log'
function log(){
   #检查是否存在日志文件，如果存在，则检查文件是否过大（20M）
   #过大时，切换文件，并将目前的日志序列号保存在_LOGSEQ中。
   if [ -f $LOGFILE ];then
        LogFileLen=`ls -l ${LOGFILE} | awk '{print $5}'`
        if [ $LogFileLen -gt 20971520 ]; then
            if [ -f ${_LOGSEQ} ] ; then
                _OrgSeq="`cat ${_LOGSEQ}`"
                if [ $_OrgSeq -gt 98 ];then
                    LogFileSeq=0
                else
                    LogFileSeq=`expr ${_OrgSeq} + 1`
                fi
            else
                LogFileSeq=0
            fi
            echo "${LogFileSeq}" > ${_LOGSEQ}
            mv $LOGFILE ${LOGFILE}.${LogFileSeq}
        fi
    fi
    _LogInfo=$1
    echo "日期："`date +20'%y-%m-%d %H:%M:%S'`""    用户：$USER" "打包jar：${jar_name}" " " 

${_LogInfo} " >> ${LOGFILE} 2>&1
}

 case $1 in
    start)
       start
    ;;
    stop)
       stop
    ;;
    restart)
       $0 stop
       sleep 2
       $0 start
     ;;
    status)
       status
    ;;
    *)
       echo "Usage: {start|stop|status}"  
    ;;
 esac

 exit 0


