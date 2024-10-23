# 직렬화
객체들의 데이터를 연속적인 데이터(스트림)로 변형하여 전송 가능한 형태(Byte, Json, CSV...)로 만드는 것

# 역직렬화
직렬화된 데이터를 다시 객체 형태로 만드는 것

## 직렬화가 필요한 이유
Primitive type 이외에 객체들은 주소값을 가지는 참조형 타입
Primitive type은 값 자체를 가지고 있어 외부로 전달할 때 raw byte 형태로 변경하여 전달 가능하지만 객체의 경우 Heap 영역에 존재하는 주소값을 가지고 있기 때문에

## 직렬화 방법 (바이트 기준)
1. 직렬화 하고자 하는 객체 Serializable 인터페이스 구현
2. ObjectOutputStream.writeObject 호출
   1. 내부에서 객체 메타데이터(패키지, 클래스명, serialVersionUID, 필드명, 필드 타입 등) 저장
   2. 필드 값들 저장
3. ObjectOutputStream 생성시 넘겨줬던 OutputStream 내 byte[] 에 값 저장

## 역직렬화 방법
1. 직렬화 된 값을 가진 InputStream 객체로 ObjectInputStream 생성
2. ObjectInputStream.readObject 실행
   1. 직렬화 된 값에서 메타데이터 읽어옴
   2. 필드 값 읽어와서 객체 생성
