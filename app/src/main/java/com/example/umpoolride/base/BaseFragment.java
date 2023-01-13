package com.example.umpoolride.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this,rootView);
        init();
        return rootView;
    }

    protected View findView(int id){
        return rootView.findViewById(id);
    }

    protected abstract int getLayoutId();
    protected abstract void init();

    protected void showToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

}
