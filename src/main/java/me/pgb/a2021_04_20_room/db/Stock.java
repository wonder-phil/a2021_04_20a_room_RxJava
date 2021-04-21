package me.pgb.a2021_04_20_room.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Stock {

    public Stock(String save_string) {
        this.save_string = save_string;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="save_string")
    public String save_string;
}
