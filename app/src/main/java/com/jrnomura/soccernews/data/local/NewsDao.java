package com.jrnomura.soccernews.data.local;

import androidx.room.Dao;
import androidx.room.Query;

import com.jrnomura.soccernews.domain.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news WHERE favorite = favorite")
    List<News> loadFavoriteNews(boolean favorite);

}
