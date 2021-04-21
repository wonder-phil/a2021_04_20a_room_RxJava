package me.pgb.a2021_04_20_room;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import me.pgb.a2021_04_20_room.db.SaveDatabase;
import me.pgb.a2021_04_20_room.db.Stock;

public class StockViewModel extends AndroidViewModel {

    private LiveData<List<Stock>> allStocks;

    public StockViewModel(@NonNull Application application) {
        super(application);

        allStocks = SaveDatabase.getInstance(application.getApplicationContext()).stockDao().getAll();
    }

    public LiveData<List<Stock>> getAllStocks() {
        return allStocks;
    }

}
