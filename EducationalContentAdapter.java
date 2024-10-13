package com.example.dogfoodapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EducationalContentAdapter extends RecyclerView.Adapter <EducationalContentAdapter.ViewHolder> {

    private List<EducationalContent> contentList;

    public EducationalContentAdapter(List<EducationalContent> contentList) {
        this.contentList = contentList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_educational_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        EducationalContent content = contentList.get(position);
        holder.titleTextView.setText(content.getTitle());
        holder.descriptionTextView.setText(content.getDescription());
        holder.videoView.setVideoURI(content.getVideoUri());
        holder.videoView.start();
    }




    @Override
    public int getItemCount() {
        return contentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        VideoView videoView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            videoView = itemView.findViewById(R.id.videoView);
        }


    }

}
