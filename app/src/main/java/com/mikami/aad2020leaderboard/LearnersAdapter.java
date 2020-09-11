package com.mikami.aad2020leaderboard;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LearnersAdapter extends  RecyclerView.Adapter<LearnersAdapter.LearnerViewHolder>{

    ArrayList<Learner> mLearners;
    public LearnersAdapter(ArrayList<Learner> learners){
        this.mLearners = learners;
    }
    @NonNull
    @Override
    public LearnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.learner_list_item, parent, false);
        return new LearnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerViewHolder holder, int position) {
        Learner learner = mLearners.get(position);
        holder.bind(learner);
    }

    @Override
    public int getItemCount() {
        return mLearners.size();
    }

    public class LearnerViewHolder extends RecyclerView.ViewHolder{

        TextView mTvName;
        TextView mTvHour;
        TextView mTvCountry;
        ImageView mIvLearner;
        ImageView mIvSkillIQ;

        public LearnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvHour = (TextView) itemView.findViewById(R.id.tvHours);
            mTvCountry = (TextView) itemView.findViewById(R.id.tvCountry);
            mIvLearner = (ImageView) itemView.findViewById(R.id.learner_image);
        }

        public void bind (Learner learner){
            Uri uri = Uri.parse(learner.badgeUrl).buildUpon().build();
            mTvName.setText(learner.name);
            mTvHour.setText(Integer.toString(learner.hour)+" learning hours, ");
            mTvCountry.setText(learner.country);
            Picasso.get().load(learner.badgeUrl).into(mIvLearner);

        }
    }
}
