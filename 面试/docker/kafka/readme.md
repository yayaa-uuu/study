### docker 官方镜像不可用，解决方案
```shell
sudo apt-get install skopeo

skopeo copy docker://docker.io/apache/kafka:latest docker-archive:kafka_latest.tar:apache/kafka:latest

docker load < kafka_latest.tar

```


### 启动kafka
```shell
docker rm -f kafka

docker run -d \
  --name kafka \
  -p 9092:9092 \
  -e KAFKA_NODE_ID=1 \
  -e KAFKA_PROCESS_ROLES=broker,controller \
  -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.1.6:9092 \
  -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
  -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9093 \
  -e KAFKA_KRAFT_CLUSTER_ID=force_rebuild_001 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  -e KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1 \
  -e KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1 \
  apache/kafka:latest
```

```shell
docker exec -it kafka /opt/kafka/bin/kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

docker exec -it kafka /opt/kafka/bin/kafka-topics.sh --list --bootstrap-server localhost:9092

docker exec -it kafka /opt/kafka/bin/kafka-get-offsets.sh --bootstrap-server localhost:9092 --topic test-topic

```

### 测试
```shell
docker exec -it kafka /opt/kafka/bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
docker exec -it kafka /opt/kafka/bin/kafka-console-consumer.sh --topic test-topic --from-beginning --bootstrap-server localhost:9092

```


### 使用docker compose 启动
```yml
services:
  kafka:
    image: apache/kafka:latest
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      # 角色与节点 ID
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: 'broker,controller'
      
      # 监听配置
      KAFKA_LISTENERS: 'PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093'
      KAFKA_ADVERTISED_LISTENERS: 'PLAINTEXT://192.168.1.6:9092'
      KAFKA_CONTROLLER_LISTENER_NAMES: 'CONTROLLER'
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: 'CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT'
      
      # KRaft 集群配置
      KAFKA_CONTROLLER_QUORUM_VOTERS: '1@localhost:9093'
      KAFKA_KRAFT_CLUSTER_ID: 'force_rebuild_001'
      
      # 副本与分片配置 (单机模式通常设为 1)
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
    restart: unless-stopped
#    volumes:
#      - ./kafka-data:/var/lib/kafka/data
```