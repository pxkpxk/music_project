package com.android.app;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.allenliu.sidebar.SideBar;
import com.dlighttech.music.adapter.ContentAdapter;
import com.dlighttech.music.model.ContentItem;
import com.dlighttech.music.model.MusicInfo;
import com.dlighttech.music.model.Song;
import com.dlighttech.music.util.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MusicSingerActivity extends BaseActivity implements ContentAdapter.OnConvertViewClicked {

    private ListView mListview;
    private SideBar sb_navigation_bar;
    private TextView tv_song_count, tv_song_model;
    private ImageView playModel;

    private ContentAdapter madapter;

    private ArrayList<ContentItem> items = new ArrayList<ContentItem>();
    private ArrayList<MusicInfo> arrayList;
    private ArrayList<Song> songs;
    private MusicInfo info;


    @Override
    public void onInitView() {

        setContentView(R.layout.music_singer_layout);

    }

    @Override
    public void onCreateView() {

        super.setTitleText("Singer");


        sb_navigation_bar = (SideBar) findViewById(R.id.navigation_bar);
        mListview = (ListView) findViewById(R.id.lv_music_detail);

        madapter = new ContentAdapter(this, items, true);
        madapter.setMusicInfos(arrayList);
        mListview.setAdapter(madapter);

        View head = getLayoutInflater().inflate(R.layout.listview_head_layout, null);
        playModel = (ImageView) head.findViewById(R.id.play_mode_icon);
        tv_song_model = (TextView) head.findViewById(R.id.tv_play_mode);
        tv_song_count = (TextView) head.findViewById(R.id.tv_song_count);
        tv_song_count.setText(String.valueOf(madapter.getCount()));
        mListview.addHeaderView(head);


        sb_navigation_bar = (SideBar) findViewById(R.id.navigation_bar);

    }

    @Override
    public void onCreateData() {


        songs = MusicUtils.getAllArtist(this);


        for (int i = 0; i < songs.size(); i++) {
            // 获取所有歌手的id
            Song song = songs.get(i);

            Log.d("haha", "歌手 id ====" + song.getArtistId());
            Log.d("haha", "歌手 ====" + song.getSinger());
            // 根据歌手id获取所有该歌手的音乐信息
            info = MusicUtils.getMusicInfoByArgs(this, false, MediaStore.Audio.Media.ARTIST_ID + " =?"
                    , new String[]{String.valueOf(song.getArtistId())});

            Bitmap bm = MusicUtils.getArtwork(this, info.getMusicId(), info.getAlbumId());

            String singername = song.getSinger();
            int musicCount = MusicUtils.getSongListForArtist(this, song.getArtistId()).length;

            ContentItem item = new ContentItem(bm, R.drawable.more_title_selected, singername, musicCount + "首");

            items.add(item);


        }


    }

    @Override
    public void onSearchTextChanged(String text) {

    }

    @Override
    public void onSearchSubmit(String text) {

    }

    @Override
    public void onConvertViewClicked(int position) {

        Song song =songs.get(position);
        Intent intent = new Intent(MusicSingerActivity.this, MusicSingerContentActivity.class);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("artistId",song.getArtistId());
        startActivity(intent);


    }
}


