# 导入现在的配置
FROM ubuntu:latest

# 安装jdk 以及配置环境变量
RUN apt-get update && apt-get install -y openjdk-17-jdk && \
    apt-get install -y ant && \
    apt-get install -y git && \
    apt-get install -y wget && \
    apt-get install -y curl && \
    apt-get install -y vim && \
    apt-get install -y net-tools && \
    apt-get install -y iputils-ping && \
    apt-get install -y telnet && \
    apt-get install -y iproute2 && \
    apt-get install -y iputils-tracepath && \
    apt-get install -y iputils-arping && \
    apt-get install -y iputils-clockdiff && \
    apt-get install -y iputils-nc && \
    apt-get install -y iputils-tracepath \
    # 安装maven \
    && apt-get install -y maven \
    # 安装nodejs \
    && apt-get install -y nodejs \
    # 安装npm \
    && apt-get install -y npm \
    #安装该项目的配置文件 \