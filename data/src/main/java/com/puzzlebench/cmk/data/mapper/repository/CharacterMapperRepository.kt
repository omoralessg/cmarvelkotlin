package com.puzzlebench.cmk.data.mapper.repository

import com.puzzlebench.cmk.data.model.CharacterRealm
import com.puzzlebench.cmk.data.model.PropertyRealm
import com.puzzlebench.cmk.data.model.ThumbnailRealm
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.model.Property
import com.puzzlebench.cmk.domain.model.Thumbnail

class CharacterMapperRepository : BaseMapperRepository<Character, CharacterRealm> {

    override fun transform(input: CharacterRealm): Character = Character(input.id!!, input.name!!, input.description!!, transformToThumbnail(input.thumbnail!!), input.resourceURI!!, transformToProperty(input.comics!!))

    override fun transform(input: Character): CharacterRealm = CharacterRealm(input.id, input.name, input.description, transformToThumbnailRealm(input.thumbnail), input.resourceURI, transformToPropertyRealm(input.comics))

    private fun transformToThumbnail(thumbnailRealm: ThumbnailRealm): Thumbnail = Thumbnail(thumbnailRealm.path!!, thumbnailRealm.extension!!)

    private fun transformToProperty(propertyRealm: PropertyRealm): Property = Property(propertyRealm.available!!, propertyRealm.returned!!, propertyRealm.collectionURI!!)

    private fun transformToThumbnailRealm(thumbnail: Thumbnail): ThumbnailRealm = ThumbnailRealm(thumbnail.path, thumbnail.extension)

    private fun transformToPropertyRealm(property: Property) : PropertyRealm = PropertyRealm(property.available, property.returned, property.collectionURI)
}
