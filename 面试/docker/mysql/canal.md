```shell
skopeo copy \
  docker://docker.io/canal/canal-server:v1.1.8 \
  docker-archive:canal-server.tar:canal/canal-server:v1.1.8
  
docker load -i canal-server.tar  
```

```shell
  ./run.sh -e canal.instance.master.address=127.0.0.1:3306 \
         -e canal.instance.dbUsername=canal \
         -e canal.instance.dbPassword=canal \
         -e canal.instance.connectionCharset=UTF-8 \
         -e canal.instance.tsdb.enable=true \
         -e canal.instance.gtidon=false \
         -e canal.instance.filter.regex=.*\\..* 
```