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

## Nginx 사용법

### 서비스 시작 및 자동 시작 설정
```bash
# Nginx 서비스 시작
sudo systemctl start nginx
## or
sudo service nginx start

# Nginx 서비스 자동 시작 설정
sudo systemctl enable nginx

# Nginx 재시작 (config 파일 변경 후)
sudo nginx -t # config 파일 테스트
sudo service nginx restart
```

### 설정 파일 위치 및 구조
- /etc/nginx/nginx.conf: 전역 설정 파일, 건들지 말 것!
  - nginx.conf
  ```bash
    http {
        include /etc/nginx/conf.d/*.conf;
        include /etc/nginx/sites-enabled/*;
    }
  ```
  - /etc/nginx/conf.d: 추가적인 conf 설정 파일 위치
- /etc/nginx/sites-availabe: 서버 블록 설정 파일 저장소 (실질적 파일 작성 위치)
  - default
  ```bash
    server {
        listen 80 default_server; # IPv4 80 포트 요청 처리
        listen [::]:80 default_server # IPv6 80 포트 요청 처리

        server_name ${domain_name};
        
        location / { # 도메인으로 들어왔을 때 어떤 위치를 참조하는지 (request URI 설정)

        }
    }
  ```
    - [listen](https://nginx.org/en/docs/http/ngx_http_core_module.html#listen)
    - [location](https://nginx.org/en/docs/http/ngx_http_core_module.html#location)
    - [server_name](https://nginx.org/en/docs/http/ngx_http_core_module.html#server_name)
- /etc/nginx/sites-enabled: 활성화된 서버 블록 심볼릭 링크(`ln -s`) 저장소
  `sudo ln -s /etc/nginx/sites-available/sample.conf /etc/nginx/sites-enabled/`


# 참고
[proxyServer](./proxyServer.md)
[nginx](https://nginx.org/en/docs/beginners_guide.html)

# 추가로 공부할 내용
- nginx vs apache
- nginx 구조, 작동 원리
- systemctl vs service