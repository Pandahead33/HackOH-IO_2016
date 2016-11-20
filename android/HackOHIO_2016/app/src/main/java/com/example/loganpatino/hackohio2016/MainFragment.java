package com.example.loganpatino.hackohio2016;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private List<Card> cards;
    private List<Card> sports;
    private RecyclerView rv;
    private RecyclerView sporty;
    private Context context;
    private int lastPosition = -1;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recyclerview_activity, container, false);

        rv=(RecyclerView) view.findViewById(R.id.rv);

        rv.addOnItemTouchListener( new RecyclerItemClickListener(context, rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println(position);


                        switch(position) {
                            //sports
                            case 0:
                                System.out.println("sports");
                                break;
                            //hang
                            case 1:
                                System.out.println("hang");
                                break;
                            //health
                            case 2:
                                System.out.println("health");
                                break;
                            //nightlife
                            case 3:
                                System.out.println("nightlife");
                                break;

                            //food
                            case 4:
                                System.out.println("food");
                                break;

                            //hobby
                            case 5:
                                System.out.println("hobby");
                                break;

                            //random
                            case 6:
                                System.out.println("random");
                                break;

                            //error
                            default:
                                System.out.println("error");
                                break;





                        }
                        /*
                        Intent intent = new Intent(getActivity(), InitialActivity.class);
                        startActivity(intent);*/

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        GridLayoutManager llm = new GridLayoutManager(getActivity(), 1);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        rv.setItemAnimator(itemAnimator);

        initializeData();
        initializeAdapter();

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

    private void animate(final View view, final int position){

        Animation animation = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        view.startAnimation(animation);
        lastPosition = position;

    }


    private void initializeData(){
        cards = new ArrayList<>();
        cards.add(new Card("Sports", R.drawable.sports));
        cards.add(new Card("Hangouts", R.drawable.hang));
        cards.add(new Card("Health", R.drawable.health));
        cards.add(new Card("Nightlife", R.drawable.nightlife));
        cards.add(new Card("Food", R.drawable.food));
        cards.add(new Card("Hobby", R.drawable.hobby));
        cards.add(new Card("Random", R.drawable.random));

    }



    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(cards);
        rv.setAdapter(adapter);
    }
}
