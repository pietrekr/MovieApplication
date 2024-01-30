package tmdb.arch.movieapp.domain.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tmdb.arch.movieapp.domain.model.local.FavoriteEntity
import tmdb.arch.movieapp.domain.model.local.ToWatchEntity

@Dao
interface MoviesDao {
    @Query("SELECT* FROM favorite")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(entity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(entity: FavoriteEntity)

    @Query("SELECT* FROM to_watch")
    fun getAllMoviesToWatch(): Flow<List<ToWatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieToWatch(entity: ToWatchEntity)

    @Query("DELETE FROM to_watch WHERE movie_id = :id")
    suspend fun deleteMovieToWatch(id: Long)
}