package com.example.wanghui.androidstudy.keep;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghui on 2016/4/22.
 * 获取视频列表的帮助类
 */
public class VideosHelper extends AsyncTask {

    private IVideoQueryListener mListener;
    private Context mContext;
    private String path;  //取视频地址

    public VideosHelper(Context context, IVideoQueryListener listener) {
        mContext = context;
        mListener = listener;
    }

    private List<VideoInfo> getAllVideosWith(Context context){
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse(path),
                new String[]{MediaStore.Video.VideoColumns._ID,
                        MediaStore.Video.VideoColumns.DURATION, //时长
                        MediaStore.Video.VideoColumns.SIZE, //大小
                        MediaStore.Video.VideoColumns.DISPLAY_NAME //文件名
                }, MediaStore.Video.VideoColumns.MIME_TYPE + "=?", new String[]{"video/mp4"}
                , MediaStore.Video.VideoColumns.DATE_MODIFIED + " desc"); //时间倒叙

        List<VideoInfo> videoInfos = new ArrayList<>();
        if (cursor == null || !cursor.moveToFirst()){
            return null;
        }
        while (cursor.moveToNext()){
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION));
            videoInfo.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE));
            videoInfo.fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME));
            long videoId = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID));
            videoInfo.thumbnail = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, videoId,
                    MediaStore.Video.Thumbnails.MICRO_KIND,null);
            videoInfos.add(videoInfo);
        }
        cursor.close();
        return videoInfos;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return getAllVideosWith(mContext);
    }

    @Override
    protected void onPostExecute(Object o) {
        mListener.onSuccess((List<VideoInfo>) o);
    }

    public interface IVideoQueryListener{
        void onSuccess(List<VideoInfo> videoInfos);
    }

    public static class VideoInfo{
        public long duration;
        public long size;
        public String fileName;
        public Bitmap thumbnail;
    }
}
