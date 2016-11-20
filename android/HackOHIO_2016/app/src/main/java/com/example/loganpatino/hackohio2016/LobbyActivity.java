package com.example.loganpatino.hackohio2016;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LobbyActivity extends AppCompatActivity {

    private List<OtherUser> mPartyMembers;
    private LobbyActivityAdapter mAdapter;
    private int count;
    private TextView mWaiting;
    private final String WAITING = "waiting";
    private final int WAIT_TIME = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Profile.fetchProfileForCurrentAccessToken();

        mPartyMembers = new ArrayList<>();
        mPartyMembers.add(new OtherUser(Profile.getCurrentProfile().getFirstName(), AccessToken.getCurrentAccessToken().getUserId()));

        TextView title = (TextView) findViewById(R.id.title);
        String lobbyTitle = getIntent().getStringExtra("title") + " Lobby";
        title.setText(lobbyTitle);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        mAdapter = new LobbyActivityAdapter();
        recyclerView.setAdapter(mAdapter);

        mWaiting = (TextView) findViewById(R.id.waiting);
        count = 0;

        new Wait().execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPartyMembers.add(new OtherUser("Lucas", "1241317319265906"));
                mAdapter.notifyDataSetChanged();
            }
        }, WAIT_TIME);
    }

    private class LobbyActivityAdapter extends RecyclerView.Adapter<LobbyActivityViewHolder> {
        @Override
        public LobbyActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lobby_list_item_view, parent, false);
            return new LobbyActivityViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(LobbyActivityViewHolder holder, int position) {
            OtherUser currentUser = mPartyMembers.get(position);
            holder.setName(currentUser.getFirstName());
            holder.setPic(currentUser.getProfilePicUrl());
        }

        @Override
        public int getItemCount() {
            return mPartyMembers.size();
        }
    }

    private class LobbyActivityViewHolder extends RecyclerView.ViewHolder {
        private TextView firstName;
        private ProfilePictureView pic;

        public LobbyActivityViewHolder(View itemView) {
            super(itemView);
            firstName = (TextView) itemView.findViewById(R.id.firstName);
            pic = (ProfilePictureView) itemView.findViewById(R.id.pic);
        }

        public void setName(String name) {
            this.firstName.setText(name);
        }

        public void setPic(String userId) {
            this.pic.setProfileId(userId);
        }
    }

    private class Wait extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            int threshold = 25000;
            int dots = 0;
            while (count < 200000) {
                count++;
                //Log.d("count", String.valueOf(count));
                if (count > threshold) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mWaiting.append(".");
                        }
                    });
                    threshold += 25000;
                    dots++;
                    if (dots == 4) {
                        dots = 0;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mWaiting.setText(WAITING);
                            }
                        });
                    }
                }
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mWaiting.setText(WAITING);
                }
            });
            return null;
        }
    }
}
