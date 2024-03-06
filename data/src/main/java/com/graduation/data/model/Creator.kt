package com.graduation.data.model

import com.google.gson.annotations.SerializedName
import com.graduation.domain.model.CreatorDto

data class Creator(

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
    fun toCreatorDto(): CreatorDto {
        return CreatorDto(
            password = password,
            fullName = fullName,
            userName = userName,
            iD = iD,
            avatar = avatar,
            email = email
        )
    }
//    companion object{
//        fun toCreator(creatorDto: CreatorDto):Creator
//        {
//            return Creator(
//                creatorDto.password,
//                creatorDto.fullName,
//                creatorDto.userName,
//                creatorDto.createdAt,
//                creatorDto.iD,
//                creatorDto.avatar,
//                creatorDto.deletedAt,
//                creatorDto,
//            )
//        }
//    }
}