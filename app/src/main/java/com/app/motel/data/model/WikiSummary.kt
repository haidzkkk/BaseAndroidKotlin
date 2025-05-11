package com.app.motel.data.model

import com.google.gson.annotations.SerializedName

data class WikiSummary(
    @SerializedName("type") val type: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("displaytitle") val displayTitle: String?,
    @SerializedName("namespace") val namespace: Namespace?,
    @SerializedName("wikibase_item") val wikiBaseItem: String?,
    @SerializedName("titles") val titles: Titles?,
    @SerializedName("pageid") val pageId: Int?,
    @SerializedName("thumbnail") val thumbnail: ImageInfo?,
    @SerializedName("originalimage") val originalImage: ImageInfo?,
    @SerializedName("lang") val lang: String?,
    @SerializedName("dir") val dir: String?,
    @SerializedName("revision") val revision: String?,
    @SerializedName("tid") val tid: String?,
    @SerializedName("timestamp") val timestamp: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("description_source") val descriptionSource: String?,
    @SerializedName("content_urls") val contentUrls: ContentUrls?,
    @SerializedName("extract") val extract: String?,
    @SerializedName("extract_html") val extractHtml: String?
)

data class Namespace(
    @SerializedName("id") val id: Int?,
    @SerializedName("text") val text: String?
)

data class Titles(
    @SerializedName("canonical") val canonical: String?,
    @SerializedName("normalized") val normalized: String?,
    @SerializedName("display") val display: String?
)

data class ImageInfo(
    @SerializedName("source") val source: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?
)

data class ContentUrls(
    @SerializedName("desktop") val desktop: PageUrls?,
    @SerializedName("mobile") val mobile: PageUrls?
)

data class PageUrls(
    @SerializedName("page") val page: String?,
    @SerializedName("revisions") val revisions: String?,
    @SerializedName("edit") val edit: String?,
    @SerializedName("talk") val talk: String?
)
