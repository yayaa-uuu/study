package com.wx.udp;

import java.net.InetSocketAddress;

public final class LogEvent {
    public static final byte SEPARATOR = (byte) ':';
    private final InetSocketAddress source;
    private final String logfile;
    private final String msg;
    private final long received;

    public LogEvent(String logfile, String msg) {
        this(null, -1, logfile, msg);
    }

    public LogEvent(InetSocketAddress source, long received, String logfile, String msg) {
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
        this.received = received;
    }

    //返回发送 LogEvent 的源的 InetSocketAddress
    public InetSocketAddress getSource() {
        return source;
    }

    //返回所发送的 LogEvent的日志文件的名称
    public String getLogfile() {
        return logfile;
    }

    //返回消息内容
    public String getMsg() {
        return msg;
    }

    //返回接收 LogEvent的时间
    public long getReceivedTimestamp() {
        return received;
    }
}