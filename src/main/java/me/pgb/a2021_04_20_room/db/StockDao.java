package me.pgb.a2021_04_20_room.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StockDao {

    @Insert
    void insert(Stock stock);

    @Delete
    void delete(Stock stock);

    @Update
    void update(Stock stock);

    @Query("SELECT * FROM stock")
    LiveData<List<Stock>> getAll();

    @Query("DELETE FROM stock")
    void deleteAll();
}
