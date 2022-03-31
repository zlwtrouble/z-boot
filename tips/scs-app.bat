
@echo "开始编译============================================="

call mvn clean install -Dmaven.test.skip=true
@echo "编译完成============================================="

pause