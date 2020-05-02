package ru.paswd.infosearch.wikidocumentsetscanner.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DataSet (

    @JsonProperty("total")
    var total: Long?,

    @JsonProperty("data")
    var data: List<Document>?
)