package com.mukesh.machinetask.domain.usecase.updateDb

import com.mukesh.machinetask.data.local.CoursesDto
import com.mukesh.machinetask.data.local.SmartDto
import com.mukesh.machinetask.data.local.UserDto
import com.mukesh.machinetask.data.remote.ResponseDto
import com.mukesh.machinetask.db.MachineTaskDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class UpdateDbUseCase @Inject constructor(
    private val machineTaskDb: MachineTaskDb
) {

    operator fun invoke(responseDto: ResponseDto): Flow<String?> = flow {
        try {
            //Update DB
            supervisorScope {
                responseDto.result.let {
                    updateSmartTable(it.collections?.smart ?: emptyList())
                    updateUserTable(it.collections?.user ?: emptyList())
                    updateCoursesTable(it.index ?: emptyList())
                }
            }
            emit(System.currentTimeMillis().toString())
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


    /**
     * Update Smart Table
     * */
    private suspend fun updateSmartTable(list: List<ResponseDto.Result.Collections.Smart>){
        val smartDtoList: List<SmartDto?> = list.map { it.toSmartDto() }
        machineTaskDb.smartDao().insertSmarts(smartDtoList)
    }


    /**
     * Update User Table
     * */
    private suspend fun updateUserTable(list: List<ResponseDto.Result.Collections.User>) {
        val userDtoList: List<UserDto?> = list.map { it.toUserDto() }
        machineTaskDb.userDao().insertUsers(userDtoList)
    }


    /**
     * Update Courses Table
     * */
    private suspend fun updateCoursesTable(list: List<ResponseDto.Result.Index>) {
        val coursesList: List<CoursesDto> = list.map { it.toCoursesDto() }
        machineTaskDb.coursesDto().insertCourses(coursesList)
    }

}