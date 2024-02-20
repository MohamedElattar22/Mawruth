package com.graduation.data.model.userinfo


import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.userinfo.UserInformationDto

data class UserInformation(

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("user_name")
    val userName: String? = null,

    @field:SerializedName("CreatedAt")
    val createdAt: String? = null,

    @field:SerializedName("ID")
    val iD: Int? = null,

    @field:SerializedName("avatar")
    val avatar: String? = null,

    @field:SerializedName("DeletedAt")
    val deletedAt: Any? = null,

    @field:SerializedName("UpdatedAt")
    val updatedAt: String? = null,

    @field:SerializedName("email")
    val email: String? = null
) {
    fun toUserInformationDto(): UserInformationDto {
        return UserInformationDto(
            password, fullName, userName, createdAt, iD, avatar, deletedAt, updatedAt, email
        )
    }
}