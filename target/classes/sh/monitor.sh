#!/bin/sh
## java env
export JAVA_HOME=/usr/local/jdk
export JRE_HOME=$JAVA_HOME/jre

## exec shell name
EXEC_SHELL_NAME=$1\.sh
## service name
SERVICE_NAME=$1
SERVICE_DIR=/app/scs
JAR_NAME=$SERVICE_NAME\.jar
PID=pid/$SERVICE_NAME\.pid
WORK_DIR=$SERVICE_DIR/app
debug_port=$3
label=master
profile=test

#function start

start(){

	#根据端口号查询对应的pid
	if [  -n  "$debug_port"  ];  then
		debug_pid_temp=$(netstat -nlp | grep :$debug_port | awk '{print $7}' | awk -F"/" '{ print $1 }')
			
		#杀掉对应的进程，如果pid不存在，则不执行
		if [  -n  "$debug_pid_temp"  ];  then
		    echo "#### debug pid is:$debug_pid_temp ."

		    kill  -9  $debug_pid_temp;

		    echo "kill #### debug pid :$debug_pid_temp ."

		fi
	fi
	eureka_url="http://localhost:9050/eureka/"
	cd $WORK_DIR
	if [   -n  "$debug_port"  ];  then
		nohup $JRE_HOME/bin/java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=$debug_port,suspend=n -Xms256m -Xmx256m  -jar  $JAR_NAME --spring.cloud.config.profile=test --spring.cloud.config.label=master --eureka.client.service-url.defaultZone=${eureka_url} >/dev/null 2>&1 &
		echo "#### debug port $debug_port"
		echo "$JRE_HOME/bin/java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=$debug_port,suspend=n -Xms256m -Xmx256m -jar   $JAR_NAME --spring.cloud.config.profile=$profile --spring.cloud.config.label=master --eureka.client.service-url.defaultZone=${eureka_url} >/dev/null 2>&1 &"

	else
		nohup $JRE_HOME/bin/java -Xms256m -Xmx512m -jar  $JAR_NAME --spring.cloud.config.profile=test --spring.cloud.config.label=master --eureka.client.service-url.defaultZone=${eureka_url} >/dev/null 2>&1 &
	fi
		
        echo $! > $SERVICE_DIR/$PID
        echo "#### start $SERVICE_NAME"
	
}

# function stop
stop(){
    cd $WORK_DIR
   if [ -f "$SERVICE_DIR/$PID" ]; then
                kill `cat $SERVICE_DIR/$PID`
                rm -rf $SERVICE_DIR/$PID
        fi
        echo "#### stop $SERVICE_NAME"
        TEMP_PID=`ps -ef | grep -w "$SERVICE_NAME" | grep "java" | awk '{print $2}'`
        if [ "$TEMP_PID" == "" ]; then
            echo "#### $SERVICE_NAME process not exists or stop success"
        else
            echo "#### $SERVICE_NAME process pid is:$TEMP_PID ."
            kill -9 $TEMP_PID
        fi
	
	
}

# function clean
clean(){
    cd $WORK_DIR
        if [ ! -d "lastDeploy" ]; then
           mkdir lastDeploy
        else
           rm lastDeploy/$SERVICE_NAME*
        fi
        if [ -f "$JAR_NAME" ]; then
           mv $JAR_NAME lastDeploy
        fi 
}

case "$2" in

    start)
   start
        ;;

    stop)
   stop
        ;;

    restart)
        stop
        sleep 20
        start
        echo "#### restart $SERVICE_NAME"
        ;;
   
    clean)
   stop
    sleep 2
   clean
    echo "#### clean $SERVICE_NAME"
   ;;

    *)
       ## restart
       stop
       sleep 2
       start
       ;;

esac
exit 0
