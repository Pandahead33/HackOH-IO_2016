package com.example.loganpatino.hackohio2016;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.loganpatino.hackohio2016.RetrofitPreferredUserInterface.PreferredUserService;
import com.facebook.login.widget.ProfilePictureView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PreferredUsersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PreferredUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreferredUsersFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private PreferredUserAdapter mAdapter;
    private String mUserId;
    private Retrofit mRetrofit;
    private List<OtherUser> mOtherUsers;
    private final String BASE_URL = "http://cswebdesign.biz/beacon/";

    public PreferredUsersFragment() {
        // Required empty public constructor
    }

    public static PreferredUsersFragment newInstance() {
        PreferredUsersFragment fragment = new PreferredUsersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserId = AccessToken.getCurrentAccessToken().getUserId();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mOtherUsers = getOtherUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preferred_users, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        mAdapter = new PreferredUserAdapter();
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private List<OtherUser> getOtherUsers() {
        List<OtherUser> otherUsers = new ArrayList<>();
        OtherUser user = new OtherUser("Lucas", "1241317319265906");
        otherUsers.add(user);
        PreferredUserService service = mRetrofit.create(PreferredUserService.class);
        Call<List<OtherUser>> call = service.getPreferredFriends(mUserId);
        call.enqueue(new Callback<List<OtherUser>>() {
            @Override
            public void onResponse(Call<List<OtherUser>> call, Response<List<OtherUser>> response) {
                if (response.isSuccessful()) {
                    //Try to get response body
                    List<OtherUser> result = response.body();
                    Log.d("RESULT", String.valueOf(result));

                    for (OtherUser p : result) {
                        Log.d("PRINT", p.getFirstName());
                    }
                }
                else {
                    //Try to get response body
                    BufferedReader reader = null;
                    StringBuilder sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));

                    String line;

                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    String result = sb.toString();
                    Log.d("RESULT", result);
                }
            }

            @Override
            public void onFailure(Call<List<OtherUser>> call, Throwable t) {

            }
        });

        return otherUsers;
    }

    private class PreferredUserAdapter extends RecyclerView.Adapter<PreferredUserViewHolder> {
        @Override
        public PreferredUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.preferred_user_card_view, parent, false);
            return new PreferredUserViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PreferredUserViewHolder holder, int position) {
            OtherUser currentUser = mOtherUsers.get(position);
            holder.setName(currentUser.getFirstName());
            holder.setPic(currentUser.getProfilePicUrl());
        }

        @Override
        public int getItemCount() {
            return mOtherUsers.size();
        }
    }

    private class PreferredUserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ProfilePictureView pic;

        public PreferredUserViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.firstName);
            pic = (ProfilePictureView) itemView.findViewById(R.id.pic);
        }

        public void setName(String name) {
            this.name.setText(name);
        }

        public void setPic(String id) {
            this.pic.setProfileId(id);
        }
    }
}
