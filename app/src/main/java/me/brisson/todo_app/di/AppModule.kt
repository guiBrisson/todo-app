package me.brisson.todo_app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.brisson.todo_app.data.database.AppDatabase
import me.brisson.todo_app.data.database.dao.TodoDao
import me.brisson.todo_app.data.database.repository.SharedPrefsImpl
import me.brisson.todo_app.data.database.repository.TodoRepositoryImpl
import me.brisson.todo_app.domain.repository.SharedPrefs
import me.brisson.todo_app.domain.repository.TodoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesSharedPrefs(
        @ApplicationContext context: Context
    ): SharedPrefs = SharedPrefsImpl(context)

    @Provides
    @Singleton
    fun providesTodoRepository(todoDao: TodoDao): TodoRepository = TodoRepositoryImpl(todoDao)

    @Provides
    fun providesTodoDao(appDatabase: AppDatabase): TodoDao = appDatabase.todoDao()

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }
}