package com.app.motel.data.repository

import com.app.motel.data.entity.KhuTroEntity
import com.app.motel.data.local.BoardingHouseDAO
import com.app.motel.data.local.RulesDAO
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Rules
import javax.inject.Inject

class RulesRepository @Inject constructor(
    private val rulesDAO: RulesDAO,
    private val boardingHouseDAO: BoardingHouseDAO,
){
    suspend fun getRulesByUserId(userId: String): List<BoardingHouse> {
        val boardingHousesEntities: List<KhuTroEntity> = boardingHouseDAO.getByUserId(userId)
        return boardingHousesEntities.map { boardingHouseEntity ->
            boardingHouseEntity.toModel().apply {
                val rulesEntities = rulesDAO.getRegulationsByKhuTro(id)
                rules = rulesEntities.map { it.toModel() } as ArrayList<Rules>
            }
        }
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