package ru.paswd.infosearch.wikidocumentsetscanner.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Document (

    @JsonProperty("title")
    var title: String?,

    @JsonProperty("pageId")
    var pageId: Long?,

    @JsonProperty("text")
    var text: String?
)