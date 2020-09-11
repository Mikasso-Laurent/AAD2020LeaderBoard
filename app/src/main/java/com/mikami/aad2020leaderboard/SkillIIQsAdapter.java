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

public class SkillIIQsAdapter extends  RecyclerView.Adapter<SkillIIQsAdapter.LearnerViewHolder>{

    ArrayList<Learner> mLearners;
    public SkillIIQsAdapter(ArrayList<Learner> learners){
        this.mLearners = learners;
    }
    @NonNull
    @Override
    public LearnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.skilliq_list_item, parent, false);
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
        TextView mTvScore;
        TextView mTvCountry;
        ImageView mIvSkillIQ;

        public LearnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvNameiq);
            mTvScore = (TextView) itemView.findViewById(R.id.tvScore);
            mTvCountry = (TextView) itemView.findViewById(R.id.tvCountryiq);
            mIvSkillIQ = (ImageView) itemView.findViewById(R.id.skill_iq_image);
        }

        public void bind (Learner learner){
            mTvName.setText(learner.name);
            mTvScore.setText(Integer.toString(learner.score)+" skill IQ Score, ");
            mTvCountry.setText(learner.country);
            Picasso.get().load(learner.badgeUrl).into(mIvSkillIQ);
        }
    }
}
