#!/bin/bash

port=`netstat -na|grep "LISTEN"|grep "3306"|awk -F[:" "]+ '{print $5}'`
 echo "start1 !"
array=($(mysql -uquery -pa123456 -e "show slave status\G"|egrep "Running|Seconds_Behind_Master" | awk '{print $2}'))

 echo "start2 ! $port"


#if [ "$port" == "3306" ];then

    if [ "${array[0]}" == "Yes" ] && [ "${array[1]}" == "Yes" ] && [ "${array[2]} " == "0" ];then

        echo "MySQL slave status is ok !"

    else
      #  mail -s "WARN!Server: $port mysql is down." 390181291@qq.com
        echo "MySQL slave status is wrong !"
    fi
#fi



#
#MYSQLPORT=`netstat -na|grep "LISTEN"|grep "3306"|awk -F[:" "]+ '{print $5}'`
#MYSQLIP=`ifconfig eth0|grep "inet addr" | awk -F[:" "]+ '{print $4}'`
#STATUS=$(mysql -uroot -p123456 -S /var/lib/mysql/mysql.sock -e "show slave status\G" | grep -i "running")
#IO_env=`echo $STATUS | grep IO | awk  ' {print $2}'`
#SQL_env=`echo $STATUS | grep SQL | awk  '{print $2}'`
#DATA=`date +"%y-%m-%d %H:%M:%S"`
#
#function checkMysqlStatus(){
#	if [ "$MYSQLPORT" == "3306" ]
#	then
#		mysql -uroot -p123456 --connect_timeout=5 -e "show databases;" &>/dev/null 2>&1
#		if [ $? -ne 0 ]
#		then
#			echo "Server: $MYSQLIP mysql is down, please try to restart mysql by manual!" > /var/log/mysqlerr
#			mail -s "WARN! server: $MYSQLIP  mysql is down." admin@yourdomain.com < /var/log/mysqlerr
#		else
#			echo "mysql is running..."
#		fi
#	else
#		mail -s "WARN!Server: $MYSQLIP mysql is down." 390181291@qq.com
#	fi
#}
#
#checkMysqlStatus
#
#if [ "$IO_env" = "Yes" -a "$SQL_env" = "Yes" ]
#then
#  echo "MySQL Slave is running!"
#else
#  echo "####### $DATA #########">> /data/mysql/mysql_slave_status.log
#  echo "MySQL Slave is not running!" >>    /data/mysql/mysql_slave_status.log
#  echo "MySQL Slave is not running!" | mail -s "WARN! $MYSQLIP MySQL Slave is not running." admin@yourdomain.com
#fi
