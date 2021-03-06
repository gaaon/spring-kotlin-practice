// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rpc/user.proto

package practice.cafe.user.rpc;

public final class CafeUserServiceProto {
  private CafeUserServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_practice_cafe_user_CafeUser_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_practice_cafe_user_CafeUser_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_practice_cafe_user_ListCafeUsersReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_practice_cafe_user_ListCafeUsersReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_practice_cafe_user_ListCafeUsersResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_practice_cafe_user_ListCafeUsersResp_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_practice_cafe_user_GetCafeUserReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_practice_cafe_user_GetCafeUserReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_practice_cafe_user_GetCafeUserResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_practice_cafe_user_GetCafeUserResp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016rpc/user.proto\022\022practice.cafe.user\";\n\010" +
      "CafeUser\022\n\n\002id\030\001 \001(\003\022\020\n\010nickname\030\002 \001(\t\022\021" +
      "\n\tavatarUrl\030\003 \001(\t\"$\n\020ListCafeUsersReq\022\020\n" +
      "\010user_ids\030\001 \003(\003\"@\n\021ListCafeUsersResp\022+\n\005" +
      "users\030\001 \003(\0132\034.practice.cafe.user.CafeUse" +
      "r\"!\n\016GetCafeUserReq\022\017\n\007user_id\030\001 \001(\003\"K\n\017" +
      "GetCafeUserResp\022/\n\004user\030\001 \001(\0132\034.practice" +
      ".cafe.user.CafeUserH\000\210\001\001B\007\n\005_user2\313\001\n\017Ca" +
      "feUserService\022^\n\rListCafeUsers\022$.practic" +
      "e.cafe.user.ListCafeUsersReq\032%.practice." +
      "cafe.user.ListCafeUsersResp\"\000\022X\n\013GetCafe" +
      "User\022\".practice.cafe.user.GetCafeUserReq" +
      "\032#.practice.cafe.user.GetCafeUserResp\"\000B" +
      "0\n\026practice.cafe.user.rpcB\024CafeUserServi" +
      "ceProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_practice_cafe_user_CafeUser_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_practice_cafe_user_CafeUser_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_practice_cafe_user_CafeUser_descriptor,
        new java.lang.String[] { "Id", "Nickname", "AvatarUrl", });
    internal_static_practice_cafe_user_ListCafeUsersReq_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_practice_cafe_user_ListCafeUsersReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_practice_cafe_user_ListCafeUsersReq_descriptor,
        new java.lang.String[] { "UserIds", });
    internal_static_practice_cafe_user_ListCafeUsersResp_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_practice_cafe_user_ListCafeUsersResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_practice_cafe_user_ListCafeUsersResp_descriptor,
        new java.lang.String[] { "Users", });
    internal_static_practice_cafe_user_GetCafeUserReq_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_practice_cafe_user_GetCafeUserReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_practice_cafe_user_GetCafeUserReq_descriptor,
        new java.lang.String[] { "UserId", });
    internal_static_practice_cafe_user_GetCafeUserResp_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_practice_cafe_user_GetCafeUserResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_practice_cafe_user_GetCafeUserResp_descriptor,
        new java.lang.String[] { "User", "User", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
