package com.example.gaenari.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gaenari.dto.response.FavoriteResponseDto
import com.example.gaenari.dto.response.ProgramTypeInfoDto

class SharedViewModel : ViewModel() {
    private val _favoritePrograms = MutableLiveData<List<FavoriteResponseDto>>()
    val favoritePrograms: LiveData<List<FavoriteResponseDto>> = _favoritePrograms

    init {
        // 기본 데이터 설정
        val defaultData = listOf(
            FavoriteResponseDto(
                programId = 0L, // ID는 임의로 0으로 설정, 실제 환경에 따라 적절히 조정 필요
                programTitle = "달리기",
                usageCount = 0,
                finishedCount = 0,
                type = "R", // 러닝
                program = ProgramTypeInfoDto(targetValue = null, intervalInfo = null)
            ),
            FavoriteResponseDto(
                programId = 0L,
                programTitle = "걷기",
                usageCount = 0,
                finishedCount = 0,
                type = "W", // 워킹
                program = ProgramTypeInfoDto(targetValue = null, intervalInfo = null)
            )
        )
        _favoritePrograms.value = defaultData
    }

    fun setFavoritePrograms(data: List<FavoriteResponseDto?>) {
        // 기본 데이터 설정
        val defaultData = listOf(
            FavoriteResponseDto(
                programId = 0L, // ID는 임의로 0으로 설정, 실제 환경에 따라 적절히 조정 필요
                programTitle = "달리기",
                usageCount = 0,
                finishedCount = 0,
                type = "R", // 러닝
                program = ProgramTypeInfoDto(targetValue = null, intervalInfo = null)
            ),
            FavoriteResponseDto(
                programId = 0L,
                programTitle = "걷기",
                usageCount = 0,
                finishedCount = 0,
                type = "W", // 워킹
                program = ProgramTypeInfoDto(targetValue = null, intervalInfo = null)
            )
        )

        // 새로운 데이터를 설정하되, 기본 데이터와 합침
        _favoritePrograms.value = defaultData + data.filterNotNull()
    }
}
