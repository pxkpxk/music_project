package com.dlighttech.music.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.music.R;
import com.dlighttech.music.model.ContentItem;

import java.util.List;

/**
 * Created by dengyong on 2016/6/21.
 * <p>
 * 继承相应的接口实现对应的点击事件
 */

public class ContentAdapter extends BaseAdapter {
    private List<ContentItem> contentItems;
    private LayoutInflater inflater;
    private OnConvertViewClicked mOnConvertView;
    private OnOperateClicked mOnOperate;
    private OnThumbClicked mOnthumb;

    public ContentAdapter(Context context, List<ContentItem> lists) {
        this.inflater = LayoutInflater.from(context);
        this.contentItems = lists;
        Activity activity = (Activity) context;
        if (activity instanceof OnConvertViewClicked) {
            mOnConvertView = (OnConvertViewClicked) activity;
        }
        if (activity instanceof OnThumbClicked) {
            mOnthumb = (OnThumbClicked) activity;
        }
        if (activity instanceof OnOperateClicked) {
            mOnOperate = (OnOperateClicked) activity;
        }
    }

    @Override
    public int getCount() {
        return contentItems.size();
    }

    @Override
    public Object getItem(int position) {
        return contentItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.content_layout,
                    parent, false);
            holder = new ViewHolder();

            holder.thumb = (ImageView) convertView.findViewById(R.id.thumb_imageView_content);
            holder.operator = (ImageView) convertView.findViewById(R.id.operate_imageView_content);
            holder.title = (TextView) convertView.findViewById(R.id.title_content);
            holder.content = (TextView) convertView.findViewById(R.id.content_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ContentItem contentItem = contentItems.get(position);

        holder.thumb.setImageBitmap(contentItem.getThumb());
        holder.operator.setImageBitmap(contentItem.getOperator());
        holder.title.setText(contentItem.getTitle());
        holder.content.setText(contentItem.getContent());

        MyListener listener = new MyListener();
        convertView.setOnClickListener(listener);
        holder.thumb.setOnClickListener(listener);
        holder.operator.setOnClickListener(listener);


        return convertView;
    }

    private static class ViewHolder {
        ImageView thumb;
        ImageView operator;
        TextView title;
        TextView content;
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.operate_imageView_content:
                    if (mOnOperate != null) {
                        mOnOperate.onOperateClicked();
                    }
                    break;
                case R.id.thumb_imageView_content:
                    if (mOnthumb != null) {
                        mOnthumb.onThumbClicked();
                    }
                    break;
                case R.id.content_layout:
                    if (mOnConvertView != null) {
                        mOnConvertView.onConvertViewClicked();
                    }
                    break;
                default:
            }
        }
    }

    /**
     * 列表item点击事件
     */
    public interface OnConvertViewClicked {
        void onConvertViewClicked();
    }

    /**
     * 右侧图片点击事件
     */

    public interface OnThumbClicked {
        void onThumbClicked();
    }

    /**
     * 右侧图片点击事件
     */

    public interface OnOperateClicked {
        void onOperateClicked();
    }


}