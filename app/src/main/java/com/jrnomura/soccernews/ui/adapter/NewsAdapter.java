package com.jrnomura.soccernews.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jrnomura.soccernews.databinding.NewsItemBinding;
import com.jrnomura.soccernews.domain.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> news;
    private View.OnClickListener favoriteListener;

    public NewsAdapter(List<News> news, View.OnClickListener favoriteListener){
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvDescription.setText(news.getDescription());
        Picasso.get().load(news.getImage()).fit().into(holder.binding.ivThumbnail);

        //Implementação da funcionalidade de abrir link
        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setData(Uri.parse(news.getLink()));
            holder.itemView.getContext().startActivity(intent);
        });

        //Implementação da funcionalidade de compartilhar
        holder.binding.ivShare.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, news.getLink());
            intent.putExtra(Intent.EXTRA_TEXT, news.getLink());
            holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Share"));
        });

        //Implementação da funcionalidade de favoritar (o evento será instaciado pelo Fragment
        holder.binding.ivFavorite.setOnClickListener(this.favoriteListener);
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
