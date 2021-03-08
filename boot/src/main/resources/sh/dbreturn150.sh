# /bin/bash
#13.150预发环境sql恢复
#DB服务器IP
DB_HOST=127.0.0.1
#数据库
db_admin="admin"
db_basics="basics"
db_bdm="bdm"
db_contract="contract"
db_credit="credit"
db_mall="mall"
db_oms="oms"
db_sap="sap"
db_sku="sku"
db_wms="wms"
db_job="xxl-job"
db_report="report"
db_finance="finance"
#database user
DB_USER="dumper"
#database password
DB_PASS="hsDmper!@#"
#日期
DB_DATE=`date +%Y-%m-%d`

#备份目录
back_dir=/app/db



#备份
mysql -uroot -p123456 admin<admin.sql
mysql -uroot -p123456 basics<basics.sql
mysql -uroot -p123456 bdm<bdm.sql
mysql -uroot -p123456 contract<contract.sql
mysql -uroot -p123456 finance<finance.sql
mysql -uroot -p123456 mall<mall.sql
mysql -uroot -p123456 oms<oms.sql
mysql -uroot -p123456 report<report.sql
mysql -uroot -p123456 sap<sap.sql
mysql -uroot -p123456 sku<sku.sql
mysql -uroot -p123456 wms<wms.sql


