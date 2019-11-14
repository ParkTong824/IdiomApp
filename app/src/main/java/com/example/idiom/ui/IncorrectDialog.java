package com.example.idiom.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.idiom.R;
import com.example.idiom.model.Idioms;

public class IncorrectDialog extends Dialog {
    private TextView incorrectTitle;
    private TextView incorrectId;
    private TextView incorrectMean;

    private TextView selectIncorrectTitle;
    private TextView selectIncorrectId;
    private TextView selectIncorrectMean;

    private Button continue_button;
    private View.OnClickListener clickListener;

    public IncorrectDialog(@NonNull Context context, View.OnClickListener mListener) {
        super(context);
        this.clickListener = mListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incorrect_dialog_layout);
        init();
    }

    private void init() {
        incorrectId = findViewById(R.id.incorrect_result_id);
        incorrectMean = findViewById(R.id.incorrect_result_mean);
        incorrectTitle = findViewById(R.id.incorrect_result_title);

        selectIncorrectId = findViewById(R.id.incorrect_select_id);
        selectIncorrectMean = findViewById(R.id.incorrect_select_mean);
        selectIncorrectTitle = findViewById(R.id.incorrect_select_title);
        continue_button = findViewById(R.id.dialog_continue_button);
        continue_button.setOnClickListener(clickListener);
    }


    void settingDialogView(Idioms incorrectIdiom, Idioms selectIdiom) {
        Log.e("at dialog incorrect",""+incorrectIdiom);
        incorrectTitle.setText(incorrectIdiom.getTitle());
        incorrectId.setText(incorrectIdiom.getId());
        incorrectMean.setText(incorrectIdiom.getMean());

        selectIncorrectTitle.setText(selectIdiom.getTitle());
        selectIncorrectMean.setText(selectIdiom.getMean());
        selectIncorrectId.setText(selectIdiom.getId());
    }
}
