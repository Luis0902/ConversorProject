package com.example.conversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    // CAMPOS /////////////////////////////////////////////////////
    EditText dollars;
    EditText lempiras;
    EditText cambio;
    RadioButton dtoe;
    RadioButton etod;
    Button convert;
    Button salir;

    DecimalFormat precision= new DecimalFormat("0.00");
    // FIN CAMPOS //////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        // INICIALIZACIÓN DE CAMPOS //////////////////////////////////////////////
        dollars = (EditText) this.findViewById(R.id.edtDolares);
        lempiras = (EditText) this.findViewById(R.id.edtLempiras);
        cambio = (EditText) this.findViewById(R.id.etCambio);
        salir=(Button)findViewById(R.id.btnSalir) ;
        dtoe = (RadioButton) this.findViewById(R.id.rbDolarAeuro);
        dtoe.setChecked(true);
        etod = (RadioButton) this.findViewById(R.id.rbEuroAdolar);

        convert = (Button) this.findViewById(R.id.btnConvertir);
        // FIN INICIALIZACIÓN DE CAMPOS ////////////////////////////////////////////////


        // EVENTOS ///////////////////////////////////////////
        convert.setOnClickListener(this);
        dtoe.setOnClickListener(this);
        etod.setOnClickListener(this);




        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(intent.CATEGORY_HOME);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



    }


    protected void convertDollarsToEuros() {

        double val;
        double tmp;

        // Controla los campos introducidos
        if ("".equals(dollars.getText().toString())) {
            val = 0;
        } else {
            val = Double.parseDouble(dollars.getText().toString());
        }

        if ("".equals(cambio.getText().toString())) {
            tmp = 0;
        } else {
            tmp = Double.parseDouble(cambio.getText().toString());
        }

        // Convierte el cambio a euros
        lempiras.setText(precision.format(val * 24.70));
    }


    protected void convertEurosToDollars() {

        double val;
        double tmp;

        // Controla los campos introducidos
        if ("".equals(lempiras.getText().toString())) {
            val = 0;
        } else {
            val = Double.parseDouble(lempiras.getText().toString());
        }

        if ("".equals(cambio.getText().toString())) {
            tmp = 0;
        } else {
            tmp = Double.parseDouble(cambio.getText().toString());
        }


        // Convierte los cambios a dólares
        dollars.setText(precision.format(val / 24.70));
    }

    public void onClick(View v) {

        // Controla el radioButtons chequeado
        if (v == convert) {
            if (dtoe.isChecked()) {
                convertDollarsToEuros();
            }
            if (etod.isChecked()) {
                convertEurosToDollars();
            }
        }

        // Controla que no estén dos radioButtons chequeados
        if (v == dtoe) {
            etod.setChecked(false);
        }

        if (v == etod) {
            dtoe.setChecked(false);
        }
    }






}

