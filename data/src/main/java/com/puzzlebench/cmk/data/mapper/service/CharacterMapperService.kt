package com.puzzlebench.cmk.data.mapper.service

import com.puzzlebench.cmk.data.service.response.CharacterResponse
import com.puzzlebench.cmk.data.service.response.PropertyResponse
import com.puzzlebench.cmk.data.service.response.ThumbnailResponse
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.model.Property
import com.puzzlebench.cmk.domain.model.Thumbnail


class CharacterMapperService : BaseMapperService<CharacterResponse, Character> {

    override fun transform(characterResponse: CharacterResponse): Character
            = Character(
            characterResponse.id,
            characterResponse.name,
            characterResponse.description,
            transformToThumbnail(characterResponse.thumbnail),
            characterResponse.resourceURI,
            transformToProperty(characterResponse.comics)
    )

    override fun transformToResponse(type: Character): CharacterResponse
            = CharacterResponse(
            type.id,
            type.name,
            type.description,
            transformToThumbnailResponse(type.thumbnail),
            type.resourceURI,
            transformToPropertyResponse(type.comics)

    )

    fun transformToThumbnail(thumbnailResponse: ThumbnailResponse): Thumbnail
            = Thumbnail(
            thumbnailResponse.path,
            thumbnailResponse.extension
    )

    fun transformToProperty(propertyResponse: PropertyResponse): Property
    = Property(
            propertyResponse.available,
            propertyResponse.returned,
            propertyResponse.collectionURI
    )

    fun transformToThumbnailResponse(thumbnail: Thumbnail): ThumbnailResponse
            = ThumbnailResponse(
            thumbnail.path,
            thumbnail.extension
    )

    fun transformToPropertyResponse(property: Property): PropertyResponse
        = PropertyResponse(
            property.available,
            property.returned,
            property.collectionURI
    )

    fun transform(charactersResponse: List<CharacterResponse>): List<Character>
            = charactersResponse.map { transform(it) }

}