package com.creanal.lagosjavadevelopers.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creanal.lagosjavadevelopers.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class DetailActivity  extends AppCompatActivity{
    TextView Link, Username;
    CircleImageView imageView;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        imageView = (CircleImageView) findViewById(R.id.user_image_header);
        Username = (TextView) findViewById(R.id.username);
        Link = (TextView) findViewById(R.id.link);

        String username = getIntent(). getExtras().getString("login");
        String avatarUrl = getIntent(). getExtras().getString("avatar_url");
        String link = getIntent(). getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        Username.setText(username);
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.loading)
                .into(imageView);

        getSupportActionBar().setTitle("Details");

    }

    private Intent createShareIntent(){

        String username = getIntent(). getExtras().getString("login");
        String link = getIntent(). getExtras().getString("html_url");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Checkout this awesome developer @ " + username +" , " + link )
                .getIntent();
        return shareIntent;
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.details, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareIntent());
        return true;

    }
}
