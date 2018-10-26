package com.zanvent.mathviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zanvent.mathview.MathView;
import com.zanvent.mathview.Util;

public class MainActivity extends AppCompatActivity {

    private MathView mathView;
    private EditText mathEquationEditText, textSizeEditText, textColorEditText;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mathView.setText(mathEquationEditText.getText().toString());
            mathView.setTextSize(
                    (textSizeEditText.getText().toString().length() > 0 ? Integer.valueOf(textSizeEditText.getText().toString()) : -1)
            );
            mathView.setTextColor(textColorEditText.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mathView = findViewById(R.id.math_view);
        mathEquationEditText = findViewById(R.id.edittext_math_equation);
        textSizeEditText = findViewById(R.id.edittext_text_size);
        textColorEditText = findViewById(R.id.edittext_text_color);
        mathEquationEditText.addTextChangedListener(textWatcher);
        textSizeEditText.addTextChangedListener(textWatcher);
        textColorEditText.addTextChangedListener(textWatcher);

        mathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked on it", Toast.LENGTH_SHORT).show();
            }
        });

        System.out.println(Util.convertDpToPixels(this, 30));
        System.out.println(Util.convertSpToPixels(this, 30));

    }
}
