package com.jrnomura.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jrnomura.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();
        List<News> news = new ArrayList<>();

        //TODO Remover Mock de Notícias
        news.add(new News("Ferroviária tem Desfalque Importante", "There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain..."));
        news.add(new News("Corinthians Joga Domingo", "There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain..."));
        news.add(new News("Time do Corinthians Vence Mais Uma", "There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain..."));

        this.news.setValue(news);

    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}