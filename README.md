## MongoDB

### 1.Docker run

```shell
docker run --name mongodb -v ~/data:/data/db -d -p 27017:27017 mongo
```

### 2.명령어

```shell
# databases 목록 확인
show databases;
# databases MARKETBOM2 사용(생성커맨드 따로 없음.)
use MARKETBOM2;

# collection 확인
show collections;

# chat Collection 생성
# db.userEvent.drop()
db.createCollection( "userEvent", { capped: true, size: 10485760, max: 5000 } )

# Capped 되어 있는 확인 방법
# db.userEvent.isCapped()

# Capped이 되지 않는 collection을 capped으로 변경하는 방법
#db.runCommand({convertToCapped: 'userEvent', size: 10485760, max: 5000});

# 조회
db.userEvent.find();
db.userEvent.find().pretty();
```

