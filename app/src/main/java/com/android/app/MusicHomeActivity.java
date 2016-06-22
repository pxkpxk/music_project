package com.android.app;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ListView;

import com.dlighttech.music.adapter.HomeAdapter;
import com.dlighttech.music.model.ContentItem;

import java.util.ArrayList;
import java.util.List;

public class MusicHomeActivity extends BaseActivity {

    private ListView mListView;
    private List<ContentItem> mData;
    private HomeAdapter mAdapter;

    @Override
    public void onCreateView() {
        setContentView(R.layout.music_home_page_layout);

        mListView = (ListView) findViewById(R.id.home_list_view);
        mAdapter = new HomeAdapter(this, mData);
        mListView.setAdapter(mAdapter);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                ArrayList<ContentItem> items=FileUtils.getFileList(MusicHomeActivity.this,FileUtils.Music);
//                for (ContentItem item:items) {
//                }
                // 获取所有歌曲id
                long[] ids= MusicUtils.getAllSongs(MusicHomeActivity.this);
                for (int i=0;i<ids.length;i++){
                    // 获取歌曲的专辑图片
                    Bitmap b =MusicUtils.getArtwork(MusicHomeActivity.this,ids[i],0);

                    Log.d("TAG",ids[i]+"====="+b);

                }
                mAdapter.updateItem();
            }
        },2000);
    }

    @Override
    public void onCreateData() {
        mData = new ArrayList<ContentItem>();
        ContentItem itemDir = new ContentItem(R.drawable.seek_thumb
                , R.drawable.left, "文件夹");
        ContentItem itemPlayList = new ContentItem(R.drawable.playlist_tile
                , R.drawable.left, "播放列表");
        ContentItem itemUpdate = new ContentItem(R.drawable.app_music
                , R.drawable.left, "最近更新");
        ContentItem itemPlay = new ContentItem(R.drawable.stat_notify_musicplayer
                , R.drawable.left, "最近播放");
        mData.add(itemDir);
        mData.add(itemPlayList);
        mData.add(itemUpdate);
        mData.add(itemPlay);
    }

    @Override
    public void onSearchTextChanged(String text) {
        Log.d("TAG", text);
    }

    @Override
    public void onSearchSubmit(String text) {
        Log.d("TAG", text);
    }


}
