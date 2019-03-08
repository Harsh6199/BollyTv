package com.example.harshranjan1.bollytv;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Movies extends AppCompatActivity {
    ImageView Poster,fav;
    ArrayList<MoviePOJO> tasks;
    TextView tv_name,tv_rating,tv_description,trailer;
    NotificationManager manager;
    MoviePOJO chosen_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ActivityCompat.requestPermissions(this,new String[]{WRITE_EXTERNAL_STORAGE,INTERNET,READ_EXTERNAL_STORAGE},34);

        chosen_movie = (MoviePOJO) getIntent().getSerializableExtra("Selected_Movie");

        Toolbar toolbar=(Toolbar)findViewById(R.id.movie_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(chosen_movie.getName());

        trailer=findViewById(R.id.trailer);
        trailer.setPaintFlags(trailer.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trailer_str=chosen_movie.getTrailer();
                Intent i =new Intent(Movies.this,Trailer.class);
                i.putExtra("trailer",trailer_str);
                startActivity(i);
            }
        });

        tv_name=findViewById(R.id.name);
        tv_name.setText(chosen_movie.getName());
        tv_rating=findViewById(R.id.rating);
        tv_rating.setText(chosen_movie.getRating());
        tv_description=findViewById(R.id.description);
        tv_description.setText(chosen_movie.getPlot_sum());

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Movies.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });
        FloatingActionButton download=findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startDownload(chosen_movie.getDownload(),chosen_movie.getName());


            }
        });






        Poster=findViewById(R.id.Poster);
        Picasso.get().load(chosen_movie.getImg_link()).into(Poster);


    }
    public String startDownload(String url, String name) {
        Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show();


         manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this,"34");
        mBuilder.setSmallIcon(R.drawable.icon1);
        mBuilder.setContentTitle("Downloading Movie");
        mBuilder.setContentText(chosen_movie.getName());
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification notification = mBuilder.build();
        notification.flags |=Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;
        manager.notify(34,notification);

        DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("GadgetSaint Downloading " + "Sample_" + name + ".mkv");
        request.setDescription("Downloading " + "Sample_" + name + ".mkv");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/GadgetSaint/"  + "/" + "Sample_" + name + ".mkv");

        long refid = mManager.enqueue(request);
        return String.valueOf(refid);
    }



    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Toast.makeText(ctxt, "Download Complete", Toast.LENGTH_SHORT).show();
            manager.cancel(34);

        }
    };
}
