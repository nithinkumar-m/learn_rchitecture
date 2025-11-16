package com.ottapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Home page response
 */
data class HomeResponse(
    @SerializedName("collections")
    val collections: List<ContentCollection>
)

/**
 * Content collection with template type
 */
data class ContentCollection(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("template")
    val template: TemplateType,
    
    @SerializedName("items")
    val items: List<ContentItem>
)

/**
 * Template types for rendering
 */
enum class TemplateType {
    @SerializedName("hero_carousel")
    HERO_CAROUSEL,
    
    @SerializedName("horizontal_list")
    HORIZONTAL_LIST,
    
    @SerializedName("vertical_grid")
    VERTICAL_GRID,
    
    @SerializedName("featured_banner")
    FEATURED_BANNER
}

/**
 * Individual content item
 */
data class ContentItem(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("description")
    val description: String? = null,
    
    @SerializedName("imageUrl")
    val imageUrl: String,
    
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String? = null,
    
    @SerializedName("type")
    val type: ContentType,
    
    @SerializedName("duration")
    val duration: String? = null,
    
    @SerializedName("rating")
    val rating: Double? = null,
    
    @SerializedName("year")
    val year: Int? = null,
    
    @SerializedName("genres")
    val genres: List<String>? = null
)

/**
 * Content types
 */
enum class ContentType {
    @SerializedName("movie")
    MOVIE,
    
    @SerializedName("series")
    SERIES,
    
    @SerializedName("video")
    VIDEO,
    
    @SerializedName("live")
    LIVE
}
