package com.mukesh.machinetask.domain.usecase.getAllCategories

import com.mukesh.machinetask.common.ioDispatcher
import com.mukesh.machinetask.data.local.SmartDto
import com.mukesh.machinetask.db.MachineTaskDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val machineTaskDb: MachineTaskDb
) {

    operator fun invoke(): Flow<List<SmartDto>?> = flow {
        try {
            val categoriesList = machineTaskDb.smartDao().getAllSmart()
            emit(categoriesList)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }.flowOn(ioDispatcher)

}