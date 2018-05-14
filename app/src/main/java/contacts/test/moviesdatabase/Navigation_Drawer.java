package contacts.test.moviesdatabase;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;

public class Navigation_Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Fragment fragment=null;
    Toolbar toolbar;
    String movieUrl = "https://api.themoviedb.org/3/discover/movie?api_key=c686d702de9f7a64b3b2fe2a04f8e7b6&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_navigation__drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    //    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      //  fab.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
          NavigationDrawer();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_nav,new Home_Fragment()).commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

      private void NavigationDrawer(){
          drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
          ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                  this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
          drawer.setDrawerListener(toggle);
          toggle.syncState();

      }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.END))
            drawer.closeDrawer(GravityCompat.START);
        else
            drawer.closeDrawer(GravityCompat.START);
    }

    private void MoviesRecyclerView(){

//        StringRequest stringRequest = new StringRequest(movieUrl,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                            Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();
//                        Gson gson = new Gson();
//                //         GsonBuilder gsonBuilder =gson.fromJson(response);
//                        GsonConverterFactory.create(gson);
//               //     gsonBuilder.create();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"Error in MovieRecycler",Toast.LENGTH_SHORT).show();
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//         requestQueue.add(stringRequest);
    }
     private void TvRecyclerView(){


     }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation__drawer, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        switch(item.getItemId()){
//            case R.id.home_menu:
//
//                break;
//            case R.id.movie_menu:
//                fragment = new Movies_fragment();
//        }
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.frame_layout,fragment).commit();
//        drawer.closeDrawer(GravityCompat.START);
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        switch(item.getItemId()){
            case R.id.home_menu:
                fragment = new Home_Fragment();
                break;
            case R.id.movie_menu:
                fragment = new Movies_fragment();
                    break;
            case R.id.tv_menu:
                fragment = new Tv_Fragment();
                    break;
            case R.id.about_menu:
                fragment = new About_Page();
                break;
            case R.id.logout_menu:
                startActivity(new Intent(Navigation_Drawer.this,Login_page.class));
                return true;
        }
       // if(!(fragment ==null)) {
          FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout_nav, fragment).commit();
            drawer.closeDrawer(GravityCompat.START);
        //}
        return true;
    }
}
