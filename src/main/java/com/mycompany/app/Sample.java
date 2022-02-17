package com.mycompany.app;

import com.baidu.aip.face.AipFace;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "25622621";
    public static final String API_KEY = "blFHL7URyfK9SciiSYIogVbM";
    public static final String SECRET_KEY = "6WEELM0M2jjFrx5WauYhtsp2dkKY3EOY";

    public static void main(String[] args) {
//        文档地址
//        http://ai.baidu.com/docs#/Face-Java-SDK/top
        String image = base64();
        String imageType = "BASE64";

        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 传入可选参数调用接口，根据需求自行设置
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        JSONObject res = client.detect(image, imageType, options);
        if (res.getString("error_msg") != null && res.getString("error_msg").equals("SUCCESS")) {
            System.out.println(res.toString(2));
        } else {
            System.out.println(res.toString());
        }
    }

    public static String base64() {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream("C:\\test\\psc (2).jpg");
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

}
