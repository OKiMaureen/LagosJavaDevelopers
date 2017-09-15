package com.creanal.lagosjavadevelopers;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creanal.lagosjavadevelopers.controller.DetailActivity;
import com.creanal.lagosjavadevelopers.model.item;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by McCharley Okafor on 9/13/2017.
 */

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {
    private List<item> items;
    private Context context;

    public itemAdapter(Context applicationContext, List<item> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }
@Override
    public itemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_rowuser, viewGroup, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(itemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(items.get(i).getLogin());
        viewHolder.githublink1.setText(items.get(i).getHtmlUrl());

        Picasso.with(context)
                .load(items.get(i).getAvatarUrl())
                .placeholder(R.drawable.loading)
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, githublink1;
        private CircleImageView imageView;


        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            githublink1 = (TextView) view.findViewById(R.id.githublink1);
            imageView = (CircleImageView) view.findViewById(R.id.cover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posit = getAdapterPosition();
                    if (posit != RecyclerView.NO_POSITION) {
                        item clickedDataItem = items.get(posit);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("login", items.get(posit).getLogin());
                        intent.putExtra("html_url", items.get(posit).getHtmlUrl());
                        intent.putExtra("avatar_url", items.get(posit).getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);


                    }
                }
            });
        }
    }
}