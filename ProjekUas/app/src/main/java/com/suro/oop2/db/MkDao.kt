package com.suro.oop2.db

import androidx.room.*
import com.suro.oop2.model.Mk

@Dao
interface MkDao {

    @Insert
    fun insert(contact: Mk)

    @Update
    fun update(contact: Mk)

    @Delete
    fun delete(contact: Mk)

    @Query("SELECT * FROM Mk")
    fun getAll() : List<Mk>

    @Query("SELECT * FROM Mk WHERE id = :id")
    fun getById(id: Int) : List<Mk>
}