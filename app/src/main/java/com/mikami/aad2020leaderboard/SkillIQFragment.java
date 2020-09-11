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
public class SkillIQFragment extends Fragment {


    private View mView;
    private RecyclerView mRvSkillIQs;
    public SkillIQFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_skill_iq, container, false);
        mRvSkillIQs = (RecyclerView) mView.findViewById(R.id.rv_skillIqs);
        LinearLayoutManager skilliqsLayoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.VERTICAL, false);
        mRvSkillIQs.setLayoutManager(skilliqsLayoutManager);
        try{
            URL skillIQUrl = ApiUtil.buildUrl(ApiUtil.SKILL_IQ_PARAM);
            new SkillIQQueryTask().execute(skillIQUrl);
        }
        catch (Exception e){
            Log.d("error", e.getMessage());
        }
        return mView;
    }


    public class SkillIQQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL skillIQUrl = urls[0];
            String result = null;
            try{
                result = ApiUtil.getJson(skillIQUrl);
            }
            catch (IOException e){
                Log.e("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            ArrayList<Learner> learners = ApiUtil.getLearnersFromJson(s);
            SkillIIQsAdapter adapter = new SkillIIQsAdapter(learners);
            mRvSkillIQs.setAdapter(adapter);
        }
    }
}
