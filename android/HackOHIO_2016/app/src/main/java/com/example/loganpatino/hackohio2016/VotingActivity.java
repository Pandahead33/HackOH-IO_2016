package com.example.loganpatino.hackohio2016;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.widget.ProfilePictureView;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VotingActivity extends AppCompatActivity {

    private List<OtherUser> mOtherUsers;
    private VotingUserAdapter mAdapter;
    private boolean click1;
    private boolean click2;
    private Handler one = new Handler();
    private Handler two = new Handler();
    private Runnable rone = null;
    private Runnable rtwo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        TextView textView1 = (TextView) findViewById(R.id.text1);
        TextView textView2 = (TextView) findViewById(R.id.text2);
        TextView textView3 = (TextView) findViewById(R.id.text3);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:40.004123,-83.010494?z=19");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:40.005474,-83.012230?z=19");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:40.004525,-83.036088?z=19");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        ImageView pic1 = (ImageView) findViewById(R.id.sandy);
        ImageView pic2 = (ImageView) findViewById(R.id.theBeek);
        ImageView pic3 = (ImageView) findViewById(R.id.vobo);
        click1 = false;
        click2 = false;

        mOtherUsers = new ArrayList<>();
        mOtherUsers.add(new OtherUser("Logan", "512148565"));
        mOtherUsers.add(new OtherUser("Lucas", "1241317319265906"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        mAdapter = new VotingUserAdapter();
        recyclerView.setAdapter(mAdapter);

        pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1 = true;
                mAdapter.notifyDataSetChanged();
            }
        });

        pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click2 = true;
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private class VotingUserAdapter extends RecyclerView.Adapter<VotingUserViewHolder> {
        @Override
        public VotingUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.voting_list_item_view, parent, false);
            return new VotingUserViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(VotingUserViewHolder holder, int position) {
            OtherUser currentUser = mOtherUsers.get(position);
            holder.setName(currentUser.getFirstName());
            holder.setPic(currentUser.getProfilePicUrl());
            holder.setVote(position);
        }

        @Override
        public int getItemCount() {
            return mOtherUsers.size();
        }
    }

    private class VotingUserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ProfilePictureView pic;
        private TextView dash;
        private TextView vote;

        public VotingUserViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.firstName);
            pic = (ProfilePictureView) itemView.findViewById(R.id.pic);
            dash = (TextView) itemView.findViewById(R.id.dash);
            vote = (TextView) itemView.findViewById(R.id.vote);
        }

        public void setName(String name) {
            this.name.setText(name);
        }

        public void setPic(String id) {
            this.pic.setProfileId(id);
        }

        public void setVote(int position) {
            if (position == 1 && click1) {
                this.dash.setText("-");
                this.vote.setText("1");
                click1 = false;
                Handler one = new Handler();
                one.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        click2 = true;
                        mAdapter.notifyDataSetChanged();
                    }
                }, 4250);



            }
            if(click2) {
                this.dash.setText("-");
                this.vote.setText("1");
                click2 = false;



                two.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(VotingActivity.this, MeetingActivity.class);
                        intent.putExtra("sick", "512148565");
                        startActivity(intent);
                    }
                }, 1750);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VotingActivity.this, MainTabActivity.class);
        finish();
        startActivity(intent);
    }




}
