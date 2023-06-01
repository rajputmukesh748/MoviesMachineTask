package com.mukesh.machinetask.presentation.viewModel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukesh.machinetask.data.remote.ResponseDto
import com.mukesh.machinetask.domain.usecase.updateDb.UpdateDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val updateDbUseCase: UpdateDbUseCase
) : ViewModel() {


    /**
     * Update DB
     * */
    private val _updateDb by lazy { MutableSharedFlow<String?>() }
    val updateDb get() = _updateDb.asSharedFlow()

    fun updateDb(responseDto: ResponseDto) =
        updateDbUseCase(responseDto = responseDto).onEach { result ->
            _updateDb.emit(result)
        }.launchIn(viewModelScope)


}