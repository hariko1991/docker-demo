# docker-demo
docker maven plugin 结合自动部署

一、docker安装及配置下载源
	sudo apt install docker.io
	#其他方式请参考下面链接https://docs.docker.com/install/linux/docker-ce/ubuntu/
#安装完成后，需要配置免sudo，主要是 service/systemctl docker start/stop/restart
	sudo groupadd docker (会提醒已经存在组 docker)
	sudo gpasswd -a ${USER} docker
	#然后重启docker
	sudo systemctl restart docker
	#然后执行下面更新组信息命令
	newgrp - docker
	#取消/添加docker自启动，占用系统启动时间及内存
	sudo systemctl disable docker # 取消
	sudo systemctl enable docker # 添加
#下载镜像加速
#/etc/docker/daemon.jso{"registry-mirrors":["https://registry.docker-cn.com"]}
docker pull registry.docker-cn.com/library/ubuntu:16.04
	
二、相关命令介绍
#搜索相关镜像 https://hub.docker.com/search
	docker search mysql
	docker pull mysql:8.0.18
	docker ps -a -q # -a显示所有容器 -q 只显示容器id
	docker rmi tomcat:lastest #删除镜像
	docker stop/kill/start/restart container id 
	docker rm container id 
#-t 是加入时间戳 -f 跟随最新的日志打印 --tail 数字显示最后多少条
	docker logs -tf container id 
#启动tomcat host 模式
docker run -dit --name tomcat_host_8081 --network=host --hostname=tomcat_host_8081 -v /home/hariko/conf/tomcat/server.conf:/var/server.conf tomcat:latest
#启动tomcat bridge 模式
docker run -dit --name tomcat_host_8081 --network=bridge --hostname=tomcat_bridge_8082 -p 8082:8080  -v /home/hariko/temp/server.conf:/var/server.conf tomcat
#启动mysql -e 指定容器中的环境变量
	docker run --name mysql_8887 -e MYSQL_ROOT_PASSWORD=hariko -d -p 8887:3306 mysql:8.0.19
	#一次性删除多个容器
	docker rm -f $(docker ps -a -q)
	docker ps -a -q | xargs docker rm
	#查看容器内部细节
	docker inspect container id #结果是一串json
	#直接进入容器启动命令的终端,不会启动新的进程
	docker exec -it container id  /bin/sh 	
#是打开新的终端,并且可以启动新的进程

docker attach container id 
#保存镜像 加载镜像
docker save tomcat -o tomcat.tar.gz
docker load -i tomcat.tar.gz
#上传自己做好的镜像 可以自己研究
docker push

三、命令使用
#创建一个mysql的子网，想指定ip只能在自己的子网中建立
docker network create --subnet 172.18.0.0/24 hariko_net 
#查看、删除、加入容器、移除容器
docker network ls/inspect/rm/connect/disconnent
#在子网中创建mysql容器
docker run -dit --network hariko_net --ip 172.18.0.10 -v /home/hariko/conf/mysql/m_8885_my.cnf:/etc/mysql/my.cnf --name mysql_m_8885 -p 8885:3306 -e MYSQL_ROOT_PASSWORD=hariko mysql:8.0.19
docker run -dit --network hariko_net --ip 172.18.0.11 -v /home/hariko/conf/mysql/s_8886_my.cnf:/etc/mysql/my.cnf --name mysql_s_8886 -p 8886:3306 -e MYSQL_ROOT_PASSWORD=hariko mysql:8.0.19
docker run -dit --network hariko_net --ip 172.18.0.12 -v /home/hariko/conf/mysql/activiti_my.cnf:/etc/mysql/my.cnf --name mysql_activiti_8887 -p 8887:3306 -e MYSQL_ROOT_PASSWORD=hariko mysql:5.7.30
docker cp mysql_m_8885:/etc/mysql/my.cnf /home/hariko/conf/mysql/m_8885_my.cnf
#docker管理工具
docker run -d -p 9000:9000 --restart=always -v /var/run/docker.sock:/var/run/docker.sock --name docker_console portainer/portainer
#查看tomcat_host_8081的日志 可以把日志直接挂载到宿主机目录
docker logs -f tomcat_host_8081
#进入tomcat_host_8081
docker exec -it tomcat_host_8081 /bin/sh

三、docker和maven结合
#开启docker远程服务 不知道路径 可以 service docker status 查看
vi /lib/systemd/system/docker.service 
ExecStart=/usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
systemctl daemon-reload
service docker restart
netstat -anp | grep 2375
DOCKER_HOST=tcp://192.168.0.161:2375
DOCKER_HOST=http://192.168.0.161:2375
#验证
curl 127.0.0.1:2375/info
http://192.168.0.161:2375/images/json
#到所在项目下执行maven的docker构建命令
mvn clean package docker:build -DskipTests

四、项目构建及Dockerfile相关
docker pull openjdk:8u102-jdk
Docker copy/add 是有区别的 官方建议用copy add 在复制压缩文件方面有好处
Docker run/cmd  run 运行在 docker build 阶段 cmd 运行在 docker run 阶段




