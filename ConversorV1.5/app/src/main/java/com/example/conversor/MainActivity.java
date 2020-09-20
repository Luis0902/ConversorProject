package com.example.conversor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.jar.JarException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {



private RequestQueue queue;
private TextView mtextView;

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

        mtextView=findViewById(R.id.textView4);
        queue= Volley.newRequestQueue(this);
        obtenerDatosVolley();


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



    private void obtenerDatosVolley()
    {
        String url = "";

         final JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url,   JsonRequest: null, new Response.Listener<JSONObject>() {
        @Override

        public void OnResponse(JSONObject response) {
            try {

                String base = response.getString(name:"base");
                JSONObject rates = response.getJSONObject("rates");
                Iterator<String> itr = rates.keys();
                while (itr.hasNext()) {

                    String key = itr.next();
                    try {

                        Object value = rates.get(key);
                        Toast.makeText(MainActivity.this, text:key+ "" + value, Toast.LENGTH_SHORT).show();
                    } catch (JarException e) {


                    }


                }
                mtextView.setText(base);


            }


        }


    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
        queue.add(request);
         }

    }






