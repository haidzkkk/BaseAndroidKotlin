package com.app.motel.feature.handleBill.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.model.Bill
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Tenant
import com.app.motel.data.repository.BillRepository
import com.app.motel.data.repository.ContractRepository
import com.app.motel.data.repository.TenantRepository
import com.app.motel.feature.profile.UserController
import kotlinx.coroutines.launch
import javax.inject.Inject

class HandleBillViewModel @Inject constructor(
    private val billRepository: BillRepository,
    private val contractRepository: ContractRepository,
    private val tenantRepository: TenantRepository,
    private val userController: UserController
): AppBaseViewModel<HandleBillState, HandleBillAction, HandleBillEvent>(HandleBillState()) {
    override fun handle(action: HandleBillAction) {

    }

    fun getBills(){
        liveData.bills.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val boardingHouseId = userController.state.currentBoardingHouseId
                val bills = billRepository.getBillByBoardingHouseId(boardingHouseId)
                liveData.bills.postValue(Resource.Success(bills))
            }catch (e: Exception){
                liveData.bills.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun initForm(bill: Bill){
        viewModelScope.launch {
            val contract: Contract? = contractRepository.getContractActiveByRoomId(bill.roomId ?: "")
            val tenant: Tenant? = tenantRepository.getTenantsById(contract?.customerId ?: "")
            bill.tenant = tenant
            liveData.currentBill.postValue(bill)
        }
    }

}