package com.satria.OOP2_CRUD.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hp")
data class hp(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "merk") val merk: String,
    @ColumnInfo(name = "stok") val stok: Int,
    @ColumnInfo(name = "harga") val harga: Int
)