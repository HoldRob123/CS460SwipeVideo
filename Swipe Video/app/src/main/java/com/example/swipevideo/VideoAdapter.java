package com.example.swipevideo;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter class for managing video items in a RecyclerView.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    private List<VideoItem> videoItem;

    /**
     * Constructor for VideoAdapter.
     *
     * @param videoItem List of video items to be displayed.
     */
    public VideoAdapter(List<VideoItem> videoItem) {
        this.videoItem = videoItem;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItem.get(position));
    }

    @Override
    public int getItemCount() {
        return videoItem.size();
    }

    /**
     * ViewHolder class for video items.
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder{

        TextView textVideoTitle, textVideoDescription, textVideoID;
        VideoView videoView;
        ProgressBar progressBar;

        /**
         * Constructor for VideoViewHolder.
         *
         * @param itemView The view of the video item.
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle = itemView.findViewById(R.id.textViewTitle);
            textVideoDescription = itemView.findViewById(R.id.textViewDescription);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
            textVideoID = itemView.findViewById(R.id.textViewID);
        }

        /**
         * Sets the data for the video item.
         *
         * @param videoItem The video item to be displayed.
         */
        void setVideoData(VideoItem videoItem){
            textVideoTitle.setText(videoItem.videoTitle);
            textVideoDescription.setText(videoItem.videoDescription);
            videoView.setVideoPath(videoItem.videoURL);
            textVideoID.setText(videoItem.videoID);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = videoView.getWidth() / (float) videoView.getHeight();

                    float scale = videoRatio / screenRatio;
                    if (scale >= 1f) {
                        videoView.setScaleX(scale);
                    } else {
                        videoView.setScaleY(1f / scale);
                    }
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }
}
