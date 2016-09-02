package com.rat.snowkids.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : 下载工具
 */
public class DownLoadUtil {
    private DownLoadUtil() {
    }

    public static boolean download(String downloadUrl, String localPathPrefix, boolean isCoverDownload) {
        String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1);
        File file = new File(localPathPrefix + File.separator + fileName);
        // 检查路径是否存在
        File dir = new File(localPathPrefix);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 文件存在
        if (file.exists()) {
            // 覆盖下载，文件存在，先删除
            if (isCoverDownload) {
                file.delete();
                file = new File(localPathPrefix + File.separator + fileName);
            }
            // 不覆盖下载，文件存在，直接退出
            else
                return true;
        }

        InputStream is = null;
        OutputStream os = null;
        try {
            // 构造URL
            URL url = new URL(downloadUrl);
            // 打开连接
            URLConnection con = url.openConnection();
            // 获得文件的长度
            int contentLength = con.getContentLength();
            // 输入流
            is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            os = new FileOutputStream(file);
            // 开始读取
            while ((len = is.read(bs)) > 0) {
                os.write(bs, 0, len);
            }
            os.flush();
            LogUtil.i("download file success: " + downloadUrl);
            return true;
        } catch (Throwable e) {
            LogUtil.e("download file error: " + downloadUrl, e);
            return false;
        } finally {
            try {
                if (null != os)
                    os.close();
                if (null != is)
                    is.close();
            } catch (Throwable e) {
                LogUtil.e("io close error: ", e);
            }
        }
    }
}