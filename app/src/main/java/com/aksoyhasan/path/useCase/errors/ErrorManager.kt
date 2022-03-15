package com.aksoyhasan.path.useCase.errors

import com.aksoyhasan.path.data.error.MovieError
import com.aksoyhasan.path.data.error.mapper.ErrorMapper
import javax.inject.Inject

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): MovieError {
        return MovieError(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
