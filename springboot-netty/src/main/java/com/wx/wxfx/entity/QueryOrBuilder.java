// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.wx.wxfx.entity;

public interface QueryOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cmpark.message.Query)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *场端序列号
   * </pre>
   *
   * <code>string serial = 1;</code>
   * @return The serial.
   */
  String getSerial();
  /**
   * <pre>
   *场端序列号
   * </pre>
   *
   * <code>string serial = 1;</code>
   * @return The bytes for serial.
   */
  com.google.protobuf.ByteString
      getSerialBytes();

  /**
   * <pre>
   *需要发送给场端的消息
   * </pre>
   *
   * <code>bytes message = 2;</code>
   * @return The message.
   */
  com.google.protobuf.ByteString getMessage();
}
