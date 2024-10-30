# NGINX란
HTTP Web Server로 리버스프록시, 캐싱, 로드밸런서, 프록시서버 등을 가능하게 제공<br>
보통 Client -> Web Server -> WAS -> DB 순으로 요청이 들어옴

## 구조
하나의 Master 프로세스와 여러개의 Worker 프로세스로 이루어져있음<br>
이벤트 기반 모델 구조, OS 종속 메커니즘을 사용해서 Worker 프로세스간에 요청을 효율적으로 분산

!참고<br>
설정 파일은 `/etc/nginx`하위에 nginx.conf 파일 존재

**Master Process**
- 설정을 읽고 적용
  - 설정파일(nginx.conf), 환경변수등을 읽고 시스템에 반영
- Worker 프로세스 유지 관리

**Worker Process**
- 실제 요청에 대해서 처리 담당

## Nginx 사용 법


# 참고
[proxyServer](./proxyServer.md)
[nginx](https://nginx.org/en/docs/beginners_guide.html)

# 추가로 공부할 내용
- nginx vs apache
- nginx 구조, 작동 원리