#!/bin/bash
date=$(date "+%Y%m%d-%H%M%S")
COPY_DIR="bak"

SRC_DIR="nginx.conf"

if [ ! -d "$COPY_DIR" ];then
       mkdir "$COPY_DIR"
fi

#备份
cp -r $SRC_DIR $COPY_DIR/${SRC_DIR}_${date}



