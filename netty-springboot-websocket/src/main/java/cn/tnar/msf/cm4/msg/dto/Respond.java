// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message(3).proto

package cn.tnar.msf.cm4.msg.dto;

/**
 * <pre>
 **
 * 通用响应消息
 * </pre>
 *
 * Protobuf type {@code cmpark.message.Respond}
 */
public final class Respond extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cmpark.message.Respond)
    RespondOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Respond.newBuilder() to construct.
  private Respond(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Respond() {
    code_ = 0;
    message_ = com.google.protobuf.ByteString.EMPTY;
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new Respond();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Respond(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {
            int rawValue = input.readEnum();

            code_ = rawValue;
            break;
          }
          case 18: {

            message_ = input.readBytes();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return cn.tnar.msf.cm4.msg.dto.Message3.internal_static_cmpark_message_Respond_descriptor;
  }

  @Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return cn.tnar.msf.cm4.msg.dto.Message3.internal_static_cmpark_message_Respond_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            cn.tnar.msf.cm4.msg.dto.Respond.class, cn.tnar.msf.cm4.msg.dto.Respond.Builder.class);
  }

  /**
   * Protobuf enum {@code cmpark.message.Respond.RespondCode}
   */
  public enum RespondCode
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     *不使用默认0
     * </pre>
     *
     * <code>CODE_NOT_USED = 0;</code>
     */
    CODE_NOT_USED(0),
    /**
     * <pre>
     *成功
     * </pre>
     *
     * <code>CODE_SUCCESS = 1;</code>
     */
    CODE_SUCCESS(1),
    /**
     * <pre>
     *失败
     * </pre>
     *
     * <code>CODE_FAILURE = 2;</code>
     */
    CODE_FAILURE(2),
    /**
     * <pre>
     *重复
     * </pre>
     *
     * <code>CODE_DUPLICATE = 3;</code>
     */
    CODE_DUPLICATE(3),
    /**
     * <pre>
     *外部引用不存在
     * </pre>
     *
     * <code>CODE_MISMATCH = 4;</code>
     */
    CODE_MISMATCH(4),
    /**
     * <pre>
     *暂不支持的功能
     * </pre>
     *
     * <code>CODE_NOT_SUPPORTED = 5;</code>
     */
    CODE_NOT_SUPPORTED(5),
    /**
     * <pre>
     *云端内部使用
     * </pre>
     *
     * <code>CODE_OFFLINE = 6;</code>
     */
    CODE_OFFLINE(6),
    UNRECOGNIZED(-1),
    ;

    /**
     * <pre>
     *不使用默认0
     * </pre>
     *
     * <code>CODE_NOT_USED = 0;</code>
     */
    public static final int CODE_NOT_USED_VALUE = 0;
    /**
     * <pre>
     *成功
     * </pre>
     *
     * <code>CODE_SUCCESS = 1;</code>
     */
    public static final int CODE_SUCCESS_VALUE = 1;
    /**
     * <pre>
     *失败
     * </pre>
     *
     * <code>CODE_FAILURE = 2;</code>
     */
    public static final int CODE_FAILURE_VALUE = 2;
    /**
     * <pre>
     *重复
     * </pre>
     *
     * <code>CODE_DUPLICATE = 3;</code>
     */
    public static final int CODE_DUPLICATE_VALUE = 3;
    /**
     * <pre>
     *外部引用不存在
     * </pre>
     *
     * <code>CODE_MISMATCH = 4;</code>
     */
    public static final int CODE_MISMATCH_VALUE = 4;
    /**
     * <pre>
     *暂不支持的功能
     * </pre>
     *
     * <code>CODE_NOT_SUPPORTED = 5;</code>
     */
    public static final int CODE_NOT_SUPPORTED_VALUE = 5;
    /**
     * <pre>
     *云端内部使用
     * </pre>
     *
     * <code>CODE_OFFLINE = 6;</code>
     */
    public static final int CODE_OFFLINE_VALUE = 6;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @Deprecated
    public static RespondCode valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static RespondCode forNumber(int value) {
      switch (value) {
        case 0: return CODE_NOT_USED;
        case 1: return CODE_SUCCESS;
        case 2: return CODE_FAILURE;
        case 3: return CODE_DUPLICATE;
        case 4: return CODE_MISMATCH;
        case 5: return CODE_NOT_SUPPORTED;
        case 6: return CODE_OFFLINE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<RespondCode>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        RespondCode> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<RespondCode>() {
            public RespondCode findValueByNumber(int number) {
              return RespondCode.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return cn.tnar.msf.cm4.msg.dto.Respond.getDescriptor().getEnumTypes().get(0);
    }

    private static final RespondCode[] VALUES = values();

    public static RespondCode valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private RespondCode(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:cmpark.message.Respond.RespondCode)
  }

  public static final int CODE_FIELD_NUMBER = 1;
  private int code_;
  /**
   * <pre>
   *响应码
   * </pre>
   *
   * <code>.cmpark.message.Respond.RespondCode code = 1;</code>
   * @return The enum numeric value on the wire for code.
   */
  @Override public int getCodeValue() {
    return code_;
  }
  /**
   * <pre>
   *响应码
   * </pre>
   *
   * <code>.cmpark.message.Respond.RespondCode code = 1;</code>
   * @return The code.
   */
  @Override public cn.tnar.msf.cm4.msg.dto.Respond.RespondCode getCode() {
    @SuppressWarnings("deprecation")
    cn.tnar.msf.cm4.msg.dto.Respond.RespondCode result = cn.tnar.msf.cm4.msg.dto.Respond.RespondCode.valueOf(code_);
    return result == null ? cn.tnar.msf.cm4.msg.dto.Respond.RespondCode.UNRECOGNIZED : result;
  }

  public static final int MESSAGE_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString message_;
  /**
   * <pre>
   *响应消息
   * </pre>
   *
   * <code>bytes message = 2;</code>
   * @return The message.
   */
  @Override
  public com.google.protobuf.ByteString getMessage() {
    return message_;
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (code_ != cn.tnar.msf.cm4.msg.dto.Respond.RespondCode.CODE_NOT_USED.getNumber()) {
      output.writeEnum(1, code_);
    }
    if (!message_.isEmpty()) {
      output.writeBytes(2, message_);
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (code_ != cn.tnar.msf.cm4.msg.dto.Respond.RespondCode.CODE_NOT_USED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, code_);
    }
    if (!message_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, message_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof cn.tnar.msf.cm4.msg.dto.Respond)) {
      return super.equals(obj);
    }
    cn.tnar.msf.cm4.msg.dto.Respond other = (cn.tnar.msf.cm4.msg.dto.Respond) obj;

    if (code_ != other.code_) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + CODE_FIELD_NUMBER;
    hash = (53 * hash) + code_;
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static cn.tnar.msf.cm4.msg.dto.Respond parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(cn.tnar.msf.cm4.msg.dto.Respond prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   **
   * 通用响应消息
   * </pre>
   *
   * Protobuf type {@code cmpark.message.Respond}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cmpark.message.Respond)
      cn.tnar.msf.cm4.msg.dto.RespondOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return cn.tnar.msf.cm4.msg.dto.Message3.internal_static_cmpark_message_Respond_descriptor;
    }

    @Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cn.tnar.msf.cm4.msg.dto.Message3.internal_static_cmpark_message_Respond_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              cn.tnar.msf.cm4.msg.dto.Respond.class, cn.tnar.msf.cm4.msg.dto.Respond.Builder.class);
    }

    // Construct using cn.tnar.msf.cm4.msg.dto.Respond.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      code_ = 0;

      message_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return cn.tnar.msf.cm4.msg.dto.Message3.internal_static_cmpark_message_Respond_descriptor;
    }

    @Override
    public cn.tnar.msf.cm4.msg.dto.Respond getDefaultInstanceForType() {
      return getDefaultInstance();
    }

    @Override
    public cn.tnar.msf.cm4.msg.dto.Respond build() {
      cn.tnar.msf.cm4.msg.dto.Respond result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public cn.tnar.msf.cm4.msg.dto.Respond buildPartial() {
      cn.tnar.msf.cm4.msg.dto.Respond result = new cn.tnar.msf.cm4.msg.dto.Respond(this);
      result.code_ = code_;
      result.message_ = message_;
      onBuilt();
      return result;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof cn.tnar.msf.cm4.msg.dto.Respond) {
        return mergeFrom((cn.tnar.msf.cm4.msg.dto.Respond)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(cn.tnar.msf.cm4.msg.dto.Respond other) {
      if (other == getDefaultInstance()) return this;
      if (other.code_ != 0) {
        setCodeValue(other.getCodeValue());
      }
      if (other.getMessage() != com.google.protobuf.ByteString.EMPTY) {
        setMessage(other.getMessage());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      cn.tnar.msf.cm4.msg.dto.Respond parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (cn.tnar.msf.cm4.msg.dto.Respond) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int code_ = 0;
    /**
     * <pre>
     *响应码
     * </pre>
     *
     * <code>.cmpark.message.Respond.RespondCode code = 1;</code>
     * @return The enum numeric value on the wire for code.
     */
    @Override public int getCodeValue() {
      return code_;
    }
    /**
     * <pre>
     *响应码
     * </pre>
     *
     * <code>.cmpark.message.Respond.RespondCode code = 1;</code>
     * @param value The enum numeric value on the wire for code to set.
     * @return This builder for chaining.
     */
    public Builder setCodeValue(int value) {
      
      code_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *响应码
     * </pre>
     *
     * <code>.cmpark.message.Respond.RespondCode code = 1;</code>
     * @return The code.
     */
    @Override
    public cn.tnar.msf.cm4.msg.dto.Respond.RespondCode getCode() {
      @SuppressWarnings("deprecation")
      cn.tnar.msf.cm4.msg.dto.Respond.RespondCode result = cn.tnar.msf.cm4.msg.dto.Respond.RespondCode.valueOf(code_);
      return result == null ? cn.tnar.msf.cm4.msg.dto.Respond.RespondCode.UNRECOGNIZED : result;
    }
    /**
     * <pre>
     *响应码
     * </pre>
     *
     * <code>.cmpark.message.Respond.RespondCode code = 1;</code>
     * @param value The code to set.
     * @return This builder for chaining.
     */
    public Builder setCode(cn.tnar.msf.cm4.msg.dto.Respond.RespondCode value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      code_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *响应码
     * </pre>
     *
     * <code>.cmpark.message.Respond.RespondCode code = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCode() {
      
      code_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString message_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     *响应消息
     * </pre>
     *
     * <code>bytes message = 2;</code>
     * @return The message.
     */
    @Override
    public com.google.protobuf.ByteString getMessage() {
      return message_;
    }
    /**
     * <pre>
     *响应消息
     * </pre>
     *
     * <code>bytes message = 2;</code>
     * @param value The message to set.
     * @return This builder for chaining.
     */
    public Builder setMessage(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *响应消息
     * </pre>
     *
     * <code>bytes message = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:cmpark.message.Respond)
  }

  // @@protoc_insertion_point(class_scope:cmpark.message.Respond)
  private static final cn.tnar.msf.cm4.msg.dto.Respond DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new cn.tnar.msf.cm4.msg.dto.Respond();
  }

  public static cn.tnar.msf.cm4.msg.dto.Respond getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Respond>
      PARSER = new com.google.protobuf.AbstractParser<Respond>() {
    @Override
    public Respond parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Respond(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Respond> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<Respond> getParserForType() {
    return PARSER;
  }

  @Override
  public cn.tnar.msf.cm4.msg.dto.Respond getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

