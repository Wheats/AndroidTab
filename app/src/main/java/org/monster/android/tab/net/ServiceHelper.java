package org.monster.android.tab.net;

import android.content.Context;
import android.text.TextUtils;

import org.monster.android.tab.MiGoApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pck on 17-10-12.
 */

public class ServiceHelper {

    /**
     * 设置当前连接的服务器环境；
     *
     * @return url host 路径
     */
    public static String buildUrl(String path) {
        boolean isOnline = true;
        return buildUrl(isOnline, path);
    }

    /**
     * 设置当前连接的服务器环境；
     *
     * @return url host 路径
     */
    public static String buildUrl(boolean isOnline, String path) {
//        String hostKey = "http://192.168.8.108:8090";
//        String hostKey = "http://192.168.8.141:8088";
        String hostKey = "http://47.92.88.164";
        return hostKey+ path;
    }

    /**
     * 请求参数；
     */
    public static class ParamBuilder {
        Context mContext;
        Map<String, String> mParams;

        public ParamBuilder(Context context) {
            mContext = context;
            mParams = new HashMap<>();
        }

        public ParamBuilder add(String key, String value) {
            if (TextUtils.isEmpty(value)) {
                value = "";
            }
            mParams.put(key, value);
            return this;
        }

        public ParamBuilder add(String key, char value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder add(String key, int value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder add(String key, long value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder add(String key, float value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder add(String key, double value) {
            mParams.put(key, String.valueOf(value));
            return this;
        }

        public ParamBuilder addAll(Map<String, String> params) {
            if (params != null) {
                mParams.putAll(params);
            }
            return this;
        }

        public Map<String, String> build() {
            mParams.put("lon",  "");
            mParams.put("lat", "");
            mParams.put("devType", "0");//0Android  1IOS
            String sign = getSign();
            mParams.put("sign", sign);
            return mParams;
        }

        public String getSign() {
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : mParams.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs.add(new NameValuePair(key, value));
                }
                return Md5SecurityUtil.getSignatureValue(nameValuePairs);
            } catch (Exception e) {
                e.printStackTrace();
                return "getLocalSignFailed";
            }
        }
    }
}
