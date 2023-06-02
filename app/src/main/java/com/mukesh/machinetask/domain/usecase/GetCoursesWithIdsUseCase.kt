package com.mukesh.machinetask.domain.usecase

import com.mukesh.machinetask.common.ioDispatcher
import com.mukesh.machinetask.data.local.CoursesDto
import com.mukesh.machinetask.db.MachineTaskDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoursesWithIdsUseCase @Inject constructor(
    private val machineTaskDb: MachineTaskDb
) {

    operator fun invoke(coursesId: List<String>): Flow<List<CoursesDto>?> = flow {
        try {
            val coursesList =
                machineTaskDb.coursesDto().findMultipleCourses(coursesId.map { it.toInt() })
            emit(coursesList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(ioDispatcher)

}