// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public  final class SetDefaultChannelPipelineRequest extends
    com.google.protobuf.GeneratedMessage {
  // Use SetDefaultChannelPipelineRequest.newBuilder() to construct.
  private SetDefaultChannelPipelineRequest() {
    initFields();
  }
  private SetDefaultChannelPipelineRequest(boolean noInit) {}
  
  private static final SetDefaultChannelPipelineRequest defaultInstance;
  public static SetDefaultChannelPipelineRequest getDefaultInstance() {
    return defaultInstance;
  }
  
  public SetDefaultChannelPipelineRequest getDefaultInstanceForType() {
    return defaultInstance;
  }
  
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_SetDefaultChannelPipelineRequest_descriptor;
  }
  
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_SetDefaultChannelPipelineRequest_fieldAccessorTable;
  }
  
  private void initFields() {
  }
  public final boolean isInitialized() {
    return true;
  }
  
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    getUnknownFields().writeTo(output);
  }
  
  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;
  
    size = 0;
    size += getUnknownFields().getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }
  
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    Builder builder = newBuilder();
    if (builder.mergeDelimitedFrom(input)) {
      return builder.buildParsed();
    } else {
      return null;
    }
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    Builder builder = newBuilder();
    if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
      return builder.buildParsed();
    } else {
      return null;
    }
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  
  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest prototype) {
    return newBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() { return newBuilder(this); }
  
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> {
    private de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest result;
    
    // Construct using de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest.newBuilder()
    private Builder() {}
    
    private static Builder create() {
      Builder builder = new Builder();
      builder.result = new de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest();
      return builder;
    }
    
    protected de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest internalGetResult() {
      return result;
    }
    
    public Builder clear() {
      if (result == null) {
        throw new IllegalStateException(
          "Cannot call clear() after build().");
      }
      result = new de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest();
      return this;
    }
    
    public Builder clone() {
      return create().mergeFrom(result);
    }
    
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest.getDescriptor();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest getDefaultInstanceForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest.getDefaultInstance();
    }
    
    public boolean isInitialized() {
      return result.isInitialized();
    }
    public de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest build() {
      if (result != null && !isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return buildPartial();
    }
    
    private de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest buildParsed()
        throws com.google.protobuf.InvalidProtocolBufferException {
      if (!isInitialized()) {
        throw newUninitializedMessageException(
          result).asInvalidProtocolBufferException();
      }
      return buildPartial();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest buildPartial() {
      if (result == null) {
        throw new IllegalStateException(
          "build() has already been called on this Builder.");
      }
      de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest returnMe = result;
      result = null;
      return returnMe;
    }
    
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest) {
        return mergeFrom((de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }
    
    public Builder mergeFrom(de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest other) {
      if (other == de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest.getDefaultInstance()) return this;
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }
    
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder(
          this.getUnknownFields());
      while (true) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            this.setUnknownFields(unknownFields.build());
            return this;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              this.setUnknownFields(unknownFields.build());
              return this;
            }
            break;
          }
        }
      }
    }
    
    
    // @@protoc_insertion_point(builder_scope:de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest)
  }
  
  static {
    defaultInstance = new SetDefaultChannelPipelineRequest(true);
    de.uniluebeck.itm.tr.iwsn.messages.Messages.internalForceInit();
    defaultInstance.initFields();
  }
  
  // @@protoc_insertion_point(class_scope:de.uniluebeck.itm.tr.iwsn.messages.SetDefaultChannelPipelineRequest)
}
