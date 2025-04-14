package com.example.bookexpert.retrofit

import retrofit2.http.GET
import com.google.gson.annotations.SerializedName


data class Product(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("data")
    val data: ProductData? = null
)

data class ProductData(
    @SerializedName("color")
    val smallColor: String? = null,

    @SerializedName("Color")
    val capitalColor: String? = null,

    @SerializedName("capacity")
    val capacity: String? = null,

    @SerializedName("capacity GB")
    val capacityGB: Int? = null,

    @SerializedName("price")
    val price: Double? = null,

    @SerializedName("generation")
    val generation: String? = null,

    @SerializedName("year")
    val year: Int? = null,

    @SerializedName("CPU model")
    val cpuModel: String? = null,

    @SerializedName("Hard disk size")
    val hardDiskSize: String? = null,

    @SerializedName("Strap Colour")
    val strapColour: String? = null,

    @SerializedName("Case Size")
    val caseSize: String? = null,

    @SerializedName("Description")
    val description: String? = null,

    @SerializedName("Capacity")
    val capitalCapacity: String? = null,

    @SerializedName("Screen size")
    val screenSize: Double? = null,

    @SerializedName("Generation")
    val capitalGeneration: String? = null,

    @SerializedName("Price")
    val capitalPrice: String? = null
)


interface ApiService {

    @GET("objects")
    suspend fun getList() : ArrayList<Product>

}