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

package com.nfragiskatos.android.devbyteviewer.database

import android.provider.MediaStore
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nfragiskatos.android.devbyteviewer.domain.Video

/**
 * Defining a Room database entity
 */
@Entity
data class DatabaseVideo constructor(
        @PrimaryKey
        val url: String,
        val updated: String,
        val title: String,
        val description: String,
        val thumbnail: String
)

/**
 * Extension function to convert our database entity to a domain model
 *
 * Right now it's just a 1-to-1 mapping, but it's good practice anyway to separate the layers so if we do need to update the
 * mapping to something more complex.
 *
 * Also, if we were to just use the database entity everywhere in our app then we might have to change each use of the entity
 * object. This way we only have to change it here in this one spot.
 */
fun List<DatabaseVideo>.asDomainModel(): List<Video> {
    return map {
        Video(
                url = it.url,
                title = it.title,
                description = it.description,
                updated = it.updated,
                thumbnail = it.thumbnail
        )
    }
}

