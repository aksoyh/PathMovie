package com.aksoyhasan.path.useCase.errors

import com.aksoyhasan.path.data.error.MovieError

interface ErrorUseCase {
    fun getError(errorCode: Int): MovieError
}
