package com.example.coursework;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements Observer<Search> {
        private RecyclerView recycler;
        private Search search;
        private LiveData<Search> liveData;
        private DataViewModel dataViewModel=null;
        final SearchAdapter adapter = new SearchAdapter(search);
//        @SuppressLint("HandlerLeak")
//        Handler handler;

//@SuppressLint("HandlerLeak")
@Override
protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler_view);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        dataViewModel=new ViewModelProvider(this, ((DataApplication)getApplication()).getViewModeFactory()).get(DataViewModel.class);

        liveData = dataViewModel.getData();
        liveData = new MutableLiveData<>();
        liveData.observe(this, this);
/*        handler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        Search incomingData = (Search)msg.obj;
                        adapter.setData(incomingData);
                }
        };*/
        }
        public void makeSearch(View view){
//            Log.d("myLogs: ", "making request");
//            UpdateThread updateThread = new UpdateThread(handler);
//            updateThread.start();
            liveData = dataViewModel.getData();
            AppDatabase db = App.getInstance().getDatabase();
            SearchDao searchDao = db.searchDao();
            //searchDao.insert(search);
            }

    @Override
    public void onChanged(Search search) {
        Log.e("MAIN", "onCanged!!!");
        adapter.setData(search);
    }
}