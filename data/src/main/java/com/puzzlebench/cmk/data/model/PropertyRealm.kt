package com.puzzlebench.cmk.data.model

import io.realm.RealmObject

open class PropertyRealm (
        var available: Int? = null,
        var returned: Int? = null,
        var collectionURI: String? = null
): RealmObject()


