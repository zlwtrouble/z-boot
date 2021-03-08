#!/bin/sh
# Program:
#     Auto cut nginx log script.
LOGS_PATH=/usr/local/nginx/logs #你的日志目录
TODAY=$(date -d 'yesterday' +%Y-%m-%d-%H)
#TODAY=history
# 移动日志并改名
#设置将生成的日志放到新的位置
mv ${LOGS_PATH}/error.log ${LOGS_PATH}/error_${TODAY}.log
mv ${LOGS_PATH}/access.log ${LOGS_PATH}/access_${TODAY}.log
# 向nginx主进程发送重新打开日志文件的信号
#你的nginx.pid所在位置 找不到的话可以看一下nginx的conf文件
kill -USR1 $(cat /usr/local/nginx/logs/nginx.pid)