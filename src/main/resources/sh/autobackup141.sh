#!/bin/bash

echo "web copy"
sshpass -p hundsun@123 scp -r  root@192.168.13.151:/app/scs/web/* /app/scs/web
echo "web end"