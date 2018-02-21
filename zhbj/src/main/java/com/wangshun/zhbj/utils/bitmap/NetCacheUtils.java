package com.wangshun.zhbj.utils.bitmap;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 网络缓存工具类
 *
 * @author Kevin
 */
public class NetCacheUtils {

    LocalCacheUtils mLocalCacheUtils;
    MemoryCacheUtils mMemoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils localCacheUtils,
                         MemoryCacheUtils memoryCacheUtils) {
        mLocalCacheUtils = localCacheUtils;
        mMemoryCacheUtils = memoryCacheUtils;
    }

    public void getBitmapFromNet(ImageView ivPic, String url) {
        BitmapTask task = new BitmapTask();
        task.execute(new Object[]{ivPic, url});
    }

    //异步封装工具、可以实现异步请求及主界面更新（对线程池+handler的封装）

    /**
     * 三个泛型的意义：
     * 1、doInBackground和execute 中的参数类型
     * 2、onProgressUpdate中的参数类型
     * 3、onPostExecute中的参数类型及doInBackground的返回值类型
     */
    class BitmapTask extends AsyncTask<Object, Void, Bitmap> {

        private ImageView imageView;
        private String url;

        //预加载，运行在主线程
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 返回的对象会自动回传到onPostExecute里面
         * 运行在子线程
         */
        @Override
        protected Bitmap doInBackground(Object... params) {
            imageView = (ImageView) params[0];
            url = (String) params[1];
            imageView.setTag(url);
            Bitmap bitmap = downloadBitmap(url);

            //publishProgress(values); //进度更新(会回调)

            return bitmap;
        }

        //更新进度的方法，运行在主线程
        @Override
        protected void onProgressUpdate(Void... values) {
            //更新进度条
            super.onProgressUpdate(values);
        }

        //加载结束之后，运行在主线程
        @Override
        protected void onPostExecute(Bitmap result) {
            // 这里的result就是doInBackground返回回来的对象
            if (result != null) {
                String ivUrl = (String) imageView.getTag();
                if (url.equals(ivUrl)) {// 确保imageview设置的是正确的图片（因为有时候listview有重用机制，多个item会公用一个imageview对象，从而导致图片错乱）
                    imageView.setImageBitmap(result);
                    System.out.println("从网络缓存读取图片");

                    // 向本地保存图片文件
                    mLocalCacheUtils.putBitmapToLocal(url, result);
                    // 向内存保存图片对象
                    mMemoryCacheUtils.putBitmapToMemory(url, result);
                }
            }
        }
    }

    /**
     * 下载图片
     *
     * @param url
     * @return
     */
    private Bitmap downloadBitmap(String url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return null;
    }

}
