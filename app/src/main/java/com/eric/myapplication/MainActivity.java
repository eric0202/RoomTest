package com.eric.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String TAG = "DATABASE TEST";
    String tagNet = "HTTP TEST";
    String tagPost = "POST TETS";
    String httpResult;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);

        testRoom();
        
        testHttp();

        testPost();
    }

    private void testPost() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("https://saying.api.azwcl.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyService myService = build.create(MyService.class);
        retrofit2.Call<com.eric.myapplication.Response> sayingForDay = myService.getSayingForDay();
        sayingForDay.enqueue(new retrofit2.Callback<com.eric.myapplication.Response>() {
            @Override
            public void onResponse(retrofit2.Call<com.eric.myapplication.Response> call, retrofit2.Response<com.eric.myapplication.Response> response) {
                Log.i(tagPost,"succeeded, and result :" + response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<com.eric.myapplication.Response> call, Throwable t) {
                Log.i(tagPost,"failed");
            }
        });
    }

    private void testHttp() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://saying.api.azwcl.com/saying/get").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(tagNet,"failed");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.i(tagNet,"succeeded");
                Log.i(tagNet, "content: "+ Objects.requireNonNull(response.body()).string());
            }
        });

    }

    private void testRoom() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                NoteDao noteDao = NotesDatabase.get(getApplicationContext()).noteDao();
                Note note = new Note();
                note.setTitle("dear diary");
                note.setContent("long time no see");
                noteDao.insertNote(note);

                List<Note> notes = noteDao.getAllNotes();

                Log.e(TAG, notes.toString());
                tv.setText(note.toString());
            }
        }.start();

    }

}