package com.android.app;

import android.util.Log;

public class MusicHomeActivity extends BaseActivity {


    public MusicHomeActivity() {
        super(null, R.layout.music_home_page_layout);
    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onCreateData() {

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