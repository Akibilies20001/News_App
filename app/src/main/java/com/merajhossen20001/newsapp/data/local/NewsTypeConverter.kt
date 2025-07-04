package com.merajhossen20001.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.merajhossen20001.newsapp.domain.newsmodels.Source
import dagger.Provides

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }


    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(",").let { sourceArray ->
            Source(sourceArray[0], sourceArray[1])

        }
    }
}
