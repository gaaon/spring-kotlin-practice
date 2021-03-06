//Generated by the protocol buffer compiler. DO NOT EDIT!
// source: rpc/user.proto

package practice.cafe.user.rpc;

@kotlin.jvm.JvmSynthetic
public inline fun listCafeUsersResp(block: practice.cafe.user.rpc.ListCafeUsersRespKt.Dsl.() -> kotlin.Unit): practice.cafe.user.rpc.ListCafeUsersResp =
  practice.cafe.user.rpc.ListCafeUsersRespKt.Dsl._create(practice.cafe.user.rpc.ListCafeUsersResp.newBuilder()).apply { block() }._build()
public object ListCafeUsersRespKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: practice.cafe.user.rpc.ListCafeUsersResp.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: practice.cafe.user.rpc.ListCafeUsersResp.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): practice.cafe.user.rpc.ListCafeUsersResp = _builder.build()

    /**
     * An uninstantiable, behaviorless type to represent the field in
     * generics.
     */
    @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
    public class UsersProxy private constructor() : com.google.protobuf.kotlin.DslProxy()
    /**
     * <code>repeated .practice.cafe.user.CafeUser users = 1;</code>
     */
     public val users: com.google.protobuf.kotlin.DslList<practice.cafe.user.rpc.CafeUser, UsersProxy>
      @kotlin.jvm.JvmSynthetic
      get() = com.google.protobuf.kotlin.DslList(
        _builder.getUsersList()
      )
    /**
     * <code>repeated .practice.cafe.user.CafeUser users = 1;</code>
     * @param value The users to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addUsers")
    public fun com.google.protobuf.kotlin.DslList<practice.cafe.user.rpc.CafeUser, UsersProxy>.add(value: practice.cafe.user.rpc.CafeUser) {
      _builder.addUsers(value)
    }/**
     * <code>repeated .practice.cafe.user.CafeUser users = 1;</code>
     * @param value The users to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignUsers")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<practice.cafe.user.rpc.CafeUser, UsersProxy>.plusAssign(value: practice.cafe.user.rpc.CafeUser) {
      add(value)
    }/**
     * <code>repeated .practice.cafe.user.CafeUser users = 1;</code>
     * @param values The users to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addAllUsers")
    public fun com.google.protobuf.kotlin.DslList<practice.cafe.user.rpc.CafeUser, UsersProxy>.addAll(values: kotlin.collections.Iterable<practice.cafe.user.rpc.CafeUser>) {
      _builder.addAllUsers(values)
    }/**
     * <code>repeated .practice.cafe.user.CafeUser users = 1;</code>
     * @param values The users to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignAllUsers")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<practice.cafe.user.rpc.CafeUser, UsersProxy>.plusAssign(values: kotlin.collections.Iterable<practice.cafe.user.rpc.CafeUser>) {
      addAll(values)
    }/**
     * <code>repeated .practice.cafe.user.CafeUser users = 1;</code>
     * @param index The index to set the value at.
     * @param value The users to set.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("setUsers")
    public operator fun com.google.protobuf.kotlin.DslList<practice.cafe.user.rpc.CafeUser, UsersProxy>.set(index: kotlin.Int, value: practice.cafe.user.rpc.CafeUser) {
      _builder.setUsers(index, value)
    }/**
     * <code>repeated .practice.cafe.user.CafeUser users = 1;</code>
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("clearUsers")
    public fun com.google.protobuf.kotlin.DslList<practice.cafe.user.rpc.CafeUser, UsersProxy>.clear() {
      _builder.clearUsers()
    }}
}
@kotlin.jvm.JvmSynthetic
public inline fun practice.cafe.user.rpc.ListCafeUsersResp.copy(block: practice.cafe.user.rpc.ListCafeUsersRespKt.Dsl.() -> kotlin.Unit): practice.cafe.user.rpc.ListCafeUsersResp =
  practice.cafe.user.rpc.ListCafeUsersRespKt.Dsl._create(this.toBuilder()).apply { block() }._build()
