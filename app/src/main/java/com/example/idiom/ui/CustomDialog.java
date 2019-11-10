package com.example.idiom.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.idiom.R;

public class CustomDialog extends Dialog {

    ImageView resultImageView;
    TextView resultTextView;
    TextView resultTitleTextView;
    TextView resultMeanTextView;
    Button resultContinueButton;

    private View.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.custom_dialog_layout);

        resultContinueButton = findViewById(R.id.dialog_continue_button);
        resultImageView = findViewById(R.id.dialog_imageView);
        resultTextView = findViewById(R.id.dialog_correct_result_textView);
        resultTitleTextView = findViewById(R.id.dialog_result_hanja);
        resultMeanTextView = findViewById(R.id.dialog_result_mean);

        resultContinueButton.setOnClickListener(clickListener);
    }

    CustomDialog(@NonNull Context context, View.OnClickListener mListener) {
        super(context);
        this.clickListener = mListener;
    }
}