package com.mukesh.machinetask.domain.usecase

import com.mukesh.machinetask.common.ioDispatcher
import com.mukesh.machinetask.data.local.CoursesDto
import com.mukesh.machinetask.db.MachineTaskDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetCoursesWithIdsUseCase @Inject constructor(
    private val machineTaskDb: MachineTaskDb
) {

    operator fun invoke(coursesId: List<Int>): Flow<List<CoursesDto>?> = flow {
        try {
            val coursesList = machineTaskDb.coursesDto().findMultipleCourses(coursesId)
            emit(coursesList)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }.flowOn(ioDispatcher)

}