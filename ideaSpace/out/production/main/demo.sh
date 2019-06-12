
#简单的启动脚本
proname="order-manage-service.jar"
loghome="/data/ordermanage/logs/"
logname="console.log"
mem_maxsize="2048M"
mem_minsize="2048M"
commond="java -jar -server -Xmx${mem_maxsize} -Xms${mem_minsize} $proname >> $loghome$logname 2>&1 &"
if [ -f "${proname}"]; then
    nohup java -jar -server -Xmx${mem_maxsize} -Xms${mem_minsize} $proname >> $loghome$logname 2>&1 $
    echo ${commond}
    echo "$proname successd"
else
    echo "start faild,$proname isn't exit !"
fi










#jenkins用启动脚本
APPNAME=web-api-own
LOG_NAME=mj-$APPNAME
JAR_PATH=/data/springboot/mj-parent/$APPNAME
JAR_NAME=${LOG_NAME}.jar
BAK_DIR=/data/backup
DOWNLOAD_DIR=/data/download
LOG_PATH=/data/logs/$APPNAME
XMS=512M
XMX=521M
if [ -f ${DOWNLOAD_DIR}/${JAR_NAME} ]; then
    echo "The code pull success!"
    ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print $2}' | xargs kill -9
    sleep 1
    echo "stop success!"
    num='ps -ef | grep ${JAR_NAME} | grep -v "grep" | wc -1'
    if [$num -ne 0]; then
        echo "process running...."
        ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print $2}' | xargs kill -9
        sleep 1
        echo "stop success!"
    fi
    if [ -f ${BAK_DIR}/${JAR_NAME}.bak]; then
        rm -f ${BAK_DIR}/${JAR_NAME}.bak
    fi
    mv ${JAR_PATH}/${JAR_NAME} ${BAK_DIR}/${JAR_NAME}.bak
    sleep 1
    echo "bakup success!"
    mv ${DOWNLOAD_DIR}/${JAR_NAME} ${JAR_PATH}
    sleep 1
    echo "begin start service"
    cd /data/springboot/mj-parent/;nohup java -jar -server -Xms${XMS} -Xmx${XMX} ${JAR_PATH}/${JAR_NAME} >> ${DOWNLOAD_DIR}/spring_boot.out 2>$1 $
else
    echo "The code pull failure!"
    echo quit
fi
echo "服务启动完成!"



