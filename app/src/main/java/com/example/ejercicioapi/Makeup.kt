package com.example.ejercicioapi

import com.google.gson.annotations.SerializedName

data class Makeup (
    var brand: String = "",
    var name: String = "",
    var price: Double = 0.0,
    @SerializedName ("price_sign")var priceSign: String? = "",
    var currency: String? = "",
    @SerializedName("image_link")var imageLink: String = "",
    @SerializedName("product_link") var productLink: String = "",
    @SerializedName("website_link") var websiteLink: String = "",
    var description: String = "",
    var rating: Double = 0.0,
    var category: String? = "",
    @SerializedName("product_type") var productType: String = "",
    @SerializedName("created_at") var createdAt: String = "",
    @SerializedName("updated_at") var updatedAt: String = "",
    @SerializedName("product_api_url") var productApiUrl: String = "",
    @SerializedName("api_featured_image") var apiFeaturedImage: String = ""
    ) {

    override fun toString(): String {
        return "MAQUILLAJE: $name \n\nDESCRIPCIÓN: $description \n\nPRECIO: $price€"
    }

    constructor(): this (
            "", "", 0.0, "", "", "","", "", "",
            0.0, "", "", "", "", "", ""
            )
}
