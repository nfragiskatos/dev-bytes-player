/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.nfragiskatos.android.devbyteviewer.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nfragiskatos.android.devbyteviewer.database.VideosDatabase
import com.nfragiskatos.android.devbyteviewer.database.getDatabase
import com.nfragiskatos.android.devbyteviewer.repository.VideosRepository
import retrofit2.HttpException

class RefreshDataWork(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val database: VideosDatabase = getDatabase(applicationContext)
        val repository = VideosRepository(database)

        repository.refreshVideos()

        return try {
            repository.refreshVideos()
            Result.success()
        } catch (exception: HttpException) {
            Result.retry()
        }
    }

}