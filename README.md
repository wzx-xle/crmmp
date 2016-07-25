# Computer Resource Management Monitor Platform
计算机资源管理监控平台

## 概述
用来监控和管理计算机资源，包括 CPU、内存、硬盘、网络等资源，同时兼容windows和linux平台。Web系统提供资源的定义和分类，资源状态的报表和告警通知。

## 目标
* 部署探针的计算机自动加入到平台中；
* 计算机资源单个及分组管理；
* 探针收集目标信息反馈到指定MQ中；

## 架构
* Web管理平台
* probe，探针
* MQ系统，可选RabbitMQ，ActiveMQ
* MessageProcessor系统