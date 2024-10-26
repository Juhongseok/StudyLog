# 도커파일
**컨테이너 이미지를 만들기위한 텍스트기반 문서**

## 명령어 정리
```dockerFile
FROM ${image}
WORKDIR ${path}

COPY ${host-path} ${image-path}
RUN ${command}
EXPOSE ${port-number}
ENV ${key}=${value}
ENTRYPOINT ${command}
CMD
```

- FROM
  - 빌드가 확장할 기본 이미지 파일
- WORKDIR
  - 향후 명령이 실행되는 위치와 디렉토리 파일이 컨테이너 이미지 내부에 복사될 위치
  - shell에서 cd 명령어와 같은 역할
- COPY
  - 호스트에서 컨테이너로 파일 복사하기 위해 사용
- RUN
  - 쉘에서 커맨드 실행하는 것처럼 이미지 빌드 과정에 필요한 커맨드 실행
- EXPOSE
  - 오픈 할 포트 지정
  - 단 컨테이너 실행 시점에 -p 옵션을 통해 호스트와 포트포워딩 필요
- ENV
  - 환경 변수 설정을 위해 사용
- ENTRYPOINT
  - 컨테이너가 실행될때 항상 실행되어야 하는 커맨드
- CMD
  - 컨테이너 실행시 디폴트로 실행할 커맨드
  - ENTRYPOINT로 지정된 커맨드에 넘길 파라미터 지정

## 이미지로 만드는 법
도커 파일이 있는 디렉토리에서 `docker build .` 실행

`.`은 현재 디렉토리를 나타내는 경로<br>
디렉토리가 다르다면 경로를 따로 지정해주면 됨

### 이름 설정
`-t` 옵션을 통해 이미지 태그 이름 설정 가능

