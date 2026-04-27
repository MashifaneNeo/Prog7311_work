package com.example.balancewise.data.local.entity

    import android.content.Context
    import androidx.room.Database
    import androidx.room.Room
    import androidx.room.RoomDatabase

    @Database(
        entities = [
            User::class,
            Category::class,
            ExpenseEntry::class,
            MonthlyGoal::class
        ],
        version = 1,
        exportSchema = false
    )
    abstract class  AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
        abstract fun categoryDao(): CategoryDao
        abstract fun expenseEntryDao(): ExpenseEntryDao
        abstract fun monthlyGoalDao(): MonthlyGoalDao

        companion object {
            @Volatile
            private var INSTANCE: AppDatabase? = null

            fun getDatabase(context: Context): AppDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "balance_wise_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }