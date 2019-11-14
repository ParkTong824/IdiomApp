package com.example.idiom.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.idiom.R;
import com.example.idiom.model.Idioms;

public class CorrectDialog extends Dialog {

    private TextView correctTitle;
    private TextView correctMean;
    private TextView correctId;

    private Button continueButton;

    private View.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.correct_dialog_layout);

        continueButton = findViewById(R.id.dialog_continue_button);

        correctTitle = findViewById(R.id.dialog_result_title);
        correctMean = findViewById(R.id.dialog_result_mean);
        correctId = findViewById(R.id.dialog_result_id);

        continueButton.setOnClickListener(clickListener);
    }

    void settingDialogView(Idioms correctIdiom) {
        Log.e("at dialog correct",""+correctIdiom);
        correctTitle.setText(correctIdiom.getTitle());
        correctId.setText(correctIdiom.getId());
        correctMean.setText(correctIdiom.getMean());
    }

    CorrectDialog(@NonNull Context context, View.OnClickListener mListener) {
        super(context);
        this.clickListener = mListener;
    }
}
