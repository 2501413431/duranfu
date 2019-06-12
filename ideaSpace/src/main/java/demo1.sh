APPNAME=web-api-own
LOG_NAME=mj-${APPNAME}
JAR_PATH=/data/springboot/mj-parent/$APPNAME
JAR_NAME=${LOG_NAME}.jar
BAK_DIR=/data/backup
DOWNLOAD_DIR=/data/download
LOG_PATH=/data/logs/$APPNAME
XMS=512M
XMX=512M
if [ -f ${DOWNLOAD_DIR}/${JAR_NAME}]; then
    echo "The code pull success!"
    ps -ef | grep ${JAR_NAME} | grep -v grep | awk '${print $2}' | xargs kill -9
    slppe 1
    echo "stop success!"
    num = 'ps -ef | grep ${JAR_NAME} | grep -v "grep" | wc -l'
    if[num -ne 0]; then
        echo "process running..."
        ps -ef | grep ${JAR_NAME} | grep -v grep | awk '${print $2}' | xargs kill -9
        echo "stop success!"
    fi
    if [if ${BAK_DIR}/${JAR_NAME}.bak];then
        rm -f ${BAK_DIR}/${JAR_NAEM}.bak
    fi
    mv ${DOWNLOAD_DIR}/${JAR_NAME} ${JAR_PATH}
    sleep 1
    echo "begin start service!"
    cd /data/springboot/mj-parent/;nohup java -jar -server -Xms${XMS} -Xmx${XMX} ${JAR_PATH}/${JAR_NAME} >> ${LOG_PATH}/springboot.out 2>&1 &
    echo ""
else

fi
echo "服务器启动完成"