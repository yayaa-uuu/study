### docker 官方镜像不可用，解决方案
```shell
sudo apt-get install skopeo

skopeo copy docker://docker.io/apache/kafka:latest docker-archive:kafka_latest.tar:apache/kafka:latest

docker load < kafka_latest.tar

```


### 启动 flink
```shell

```

```shell

```


### 使用docker compose 启动
```

```