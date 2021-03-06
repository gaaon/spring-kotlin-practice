// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rpc/user.proto

package practice.cafe.user.rpc;

/**
 * Protobuf type {@code practice.cafe.user.GetCafeUserResp}
 */
public final class GetCafeUserResp extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:practice.cafe.user.GetCafeUserResp)
    GetCafeUserRespOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetCafeUserResp.newBuilder() to construct.
  private GetCafeUserResp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetCafeUserResp() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetCafeUserResp();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetCafeUserResp(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 10: {
            practice.cafe.user.rpc.CafeUser.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) != 0)) {
              subBuilder = user_.toBuilder();
            }
            user_ = input.readMessage(practice.cafe.user.rpc.CafeUser.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(user_);
              user_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
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
    return practice.cafe.user.rpc.CafeUserServiceProto.internal_static_practice_cafe_user_GetCafeUserResp_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return practice.cafe.user.rpc.CafeUserServiceProto.internal_static_practice_cafe_user_GetCafeUserResp_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            practice.cafe.user.rpc.GetCafeUserResp.class, practice.cafe.user.rpc.GetCafeUserResp.Builder.class);
  }

  private int bitField0_;
  public static final int USER_FIELD_NUMBER = 1;
  private practice.cafe.user.rpc.CafeUser user_;
  /**
   * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
   * @return Whether the user field is set.
   */
  @java.lang.Override
  public boolean hasUser() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
   * @return The user.
   */
  @java.lang.Override
  public practice.cafe.user.rpc.CafeUser getUser() {
    return user_ == null ? practice.cafe.user.rpc.CafeUser.getDefaultInstance() : user_;
  }
  /**
   * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
   */
  @java.lang.Override
  public practice.cafe.user.rpc.CafeUserOrBuilder getUserOrBuilder() {
    return user_ == null ? practice.cafe.user.rpc.CafeUser.getDefaultInstance() : user_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getUser());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getUser());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof practice.cafe.user.rpc.GetCafeUserResp)) {
      return super.equals(obj);
    }
    practice.cafe.user.rpc.GetCafeUserResp other = (practice.cafe.user.rpc.GetCafeUserResp) obj;

    if (hasUser() != other.hasUser()) return false;
    if (hasUser()) {
      if (!getUser()
          .equals(other.getUser())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasUser()) {
      hash = (37 * hash) + USER_FIELD_NUMBER;
      hash = (53 * hash) + getUser().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static practice.cafe.user.rpc.GetCafeUserResp parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(practice.cafe.user.rpc.GetCafeUserResp prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code practice.cafe.user.GetCafeUserResp}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:practice.cafe.user.GetCafeUserResp)
      practice.cafe.user.rpc.GetCafeUserRespOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return practice.cafe.user.rpc.CafeUserServiceProto.internal_static_practice_cafe_user_GetCafeUserResp_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return practice.cafe.user.rpc.CafeUserServiceProto.internal_static_practice_cafe_user_GetCafeUserResp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              practice.cafe.user.rpc.GetCafeUserResp.class, practice.cafe.user.rpc.GetCafeUserResp.Builder.class);
    }

    // Construct using practice.cafe.user.rpc.GetCafeUserResp.newBuilder()
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
        getUserFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (userBuilder_ == null) {
        user_ = null;
      } else {
        userBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return practice.cafe.user.rpc.CafeUserServiceProto.internal_static_practice_cafe_user_GetCafeUserResp_descriptor;
    }

    @java.lang.Override
    public practice.cafe.user.rpc.GetCafeUserResp getDefaultInstanceForType() {
      return practice.cafe.user.rpc.GetCafeUserResp.getDefaultInstance();
    }

    @java.lang.Override
    public practice.cafe.user.rpc.GetCafeUserResp build() {
      practice.cafe.user.rpc.GetCafeUserResp result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public practice.cafe.user.rpc.GetCafeUserResp buildPartial() {
      practice.cafe.user.rpc.GetCafeUserResp result = new practice.cafe.user.rpc.GetCafeUserResp(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        if (userBuilder_ == null) {
          result.user_ = user_;
        } else {
          result.user_ = userBuilder_.build();
        }
        to_bitField0_ |= 0x00000001;
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof practice.cafe.user.rpc.GetCafeUserResp) {
        return mergeFrom((practice.cafe.user.rpc.GetCafeUserResp)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(practice.cafe.user.rpc.GetCafeUserResp other) {
      if (other == practice.cafe.user.rpc.GetCafeUserResp.getDefaultInstance()) return this;
      if (other.hasUser()) {
        mergeUser(other.getUser());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      practice.cafe.user.rpc.GetCafeUserResp parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (practice.cafe.user.rpc.GetCafeUserResp) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private practice.cafe.user.rpc.CafeUser user_;
    private com.google.protobuf.SingleFieldBuilderV3<
        practice.cafe.user.rpc.CafeUser, practice.cafe.user.rpc.CafeUser.Builder, practice.cafe.user.rpc.CafeUserOrBuilder> userBuilder_;
    /**
     * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
     * @return Whether the user field is set.
     */
    public boolean hasUser() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
     * @return The user.
     */
    public practice.cafe.user.rpc.CafeUser getUser() {
      if (userBuilder_ == null) {
        return user_ == null ? practice.cafe.user.rpc.CafeUser.getDefaultInstance() : user_;
      } else {
        return userBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
     */
    public Builder setUser(practice.cafe.user.rpc.CafeUser value) {
      if (userBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        user_ = value;
        onChanged();
      } else {
        userBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
     */
    public Builder setUser(
        practice.cafe.user.rpc.CafeUser.Builder builderForValue) {
      if (userBuilder_ == null) {
        user_ = builderForValue.build();
        onChanged();
      } else {
        userBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
     */
    public Builder mergeUser(practice.cafe.user.rpc.CafeUser value) {
      if (userBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
            user_ != null &&
            user_ != practice.cafe.user.rpc.CafeUser.getDefaultInstance()) {
          user_ =
            practice.cafe.user.rpc.CafeUser.newBuilder(user_).mergeFrom(value).buildPartial();
        } else {
          user_ = value;
        }
        onChanged();
      } else {
        userBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
     */
    public Builder clearUser() {
      if (userBuilder_ == null) {
        user_ = null;
        onChanged();
      } else {
        userBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
     */
    public practice.cafe.user.rpc.CafeUser.Builder getUserBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getUserFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
     */
    public practice.cafe.user.rpc.CafeUserOrBuilder getUserOrBuilder() {
      if (userBuilder_ != null) {
        return userBuilder_.getMessageOrBuilder();
      } else {
        return user_ == null ?
            practice.cafe.user.rpc.CafeUser.getDefaultInstance() : user_;
      }
    }
    /**
     * <code>optional .practice.cafe.user.CafeUser user = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        practice.cafe.user.rpc.CafeUser, practice.cafe.user.rpc.CafeUser.Builder, practice.cafe.user.rpc.CafeUserOrBuilder> 
        getUserFieldBuilder() {
      if (userBuilder_ == null) {
        userBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            practice.cafe.user.rpc.CafeUser, practice.cafe.user.rpc.CafeUser.Builder, practice.cafe.user.rpc.CafeUserOrBuilder>(
                getUser(),
                getParentForChildren(),
                isClean());
        user_ = null;
      }
      return userBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:practice.cafe.user.GetCafeUserResp)
  }

  // @@protoc_insertion_point(class_scope:practice.cafe.user.GetCafeUserResp)
  private static final practice.cafe.user.rpc.GetCafeUserResp DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new practice.cafe.user.rpc.GetCafeUserResp();
  }

  public static practice.cafe.user.rpc.GetCafeUserResp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetCafeUserResp>
      PARSER = new com.google.protobuf.AbstractParser<GetCafeUserResp>() {
    @java.lang.Override
    public GetCafeUserResp parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetCafeUserResp(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetCafeUserResp> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetCafeUserResp> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public practice.cafe.user.rpc.GetCafeUserResp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

