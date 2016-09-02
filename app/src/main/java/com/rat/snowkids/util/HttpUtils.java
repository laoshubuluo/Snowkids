package com.rat.snowkids.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class HttpUtils {
    private static final int TIME_OUT = 10 * 1000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码

    public static InputStream getInputStreamFromUrl(String urlStr)
            throws MalformedURLException, IOException {
        URL url = new URL(urlStr);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConn.getInputStream();
        return inputStream;
    }

    /**
     * 从网络获取 字符数组
     *
     * @param url
     * @return
     */
    public static byte[] getByteFromHttps(String path) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(path);
        try {
            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toByteArray(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return null;
    }

    /**
     * <pre>
     * 发送带参数的POST的HTTP请求
     * </pre>
     *
     * @param reqUrl     HTTP请求URL
     * @param parameters 参数映射表
     * @return HTTP响应的字符串
     */
    public static String doPost(String reqUrl, Map<String, String> parameters,
                                String recvEncoding) {
        HttpURLConnection url_con = null;
        String responseContent = null;
        try {
            StringBuffer params = new StringBuffer();
            for (Iterator<Entry<String, String>> iter = parameters.entrySet()
                    .iterator(); iter.hasNext(); ) {
                Entry<String, String> element = iter.next();
                params.append(element.getKey().toString());
                params.append("=");
                params.append(URLEncoder.encode(element.getValue().toString(),
                        "utf-8"));
                params.append("&");
            }

            if (params.length() > 0) {
                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(reqUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(10000));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(10000)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            // url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
            // 1.5换成这个,连接超时
            // url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null) {
                tempStr.append(tempLine);
                tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (url_con != null) {
                url_con.disconnect();
            }
        }
        return responseContent;
    }

    /**
     * @param file
     * @param RequestURL
     * @return
     */
    public static String uploadFileClient(File file, String RequestURL) {
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("Charset", "utf-8"); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);
            if (file != null) {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                DataOutputStream dos = new DataOutputStream(
                        conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""
                        + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="
                        + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());

                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();

                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String doGet(String illCaseid) {
        // 创建httpRequest对象
        HttpGet httpRequest = new HttpGet(illCaseid);
        try {
            // 取得HttpClient对象
            HttpClient httpclient = new DefaultHttpClient();
            // 请求HttpClient，取得HttpResponse
            HttpResponse httpResponse = httpclient.execute(httpRequest);
            // 请求成功
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 取得返回的字符串
                String strResult = EntityUtils.toString(
                        httpResponse.getEntity(), "UTF-8");
                return strResult;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    /**
//     * 从网络Url中下载文件
//     *
//     * @param urlStr
//     * @param fileName
//     * @param savePath
//     * @throws IOException
//     */
//    public static void downLoadFromUrl(Uri url, String fileName, String savePath) throws IOException {
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        //设置超时间为3秒
//        conn.setConnectTimeout(3 * 1000);
//        //防止屏蔽程序抓取而返回403错误
//        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//
//        //得到输入流
//        InputStream inputStream = conn.getInputStream();
//        //获取自己数组
//        byte[] getData = readInputStream(inputStream);
//
//        //文件保存位置
//        File saveDir = new File(savePath);
//        if (!saveDir.exists()) {
//            saveDir.mkdir();
//        }
//        File file = new File(saveDir + File.separator + fileName);
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(getData);
//        if (fos != null) {
//            fos.close();
//        }
//        if (inputStream != null) {
//            inputStream.close();
//        }
//
//
//        System.out.println("info:" + url + " download success");
//
//    }
//
//
//    /**
//     * 从输入流中获取字节数组
//     *
//     * @param inputStream
//     * @return
//     * @throws IOException
//     */
//    public static byte[] readInputStream(InputStream inputStream) throws IOException {
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        while ((len = inputStream.read(buffer)) != -1) {
//            bos.write(buffer, 0, len);
//        }
//        bos.close();
//        return bos.toByteArray();
//    }
//
//    public static void main(String[] args) {
//        try {
//            downLoadFromUrl("http://101.95.48.97:8005/res/upload/interface/apptutorials/manualstypeico/6f83ce8f-0da5-49b3-bac8-fd5fc67d2725.png",
//                    "百度.jpg", "d:/resource/images/diaodiao/country/");
//        } catch (Exception e) {
//        }
//    }
}
