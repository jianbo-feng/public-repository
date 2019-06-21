# Springboot 开发的项目推送到Docker

## 组件/框架版本要求
```
1、SpringBoot版本：2.1.6.RELEASE
2、docker-maven-plugin版本：1.0.0
<groupId>com.spotify</groupId>
<artifactId>docker-maven-plugin</artifactId>
<version>1.0.0</version>
```
## 本地Maven设置
```
setting.xml文件servers节点下添加
<server>
  <id>my-docker-registry</id>
  <username>yourname</username>
  <password>yourpasswd</password>
  <configuration>
    <email>yourmail@xx.com</email>
  </configuration>
</server>
```
## 注册hub.docker.com(和github、gitlab类似)账户，并创建repository

## docker的image配置
```
<imageName>${docker.image.prefix}/${project.artifactId}</imageName>
特别说明：
1、docker.image.prefix docker镜像的前缀，设置你在hub.docker.com的注册用户名（例如zhangsan）
2、project.artifactId 设置成在hub.docker.com的里面的仓库名称（例如docker2）
```

## 推送镜像语句
```
docker push zhangsan/docker2:latest 
我本地的git账户有点混乱（即有github、又有gitlad），直接使用idea的docker-push操作未成功，使用终端进行推送则成功
```

## 在Kitematic中查看推送结果
```
推送结束之后，先退出kitematic，再启动；可在 My Repos、My Images中看到最新的镜像
```