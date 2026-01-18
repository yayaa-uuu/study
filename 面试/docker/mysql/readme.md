### docker 官方镜像不可用，解决方案
```shell
sudo apt-get install skopeo

skopeo copy docker://mysql:8.0.44 docker-archive:mysql.tar



```


#### 使用 docker 部署 mysql
```shell
#!/bin/bash
# 1. 导入镜像
docker load < mysql.tar

# 2. 如果镜像显示为 <none>，手动打标签
docker tag <IMAGE_ID> mysql:8.0

# 2. 创建数据目录
mkdir -p ~/mysql-data

# 3. 启动 MySQL
docker run -d \
  --name mysql-server \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=myappdb \
  -p 3306:3306 \
  -v ~/mysql-data:/var/lib/mysql \
  mysql:8.0
  
# 4. 等待 MySQL 启动
echo "等待 MySQL 启动..."
sleep 30

# 5. 验证连接
docker exec mysql8 mysql -u root -proot -e "SHOW DATABASES;"
```

```shell

# 创建主目录
mkdir -p ~/mysql-docker

# 创建子目录
mkdir -p ~/mysql-docker/{conf,data,logs}

# 设置正确的权限（MySQL容器通常以mysql用户运行）
sudo chown -R 1000:1000 ~/mysql-docker
# 或者更安全的做法
sudo chmod -R 755 ~/mysql-docker


```

```shell
cat > ~/mysql-docker/conf/custom.cnf << EOF
[mysqld]
# 字符集配置
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci

log-bin=mysql-bin # 开启 binlog
binlog-format=ROW # 选择 ROW 模式
server_id=1 # 配置 MySQL replaction 需要定义，不要和 canal 的 slaveId 重复

# 时区配置
default-time-zone='+08:00'

# 性能配置
max_connections=1000
innodb_buffer_pool_size=256M

# 日志配置
slow_query_log=1
slow_query_log_file=/var/log/mysql/mysql-slow.log
log-error=/var/log/mysql/mysql-error.log

# 其他配置
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
EOF
```

```shell
docker run -d \
  --name mysql8 \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=root \
  -v ~/mysql-docker/conf:/etc/mysql/conf.d \
  -v ~/mysql-docker/data:/var/lib/mysql \
  -v ~/mysql-docker/logs:/var/log/mysql \
  mysql:8.0
```


```yaml
services:
  mysql:
    image: mysql:8.0
    container_name: mysql8
    restart: unless-stopped
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: myapp
      MYSQL_ROOT_HOST: '%'
      TZ: Asia/Shanghai
    ports:
      - "3306:3306"
    volumes:
      - ~/mysql-docker/conf:/etc/mysql/conf.d
      - ~/mysql-docker/data:/var/lib/mysql
      - ~/mysql-docker/logs:/var/log/mysql
    command: 
      - --default-authentication-plugin=mysql_native_password
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci
    networks:
      - mysql-network
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-u", "root", "-p$$MYSQL_ROOT_PASSWORD"]
      interval: 30s
      timeout: 10s
      retries: 3

networks:
  mysql-network:
    driver: bridge

```

```shell
docker compose up -d

docker compose down
```

```shell
docker exec -it mysql8 mysql -u root -proot

CREATE USER canal IDENTIFIED BY 'canal';  
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
-- GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ;
FLUSH PRIVILEGES;

docker exec -it mysql8 mysql -u canal -pcanal
```