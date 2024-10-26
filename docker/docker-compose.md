# 도커컴포즈
yml 설정 파일을 사용해서 실행됨

도커컴포즈 파일 기본 경로는 compose.yaml(추천됨), compose.yml, 이전버전의 호환성을 위해 docker-compose.yaml, docker-compose.yml도 지원하지만 여러 파일이 있는 경우 compose.yaml이 적용됨

## 실행 방법
- docker compose up
  - 실행
- docker compose down
  - 중단 및 삭제
- docker compose logs
  - 로그 확인

## 파일 형식
```yml
version: ${verison} # optional since v1.27.0

services: # 실행 할 서비스 목록
    service1: # 이름
        # 서비스 설정
        images:
        ports:
        volumes:
        networks: # 설정 없으면 기본 드라이버는 bridge 사용
        links: # 네트워크를 통해 컨테이너 연결
        depends_on: # 타겟 서비스 이후 실행
    service2:
        # 서비스 설정

networks:
    network1:
        driver:
        driver_opts:
            subnet:
            IPAddress:
volumes:

```

# 참고
[docker docs](https://docs.docker.com/compose/)