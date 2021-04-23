package me.pgb.a2021_04_20_room;

import android.app.Application;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.pgb.a2021_04_20_room.db.SaveDatabase;
import me.pgb.a2021_04_20_room.db.Stock;
import me.pgb.a2021_04_20_room.db.StockDao;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "_MAINACT_";
    private StockDao stockDao;
    private LiveData<List<Stock>> allStocks;
    private SaveDatabase saveDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saveDatabase = SaveDatabase.getInstance(getApplicationContext());
        stockDao = saveDatabase.stockDao();
        allStocks = stockDao.getAll();
        Stock stock = new Stock("CSE 3200");
        observable = io.reactivex.Observable.just(stock);
        Observer<Stock> observer = getStockObserver(stock);

        observable
                .observeOn(Schedulers.io())
                .subscribe(observer);

    }

    private Observable<Stock> observable;

    private Observer<Stock> getStockObserver(Stock stock) { // OBSERVER
        return new Observer<Stock>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(@NonNull Stock stock) {
                saveDatabase.stockDao().insert(stock);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}