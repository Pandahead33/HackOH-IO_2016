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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
    private Context mContext;
    private List<String> mPreferredUsersList;
    private PreferredUserAdapter mAdapter;

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
        mContext = getActivity().getApplicationContext();
        mPreferredUsersList = getPreferredFriends();
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

    private List<String> getPreferredFriends() {
        String userId = AccessToken.getCurrentAccessToken().getUserId();
        // TODO: Get preferred friends from db and make appropriate list

        // dummy data to test
        List<String> list = Arrays.asList("Logan", "Lucas", "Alex", "Clinton", "Logan", "Lucas", "Alex", "Clinton", "Logan", "Lucas", "Alex", "Clinton", "Logan", "Lucas", "Alex", "Clinton", "Logan", "Lucas", "Alex", "Clinton", "Logan", "Lucas", "Alex", "Clinton");

        return list;
    }

    private class PreferredUserAdapter extends RecyclerView.Adapter<PreferredUserViewHolder> {
        @Override
        public PreferredUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.preferred_user_card_view, parent, false);
            return new PreferredUserViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PreferredUserViewHolder holder, int position) {
            holder.setName(mPreferredUsersList.get(position));
        }

        @Override
        public int getItemCount() {
            return mPreferredUsersList.size();
        }
    }

    private class PreferredUserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public PreferredUserViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }

        public void setName(String name) {
            this.name.setText(name);
        }
    }
}
