# cafe

## 실행법

### **cafe-webflux-memory**
1. gradle task를 실행해주세요.
```shell
./gradlew :cafe-webflux-memory:bootRun
```

### **cafe-webflux**
1. docker-compose를 실행해주세요.
```shell
docker-compose up
```

2. grpc-reactor booRun task를 실행해주세요.
```shell
./gradlew :cafe-user-grpc-reactor:bootRun
```

3. cafe-webflux bootRun task를 실행해주세요.
```shell
./gradlew :cafe-webflux:bootRun
```

만약 telegram api 연동을 하고 싶으시다면, telegram_bot_token을 발급 받고 환경변수로 주입해주세요.
```shell
TELEGRAM_BOT_TOKEN=<telegram_bot_token> ./gradlew :cafe-webflux:bootRun
```

[token 발급](https://core.telegram.org/bots#3-how-do-i-create-a-bot)

### **cafe-webflux-coroutine**
1. docker-compose를 실행해주세요
```shell
docker-compose up
```

2. grpc-kotlin booRun task를 실행해주세요
```shell
./gradlew :cafe-user-grpc-kotlin:bootRun
```

3. cafe-webflux-coroutine bootRun task를 실행해주세요
```shell
./gradlew :cafe-webflux-coroutine:bootRun
```

만약 telegram api 연동을 하고 싶으시다면, telegram_bot_token을 발급 받고 환경변수로 주입해주세요.
```shell
TELEGRAM_BOT_TOKEN=<telegram_bot_token> ./gradlew :cafe-webflux-coroutine:bootRun
```

[token 발급](https://core.telegram.org/bots#3-how-do-i-create-a-bot)

## 테스트
1. docker 를 실행해주세요

2. cafe-webflux-coroutine test를 실행해주세요
```shell
./gradlew :cafe-webflux-coroutine:test
```
