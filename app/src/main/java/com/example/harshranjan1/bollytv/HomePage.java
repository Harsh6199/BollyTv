package com.example.harshranjan1.bollytv;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    ArrayList<MoviePOJO> movies;
    GridView gridView;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movie List");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        email=findViewById(R.id._id_email);
//        email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(HomePage.this, "hello", Toast.LENGTH_SHORT).show();
//            }
//        });





        progressDialog=new ProgressDialog(this);

        movies = new ArrayList<>();
        gridView=findViewById(R.id.grid_view);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("Movies");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    MoviePOJO movie = postSnapshot.getValue(MoviePOJO.class);
                    movies.add(movie);
                    GridAdapter adapter=new GridAdapter(HomePage.this,movies);
                    gridView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAuth = FirebaseAuth.getInstance();



        final String letterlist[]={"Rowdy Rathore","Gangs of wassepur2","pk","Dangal","piku","Dhoom3","Chennai Express","Tiger zinda hai",
                "Bajranji Bhaijaan","Sultan","Krrish 3","Raees","Happy new year","Golmaal Again","prem ratan","Kick",
        "Ye jawani hai deewani","Bajirao Mastani","Bang Bang","Dabang 2","Bagghi 2","Dilwale","Tanu weds Manu returns","Singham Returns",
        "Bodyguard","Dabang","Judwa 2","Toilet ek prem katha","MS Dhoni-the untold story","Rustom","Airlift","Jab tak hai jaan","Bahubali 1",
        "Ready","Tubelight","Badrinath ki dulhaniya","Ram Leela","Jolly llb2","Jai Ho","Agneepath","Ghajni","Ra-one",
        "Barfi!","Ae Dil Hai Mushkil","Holiday","Bhagg Milkha Bagh","Hosefull 3","Sonu ke Titu ki sweety","ABCD-2","Don2","Golmal3"
                ,"Son Of Sardar","Ek Villan","Housefull2","Raid","2 States","Bol Bacchan","Kaabil","Grand Masti","Race 2","Singham",
        "Shivaay","Welcome Back","Baby","Talaash","Raajneeti","Singh is Bling","Zindagi Na milegi dobara","Gabbar is Back","Fan",
        "Brothers","OMG!"};


        final String links[]={


        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/75439684-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/Agneepath-Poster-Feature-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/Ghajjni-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/Ra-One-Poster-Feature-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/74637364.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/feature1-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/79951279.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/74637391.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/housefull3-306x393.jpgjvhv",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/11/Sonu-Ke-Titu-Ki-Sweety-3-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/425912702-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/Done-2-Poster-Feature-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/Golmaal-Poster-Feature-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/74637370.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/82792532-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/Housefull-2-Poster-Feature-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2017/08/Raid-3-1-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/79504474.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/74637344.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/Kaabil-1-3-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/74637349.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/74637373.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/singham-Poster-Feature-306x393.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/Shivaay-Review3-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/432729792-306x393.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/92864437-306x393.jpg",
        "http://media3.bollywoodhungama.in/wp-content/uploads/2016/03/74637379.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/rajneeti-Poster-Feature-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/432721149-306x393.jpg",
        "http://stat3.bollywoodhungama.in/wp-content/uploads/2016/03/ZNMD-Poster-Feature-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/423049326-306x393.jpg",
        "http://stat2.bollywoodhungama.in/wp-content/uploads/2016/03/435145397-306x369.jpg",
        "http://media1.bollywoodhungama.in/wp-content/uploads/2016/03/426389974.jpg",
        "http://media2.bollywoodhungama.in/wp-content/uploads/2016/03/74637358.jpg"};


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i=new Intent(HomePage.this,Movies.class);
                MoviePOJO movie=movies.get(position);
                i.putExtra("Selected_Movie",movie);
                startActivity(i);

            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                final AlertDialog.Builder builder=new AlertDialog.Builder(HomePage.this);
                builder.setMessage("Movie Title  :  " +letterlist[position] );
                builder.setCancelable(true);
                builder.setNegativeButton("Download Movie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent(HomePage.this,Movies.class);
                        MoviePOJO movie=movies.get(position);
                        i.putExtra("Selected_Movie",movie);
                        startActivity(i);

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
               // Toast.makeText(HomePage.this, "hello", Toast.LENGTH_SHORT).show();
                return false;
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] from={"bollytv1000@gmail.com"};
                Intent i=new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_EMAIL,from);
                startActivity(i);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {


            return true;
        }
        if (id==R.id.exit)
        {
            final AlertDialog.Builder builder=new AlertDialog.Builder(HomePage.this);
            builder.setMessage("Exit app ?");
            builder.setCancelable(true);
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertdialog=builder.create();
            alertdialog.show();
        }
        if(id==R.id.sign_out)
        {
            progressDialog.setMessage("Signing out...");
            progressDialog.show();
            mAuth.signOut();
            Intent i=new Intent(HomePage.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_settings)
        {
            Intent i=new Intent(HomePage.this,Settings.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share)
        {
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            startActivity(i);



        } else if (id == R.id.nav_exit)
        {
            progressDialog.setMessage("Signing out...");
            progressDialog.show();
            mAuth.signOut();
            Intent i=new Intent(HomePage.this,LoginActivity.class);
            startActivity(i);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
