# I/O
## Byte를 다루는 클래스
- OutputStream
- InputStream

## String, Char과 같이 문자를 다루는 클래스
- Writer
- Reader

문자를 받아서 byte로 변경 후 outputStream으로 데이터 전달<br>
inputStrema에서 byte로 받아서 문자로 변경

**!모든 데이터는 무조건 byte로 저장 됨!**<br>
**문자 인코딩을 위해서는(byte로 변경하기 위해서는) 무조건 인코딩 표가 필요**
