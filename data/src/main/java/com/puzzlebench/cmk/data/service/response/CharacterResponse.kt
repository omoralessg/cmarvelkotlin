package com.puzzlebench.cmk.data.service.response

import com.puzzlebench.cmk.domain.model.Property

class CharacterResponse (
        val id: Int,
        val name: String,
        val description: String,
        val thumbnail: ThumbnailResponse,
        val resourceURI : String,
        val comics : PropertyResponse
)