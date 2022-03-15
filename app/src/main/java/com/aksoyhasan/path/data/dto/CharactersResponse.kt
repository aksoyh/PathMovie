package com.aksoyhasan.path.data.dto

import androidx.annotation.Keep

@Keep
data class CharactersResponse(
    val count: Int? = null, // 10
    val limit: Int? = null, // 10
    val offset: Int? = null, // 1
    val results: List<Result?>? = null,
    val total: Int? = null // 1560
) {
    @Keep
    data class Result(
        val comics: Comics? = null,
        val description: String? = null, // Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!
        val events: Events? = null,
        val id: Int? = null, // 1017100
        val modified: String? = null, // 2013-09-18T15:54:04-0400
        val name: String? = null, // A-Bomb (HAS)
        val resourceURI: String? = null, // http://gateway.marvel.com/v1/public/characters/1017100
        val series: Series? = null,
        val stories: Stories? = null,
        val thumbnail: Thumbnail? = null,
        val urls: List<Url?>? = null
    ) {
        @Keep
        data class Comics(
            val available: Int? = null, // 4
            val collectionURI: String? = null, // http://gateway.marvel.com/v1/public/characters/1017100/comics
            val items: List<Item?>? = null,
            val returned: Int? = null // 4
        ) {
            @Keep
            data class Item(
                val name: String? = null, // FREE COMIC BOOK DAY 2013 1 (2013) #1
                val resourceURI: String? = null // http://gateway.marvel.com/v1/public/comics/47176
            )
        }

        @Keep
        data class Events(
            val available: Int? = null, // 0
            val collectionURI: String? = null, // http://gateway.marvel.com/v1/public/characters/1017100/events
            val items: List<Item?>? = null,
            val returned: Int? = null // 0
        ) {
            @Keep
            data class Item(
                val name: String? = null, // Chaos War
                val resourceURI: String? = null // http://gateway.marvel.com/v1/public/events/296
            )
        }

        @Keep
        data class Series(
            val available: Int? = null, // 2
            val collectionURI: String? = null, // http://gateway.marvel.com/v1/public/characters/1017100/series
            val items: List<Item?>? = null,
            val returned: Int? = null // 2
        ) {
            @Keep
            data class Item(
                val name: String? = null, // FREE COMIC BOOK DAY 2013 1 (2013)
                val resourceURI: String? = null // http://gateway.marvel.com/v1/public/series/17765
            )
        }

        @Keep
        data class Stories(
            val available: Int? = null, // 7
            val collectionURI: String? = null, // http://gateway.marvel.com/v1/public/characters/1017100/stories
            val items: List<Item?>? = null,
            val returned: Int? = null // 7
        ) {
            @Keep
            data class Item(
                val name: String? = null, // Hulk (2008) #55
                val resourceURI: String? = null, // http://gateway.marvel.com/v1/public/stories/92078
                val type: String? = null // cover
            )
        }

        @Keep
        data class Thumbnail(
            val extension: String? = null, // jpg
            val path: String? = null // http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16
        )

        @Keep
        data class Url(
            val type: String? = null, // detail
            val url: String? = null // http://marvel.com/characters/76/a-bomb?utm_campaign=apiRef&utm_source=4de8bc105b313fdab571be15ff3b72b4
        )
    }
}