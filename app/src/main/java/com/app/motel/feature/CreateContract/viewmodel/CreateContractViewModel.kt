package com.app.motel.feature.CreateContract.viewmodel

import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.repository.CreateContractRepository
import javax.inject.Inject

class CreateContractViewModel @Inject constructor(
    private val repository: CreateContractRepository
): AppBaseViewModel<CreateContractViewState, CreateContractViewAction, CreateContractViewEvent>(
    CreateContractViewState()
) {
    override fun handle(action: CreateContractViewAction) {
        TODO("Not yet implemented")
    }
}