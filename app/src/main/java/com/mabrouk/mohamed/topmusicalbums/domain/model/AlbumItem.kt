package com.mabrouk.mohamed.topmusicalbums.domain.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class AlbumItem : RealmObject {
    var id: Long? = 0
    var name: String? = ""
    var artistName: String? = ""
    var albumImageUrl: String? = ""
    var genres: RealmList<String> = realmListOf()
    var releaseDate: String? = ""
    var isExplicit: Boolean = false
    var albumUrl: String? = ""
}