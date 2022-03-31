#!/bin/sh

waitNum=60

echo "开始等待"
while(( $waitNum > 0  ))
do
    echo $waitNum
		sleep
    let "waitNum--"
done
source /etc/profile

cd /usr/local/filebeat

sh filebeat.sh

#nohup  sh /home/app/sh/tx.sh start &
#sleep 30

nohup  sh /home/app/sh/model.sh start &
echo $(date +%Y-%m-%d\ %H:%M:%S)