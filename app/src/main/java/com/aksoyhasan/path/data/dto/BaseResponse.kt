package com.aksoyhasan.path.data.dto

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class BaseResponse<T>(
    var `data`: T? = null,
    val attributionHTML: String? = null, // <a href="http://marvel.com">Data provided by Marvel. © 2022 MARVEL</a>
    val attributionText: String? = null, // Data provided by Marvel. © 2022 MARVEL
    val code: Int? = null, // 200
    val copyright: String? = null, // © 2022 MARVEL
    val etag: String? = null, // 63813fcc89c2bbd6030470b9d1e1ea29b62e5679
    val status: String? = null // Ok
) : Serializable