package com.mabrouk.mohamed.topmusicalbums.data.local.database

import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

class RealmDataBase {

    private val realm: Realm by lazy {
        val configuration = RealmConfiguration.create(schema = setOf(AlbumItem::class))
        Realm.open(configuration)
    }

    fun getAlbums(): List<AlbumItem> {
        return realm.query<AlbumItem>().find()
    }

    fun saveAlbums(albums: List<AlbumItem>) {
        realm.writeBlocking {
            albums.forEach {
                copyToRealm(it)
            }
        }
    }
}