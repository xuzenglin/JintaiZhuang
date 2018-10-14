package com.chris.lanfeng.utils;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @ClassName:  WebSocket
 * @Description: websocket数据订阅服务
 * @author: Riven
 * @date:   2017年7月10日 上午9:38:26
 *
 * @Copyright: 2017 www.lbssoft.com Inc. All rights reserved.
 */
public class WebSocket {
    private URI mUrl;

    private Socket mSocket;

    private boolean mHandshakeComplete;

    private InputStream mInput;

    private OutputStream mOutput;

    private HashMap<String, String> mHeaders;


    public WebSocket(URI url) {
        mUrl = url;

        String protocol = mUrl.getScheme();
        if (!protocol.equals("ws") && !protocol.equals("wss")) {
            throw new IllegalArgumentException("Unsupported protocol: " + protocol);
        }
    }


    public void setHeaders(HashMap<String, String> headers) {
        mHeaders = headers;
    }


    public Socket getSocket() {
        return mSocket;
    }


    public void connect() throws IOException {
        String host = mUrl.getHost();
        String path = mUrl.getPath();
        if (path.equals("")) {
            path = "/";
        }

        String query = mUrl.getQuery();
        if (query != null) {
            path = path + "?" + query;
        }

        String origin = "http://" + host;

        mSocket = createSocket();
        int port = mSocket.getPort();
        if (port != 80) {
            host = host + ":" + port;
        }

        mOutput = mSocket.getOutputStream();
        StringBuffer extraHeaders = new StringBuffer();
        if (mHeaders != null) {
            for (Entry<String, String> entry : mHeaders.entrySet()) {
                extraHeaders.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
            }
        }

        String request = "GET "+path+" HTTP/1.1\r\n" +
                "Upgrade: WebSocket\r\n" +
                "Connection: Upgrade\r\n" +
                "Host: "+host+"\r\n" +
                "Origin: "+origin+"\r\n" +
                extraHeaders.toString() +
                "\r\n";
        mOutput.write(request.getBytes());
        mOutput.flush();

        mInput = mSocket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(mInput));
        String header = reader.readLine();
        if (!header.equals("HTTP/1.1 101 Web Socket Protocol Handshake")) {
            throw new IOException("Invalid handshake response");
        }

        header = reader.readLine();
        if (!header.equals("Upgrade: WebSocket")) {
            throw new IOException("Invalid handshake response");
        }

        header = reader.readLine();
        if (!header.equals("Connection: Upgrade")) {
            throw new IOException("Invalid handshake response");
        }

        do {
            header = reader.readLine();
        } while (!header.equals(""));

        mHandshakeComplete = true;
    }

    private Socket createSocket() throws IOException {
        String scheme = mUrl.getScheme();
        String host = mUrl.getHost();

        int port = mUrl.getPort();
        if (port == -1) {
            if (scheme.equals("wss")) {
                port = 443;
            } else if (scheme.equals("ws")) {
                port = 80;
            } else {
                throw new IllegalArgumentException("Unsupported scheme");
            }
        }

        if (scheme.equals("wss")) {
            SocketFactory factory = SSLSocketFactory.getDefault();
            return factory.createSocket(host, port);
        } else {
            return new Socket(host, port);
        }
    }


    public void send(String str) throws IOException {
        if (!mHandshakeComplete) {
            throw new IllegalStateException("Handshake not complete");
        }

        mOutput.write(0x00);
        mOutput.write(str.getBytes("UTF-8"));
        mOutput.write(0xff);
        mOutput.flush();
    }


    public String recv() throws IOException {
        if (!mHandshakeComplete) {
            throw new IllegalStateException("Handshake not complete");
        }

        StringBuffer buf = new StringBuffer();

        int b = mInput.read();
        if ((b & 0x80) == 0x80) {
            // Skip data frame
            int len = 0;
            do {
                b = mInput.read() & 0x7f;
                len = len * 128 + b;
            } while ((b & 0x80) != 0x80);

            for (int i = 0; i < len; i++) {
                mInput.read();
            }
        }

        while (true) {
            b = mInput.read();
            if (b == 0xff) {
                break;
            }

            buf.append((char)b);
        }

        return new String(buf.toString().getBytes(), "UTF8");
    }


    public void close() throws IOException {
        mInput.close();
        mOutput.close();
        mSocket.close();
    }



}