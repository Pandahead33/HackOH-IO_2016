package com.example.loganpatino.hackohio2016;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.AccessToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.example.loganpatino.hackohio2016.RetrofitSettingsInterface.SettingsService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private String mUserId;
    private final String BASE_URL = "http://cswebdesign.biz/beacon/";
    private Retrofit mRetrofit;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserId = AccessToken.getCurrentAccessToken().getUserId();
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);

        final String id = AccessToken.getCurrentAccessToken().getUserId();

        Switch notificationSwitch = (Switch) view.findViewById(R.id.notificationSwitch);
        //notificationSwitch.setChecked(getReceiveNotificationSetting());
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setReceiveNotificationSetting(isChecked);
            }
        });

        final TextView rangeProgress = (TextView) view.findViewById(R.id.rangeProgress);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.rangeSeekBar);
        //seekBar.setProgress(getDistance());
        rangeProgress.setText(String.valueOf(seekBar.getProgress()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rangeProgress.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int distance = seekBar.getProgress();
                setDistance(distance);
            }
        });

        return view;
        };

    public void locateButt(View view)
    {
        /*Intent intent = new Intent(getActivity(), .class);
        startActivity(intent);*/
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

    private boolean setDistance(int distance) {
        final boolean[] ans = {false};
        SettingsService service = mRetrofit.create(RetrofitSettingsInterface.SettingsService.class);
        Call<Boolean> call = service.setDistance(mUserId, distance);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    //Try to get response body
                    boolean result = response.body();
                    Log.d("RESULT", String.valueOf(result));
                    ans[0] = result;
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
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

        return ans[0];
    }

    private int getDistance() {
        final int[] ans = {-1};
        SettingsService service = mRetrofit.create(SettingsService.class);
        Call<Integer> call = service.getDistance(mUserId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    //Try to get response body
                    int result = response.body();
                    Log.d("RESULT", String.valueOf(result));
                    ans[0] = result;
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
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

        return ans[0];
    }

    private boolean getReceiveNotificationSetting() {
        final boolean[] ans = {false};
        SettingsService service = mRetrofit.create(SettingsService.class);
        Call<Boolean> call = service.getReceiveNotificationSetting(mUserId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    //Try to get response body
                    boolean result = response.body();
                    Log.d("RESULT", String.valueOf(result));
                    ans[0] = result;
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
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

        return ans[0];
    }

    private boolean setReceiveNotificationSetting(boolean notifications) {
        final boolean[] ans = {false};
        SettingsService service = mRetrofit.create(SettingsService.class);
        Call<Boolean> call = service.setReceiveNotificationSetting(mUserId, notifications);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    //Try to get response body
                    boolean result = response.body();
                    Log.d("RESULT", String.valueOf(result));
                    ans[0] = result;
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
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

        return ans[0];
    }
}
