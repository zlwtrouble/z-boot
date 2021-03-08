#!/bin/bash
date=$(date "+%Y%m%d-%H%M%S")

#备份
cp -r /app/scs /app/bak/scs${date}_${1}





#保留文件数

ReservedNum=3

#当前脚步所在目录

RootDir=/app/bak

 cd ${RootDir}

#显示文件数， *.*可以改为指定文件类型

FileNum=$(ls -l |grep "^d"|wc -l)

echo "文件数量${FileNum}"
while(( $FileNum > $ReservedNum ))

do

    #取最旧的文件，*.*可以改为指定文件类型

    OldFile=$(ls                 -rt  | head -1)

    echo "Delete File:"$RootDir'/'$OldFile

    rm -rf $RootDir'/'$OldFile

    let "FileNum--"

done

scp -r root@192.168.13.149:/app/scs/*web*.jar /app/scs 

cd /app/sh
sh model.sh restart
