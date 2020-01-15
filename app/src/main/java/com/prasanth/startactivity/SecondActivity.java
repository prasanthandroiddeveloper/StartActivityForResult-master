package com.prasanth.startactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.prasanth.startactivity.FirstActivity.FIRST_DATA_KEY;

/**
 * @author prasanth
 */
public class SecondActivity extends AppCompatActivity {
    public static final String SECOND_DATA_KEY = "SECOND_DATA_KEY";
    private Intent mIntent;
    private EditText mEditText;
    private int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mIntent = new Intent();

        Bundle bundle = getIntent().getExtras();
        String firstActivityData = bundle.getString(FIRST_DATA_KEY);

        TextView textView = findViewById(R.id.second_tv);
        textView.setText(firstActivityData);

        mEditText = findViewById(R.id.second_et);
        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboardFrom(v);
                }
            }
        });

        Button okBtn = findViewById(R.id.second_ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextString = mEditText.getText().toString();
                if (!TextUtils.isEmpty(editTextString)) {
                    mIntent.putExtra(SECOND_DATA_KEY, mEditText.getText().toString());
                    setResult(REQUEST_CODE,mIntent);
                    finish();
                }
            }
        });
    }

    public void hideKeyboardFrom(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
