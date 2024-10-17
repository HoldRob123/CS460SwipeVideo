package com.example.swipevideo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity class that sets up the main activity for the app.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     *                           then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           <b>Note: Otherwise it is null.</b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        final ViewPager2 videoViewPager = findViewById(R.id.videosViewPager);

        List<VideoItem> videoItemsList = new ArrayList<>();
        VideoItem videoCelebration1 = new VideoItem();
        videoCelebration1.videoURL = "https://firebasestorage.googleapis.com/v0/b/cs460-46bbc.appspot.com/o/6010489-hd_720_1280_25fps.mp4?alt=media&token=d1745e92-add3-4e46-b529-9e8fd4da1431";
        videoCelebration1.videoTitle = "Waves";
        videoCelebration1.videoDescription = "Waves crashing against rocks";
        videoCelebration1.videoID = "ID: 156674912";
        videoItemsList.add(videoCelebration1);

        VideoItem videoCelebration2 = new VideoItem();
        videoCelebration2.videoURL = "https://firebasestorage.googleapis.com/v0/b/cs460-46bbc.appspot.com/o/4434286-hd_720_1280_30fps.mp4?alt=media&token=0e5923a2-d898-4bc8-aea6-ac50592fcf1d";
        videoCelebration2.videoTitle = "Walking through the forest";
        videoCelebration2.videoDescription = "Beautiful lake in the forest";
        videoCelebration2.videoID = "ID: 897956646";
        videoItemsList.add(videoCelebration2);

        VideoItem videoCelebration3 = new VideoItem();
        videoCelebration3.videoURL = "https://firebasestorage.googleapis.com/v0/b/cs460-46bbc.appspot.com/o/5595352-hd_1080_1920_24fps.mp4?alt=media&token=01abee65-aad4-4926-a0db-1a751914dfe1";
        videoCelebration3.videoTitle = "City life";
        videoCelebration3.videoDescription = "Timelapse of the city";
        videoCelebration3.videoID = "ID: 165784924";
        videoItemsList.add(videoCelebration3);


        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));

    }
}