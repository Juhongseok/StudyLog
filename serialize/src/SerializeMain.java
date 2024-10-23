import dto.MemberInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class SerializeMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MemberInfo member = new MemberInfo("member", 20);

        System.out.println("[직렬화] 시작");
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteStream);
        outputStream.writeObject(member);
        byte[] serializedResult = byteStream.toByteArray();
        System.out.println("직렬화 결과 : " + Arrays.toString(serializedResult));
        System.out.println("[직렬화] 종료");

        System.out.println("-----------------------------------------------");

        System.out.println("[역직렬화] 시작");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedResult);
        ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
        MemberInfo memberInfo = (MemberInfo) inputStream.readObject();
        System.out.println("역직렬화 결과 : " + memberInfo);
        System.out.println("[역직렬화] 종료");
    }

}
