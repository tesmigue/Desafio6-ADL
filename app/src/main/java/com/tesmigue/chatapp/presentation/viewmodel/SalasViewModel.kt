package com.tesmigue.chatapp.presentation.viewmodel

import androidx.lifecycle.*
import com.tesmigue.chatapp.domain.model.Sala
import com.tesmigue.chatapp.domain.usecase.ObtenerSalasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalasViewModel @Inject constructor(
    private val obtenerSalasUseCase: ObtenerSalasUseCase
) : ViewModel() {

    private val _salas = MutableLiveData<List<Sala>>()
    val salas: LiveData<List<Sala>> = _salas

    init {
        cargarSalas()
    }

    private fun cargarSalas() {
        viewModelScope.launch {
            _salas.value = obtenerSalasUseCase()
        }
    }
}
