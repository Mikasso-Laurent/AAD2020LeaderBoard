package com.mikami.aad2020leaderboard;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LearningFragment extends Fragment {


    private View mView;
    private TextView mLearning_text;
    private RecyclerView mRvLearners;

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_learning, container, false);
        mRvLearners = (RecyclerView) mView.findViewById(R.id.rv_learners);
        LinearLayoutManager learnersLayoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.VERTICAL, false);
        mRvLearners.setLayoutManager(learnersLayoutManager);
        try{
            URL learningUrl = ApiUtil.buildUrl(ApiUtil.LEARNING_PARAM);
            new LearningQueryTask().execute(learningUrl);
        }
        catch (Exception e){
            Log.d("error", e.getMessage());
        }
        return mView;
    }

    public class LearningQueryTask extends AsyncTask<URL, Void, String>{
        @Override
        protected String doInBackground(URL... urls) {
            URL learningUrl = urls[0];
            String result = null;
            try{
                result = ApiUtil.getJson(learningUrl);
            }
            catch (IOException e){
                Log.e("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            ArrayList<Learner> learners = ApiUtil.getLearnersFromJson(s);
            LearnersAdapter adapter = new LearnersAdapter(learners);
            mRvLearners.setAdapter(adapter);
        }
    }
}
