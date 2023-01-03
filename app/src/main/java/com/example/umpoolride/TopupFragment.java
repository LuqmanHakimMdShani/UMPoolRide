package com.example.umpoolride;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopupFragment extends Fragment {

    public TopupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etText = view.findViewById(R.id.topupamount);
        etText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5, 2)});

        ImageButton PaymentMethodBtn = view.findViewById(R.id.PaymentMethodBtn);
        View.OnClickListener OCLPaymentMethod = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TopupAmount = String.valueOf(etText.getText());

                if(!TextUtils.isEmpty(TopupAmount)){
                    Bundle bundle = new Bundle();
                    bundle.putString("TopupAmount", TopupAmount);
                    Navigation.findNavController(view).navigate(R.id.TopupToTopupSource, bundle);
                }else{
                    Toast.makeText(getActivity(), "Please enter your Topup amount", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        };
        PaymentMethodBtn.setOnClickListener(OCLPaymentMethod);
    }

    class DecimalDigitsInputFilter implements InputFilter {
        private Pattern mPattern;
        DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
            mPattern = Pattern.compile("[0-9]+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
        }
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher matcher = mPattern.matcher(dest);
            if (!matcher.matches())
                return "";
            return null;
        }
    }
}