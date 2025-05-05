package com.app.motel.data.repository

import com.app.motel.data.local.BoardingHouseDAO
import com.app.motel.data.local.RulesDAO
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Rules
import javax.inject.Inject

class RulesRepository @Inject constructor(
    private val rulesDAO: RulesDAO,
    private val boardingHouseDAO: BoardingHouseDAO,
){
    suspend fun getRulesByBoardingHouseId(boardingHouseId: String): List<Rules> {
        val rulesEntities = rulesDAO.getRegulationsByKhuTro(boardingHouseId)
        return rulesEntities.map { it.toModel() } as ArrayList<Rules>
    }

    suspend fun getRulesByTenantId(tenantId: String): List<Rules> {
        val boardingHouseEntities = boardingHouseDAO.getByTenantId(tenantId)

        val rules = boardingHouseEntities.flatMap { boardingHouseEntity ->
            getRulesByBoardingHouseId(boardingHouseEntity.id)
        }
        return rules
    }

    suspend fun saveRules(rules: List<Rules>): Resource<Boolean>{
        return try {
            rules.forEach {
                rulesDAO.insert(it.toEntity())
            }
            Resource.Success(true)
        }catch (e: Exception){
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }

}