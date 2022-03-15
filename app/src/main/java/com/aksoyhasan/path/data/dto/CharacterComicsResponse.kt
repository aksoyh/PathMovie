package com.aksoyhasan.path.data.dto

data class CharacterComicsResponse(
    val count: Int? = null, // 10
    val limit: Int? = null, // 10
    val offset: Int? = null, // 0
    val results: List<Result?>? = null,
    val total: Int? = null // 52
) {
    data class Result(
        val characters: Characters? = null,
        val collectedIssues: List<CollectedIssue?>? = null,
        val collections: List<Collection?>? = null,
        val creators: Creators? = null,
        val dates: List<Date?>? = null,
        val description: Any? = null, // null
        val diamondCode: String? = null,
        val digitalId: Int? = null, // 3736
        val ean: String? = null,
        val events: Events? = null,
        val format: String? = null, // Comic
        val id: Int? = null, // 11328
        val images: List<Image?>? = null,
        val isbn: String? = null,
        val issn: String? = null,
        val issueNumber: Int? = null, // 82
        val modified: String? = null, // 2020-01-31T09:05:45-0500
        val pageCount: Int? = null, // 0
        val prices: List<Price?>? = null,
        val resourceURI: String? = null, // http://gateway.marvel.com/v1/public/comics/11328
        val series: Series? = null,
        val stories: Stories? = null,
        val textObjects: List<TextObject?>? = null,
        val thumbnail: Thumbnail? = null,
        val title: String? = null, // Tales of Suspense (1959) #82
        val upc: String? = null,
        val urls: List<Url?>? = null,
        val variantDescription: String? = null,
        val variants: List<Any?>? = null
    ) {
        data class Characters(
            val available: Int? = null, // 6
            val collectionURI: String? = null, // http://gateway.marvel.com/v1/public/comics/11328/characters
            val items: List<Item?>? = null,
            val returned: Int? = null // 6
        ) {
            data class Item(
                val name: String? = null, // A.I.M.
                val resourceURI: String? = null // http://gateway.marvel.com/v1/public/characters/1009144
            )
        }

        data class CollectedIssue(
            val name: String? = null, // Avengers (1998) #70
            val resourceURI: String? = null // http://gateway.marvel.com/v1/public/comics/324
        )

        data class Collection(
            val name: String? = null, // Marvel Masterworks: The Invincible Iron Man Vol. 1 (Hardcover)
            val resourceURI: String? = null // http://gateway.marvel.com/v1/public/comics/17468
        )

        data class Creators(
            val available: Int? = null, // 5
            val collectionURI: String? = null, // http://gateway.marvel.com/v1/public/comics/11328/creators
            val items: List<Item?>? = null,
            val returned: Int? = null // 5
        ) {
            data class Item(
                val name: String? = null, // Gene Colan
                val resourceURI: String? = null, // http://gateway.marvel.com/v1/public/creators/270
                val role: String? = null // penciler
            )
        }

        data class Date(
            val date: String? = null, // 1966-10-01T00:00:00-0400
            val type: String? = null // onsaleDate
        )

        data class Events(
            val available: Int? = null, // 0
            val collectionURI: String? = null, // http://gateway.marvel.com/v1/public/comics/11328/events
            val items: List<Any?>? = null,
            val returned: Int? = null // 0
        )

        data class Image(
            val extension: String? = null, // jpg
            val path: String? = null // http://i.annihil.us/u/prod/marvel/i/mg/6/60/5e42ff261d723
        )

        data class Price(
            val price: Int? = null, // 0
            val type: String? = null // printPrice
        )

        data class Series(
            val name: String? = null, // Tales of Suspense (1959 - 1968)
            val resourceURI: String? = null // http://gateway.marvel.com/v1/public/series/2079
        )

        data class Stories(
            val available: Int? = null, // 5
            val collectionURI: String? = null, // http://gateway.marvel.com/v1/public/comics/11328/stories
            val items: List<Item?>? = null,
            val returned: Int? = null // 5
        ) {
            data class Item(
                val name: String? = null, // Cover #11934
                val resourceURI: String? = null, // http://gateway.marvel.com/v1/public/stories/11934
                val type: String? = null // cover
            )
        }

        data class TextObject(
            val language: String? = null, // en-us
            val text: String? = null, // Biohazard! In a post-9/11 world, the very word strikes fear in the heart of people around the globe! And now, a deadly chemical agent has been released over Mount Rushmore, rapidly spreading through the surrounding countryside! Is this a terrorist attack on American soil? That's what the Avengers seek to uncover as they brave the lethal perimeter of the Red Zone - but the truth they uncover is more startling and terrifying than even that!
            val type: String? = null // issue_solicit_text
        )

        data class Thumbnail(
            val extension: String? = null, // jpg
            val path: String? = null // http://i.annihil.us/u/prod/marvel/i/mg/6/60/5e42ff261d723
        )

        data class Url(
            val type: String? = null, // detail
            val url: String? = null // http://marvel.com/comics/issue/11328/tales_of_suspense_1959_82?utm_campaign=apiRef&utm_source=4de8bc105b313fdab571be15ff3b72b4
        )
    }
}