package com.rjp.eaction.network.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.HttpException;


/**
 * Created by Tamic on 2016-08-12.
 */
public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = getErrorMsg(ex.code);
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
            ex.message = getErrorMsg(ex.code);
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = getErrorMsg(ex.code);
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
            ex.message = getErrorMsg(ex.code);
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponeThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = getErrorMsg(ex.code);
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ResponeThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = getErrorMsg(ex.code);
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ResponeThrowable(e, ERROR.UNKNOW_HOST);
            ex.message = getErrorMsg(ex.code);
            return ex;
        } else {
            ex = new ResponeThrowable(e, ERROR.UNKNOWN);
            ex.message = getErrorMsg(ex.code);
            return ex;
        }
    }

    /**
     * 获取错误码的描述
     * @param code
     * @return
     */
    public static String getErrorMsg(int code) {
        switch (code){
            case ERROR.UNKNOWN:
                return "未知错误";
            case ERROR.PARSE_ERROR:
                return "解析错误";
            case ERROR.NETWORD_ERROR:
                return "网络错误";
            case ERROR.HTTP_ERROR:
                return "协议出错";
            case ERROR.TIMEOUT_ERROR:
                return "连接超时";
            case ERROR.UNKNOW_HOST:
                return "未知的主机异常";
            case ERROR.SSL_ERROR:
                return "证书出错";
        }
        return "未知错误";
    }


    /**
     * 约定错误码
     */
    public class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;

        /**
         * 未知的主机异常
         */
        public static final int UNKNOW_HOST = 1007;
    }

    /**
     * 自定义错误类型
     */
    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    public class ServerException extends RuntimeException {
        public int code;
        public String message;
    }
}

