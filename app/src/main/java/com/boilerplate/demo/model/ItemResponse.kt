package com.boilerplate.demo.model

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.boilerplate.demo.base.auth.response.BaseApiResponse
import com.google.gson.annotations.SerializedName

data class ItemEntity(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String?,

    @SerializedName("image_url")
    var image: String? = null
) : BaseApiResponse()


class ItemResponse(
    var id: Int = 0,
    var isSelected: ObservableField<Boolean> = ObservableField(false),
    var name: String?,
    var image: String? = null
) : BaseApiResponse()


fun ItemEntity.map(): ItemResponse {
    return ItemResponse(id = this.id, name = this.name, image = this.image)

}




