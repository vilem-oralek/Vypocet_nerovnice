package com.example.nerovnice;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerOperation;
    private EditText editText1, editText2, editText3, editText4;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText1 = findViewById(R.id.editTextNumberSigned);
        editText2 = findViewById(R.id.editTextNumberSigned2);
        editText3 = findViewById(R.id.editTextNumberSigned3);
        editText4 = findViewById(R.id.editTextNumberSigned4);

        textView = findViewById(R.id.textView);
        spinnerOperation = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.operator,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerOperation.setAdapter(adapter);
    }
    public void Calculate(View view){
        Spinner spinnerOperation = (Spinner) findViewById(R.id.spinner);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);

        String num1Str = editText1.getText().toString();
        String num2Str = editText2.getText().toString();
        String num3Str = editText3.getText().toString();
        String num4Str = editText4.getText().toString();
        String operation = spinnerOperation.getSelectedItem().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty() || num3Str.isEmpty() || num4Str.isEmpty()){
            textView.setText("zadejte všechny čísla");
            return;
        }

        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);
        int num3 = Integer.parseInt(num3Str);
        int num4 = Integer.parseInt(num4Str);

        if (num1 == 0){
            textView.setText("Nesprávný formát kvadratické nerovnice");
            return;
        }

        num3 -= num4;
        double D = num2 * num2 - 4 * num1 * num3;
        double x1 = (-num2 - Math.sqrt(D)) / (2 * num1);
        double x2 = (-num2 + Math.sqrt(D)) / (2 * num1);
        double x = (-num2) / (2 * num1);

        switch (operation) {
            case ">":
                if (D > 0) {
                    if (num1 > 0){
                        textView.setText("(-∞, " + x1 + ") ∪ (" + x2 + ", ∞)");
                    } else if (num1 < 0){
                        textView.setText("(" + x2 + ", " + x1 + ")");
                    }
                } else if (D == 0){
                    if (num1 > 0){
                        textView.setText("(-∞, " + x + ") ∪ (" + x + ", ∞)");
                    } else if (num1 < 0){
                        textView.setText("{∅}");
                    }
                } else if (D < 0){
                    if (num1 > 0){
                        textView.setText("(-∞, ∞)");
                    } else if (num1 < 0){
                        textView.setText("{∅}");
                    }
                }
                break;
            case "≥":
                if (D > 0) {
                    if (num1 > 0){
                        textView.setText("(-∞, " + x1 + "> ∪ <" + x2 + ", ∞)");
                    } else if (num1 < 0){
                        textView.setText("<" + x2 + ", " + x1 + ">");
                    }
                } else if (D == 0){
                    if (num1 > 0){
                        textView.setText("(-∞, ∞)");
                    } else if (num1 < 0){
                        textView.setText("{" + x + "}");
                    }
                } else if (D < 0){
                    if (num1 > 0){
                        textView.setText("(-∞, ∞)");
                    } else if (num1 < 0){
                        textView.setText("{∅}");
                    }
                }
                break;
            case "<":
                if (D > 0) {
                    if (num1 > 0){
                        textView.setText("(" + x1 + ", " + x2 + ")");
                        textView.setText("(-∞, " + x1 + ") ∪ (" + x2 + ", ∞)");
                    } else if (num1 < 0){
                        textView.setText("(-∞, " + x2 + ") ∪ (" + x1 + ", ∞)");
                    }
                } else if (D == 0){
                    if (num1 > 0){
                        textView.setText("{∅}");
                    } else if (num1 < 0){
                        textView.setText("(-∞, " + x + ") ∪ (" + x + ", ∞)");
                    }
                } else if (D < 0){
                    if (num1 > 0){
                        textView.setText("{∅}");
                    } else if (num1 < 0){
                        textView.setText("(-∞, ∞)");
                    }
                }
                break;
            case "≤":
                if (D > 0) {
                    if (num1 > 0){
                        textView.setText("<" + x1 + ", " + x2 + ">");
                    } else if (num1 < 0){
                        textView.setText("(-∞, " + x2 + "> ∪ <" + x1 + ", ∞)");
                    }
                } else if (D == 0){
                    if (num1 > 0){
                        textView.setText("{" + x + "}");
                    } else if (num1 < 0){
                        textView.setText("(-∞, ∞)");
                    }
                } else if (D < 0){
                    if (num1 > 0){
                        textView.setText("{∅}");
                    } else if (num1 < 0){
                        textView.setText("(-∞, ∞)");
                    }
                }
                break;
        }
    }
    public void Reset(View view){
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        textView.setText("Výsledek");
    }
}

