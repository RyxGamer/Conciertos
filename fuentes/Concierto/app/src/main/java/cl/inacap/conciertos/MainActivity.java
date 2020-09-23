package cl.inacap.conciertos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.conciertos.dto.Evento;

public class MainActivity extends AppCompatActivity {

    Spinner comboGenero;
    private List<Evento> eventos = new ArrayList<>();
    private EditText nombreTxt;
    private EditText valorTxt;
    private EditText notaTxt;
    private ListView eventosLV;
    private Button agregarBtn;
    private Button limpiarBtn;
    private int dia,mes,ano;
    Button bfecha;
    EditText efecha;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nombreTxt = findViewById(R.id.nombreTxt);
        this.valorTxt = findViewById(R.id.valorTxt);
        this.notaTxt = findViewById(R.id.notaTxt);
        this.eventosLV = findViewById(R.id.eventosLv);
        this.agregarBtn = findViewById(R.id.agregarBTN);
        this.limpiarBtn = findViewById(R.id.limpiarBtn);
        efecha=(EditText) = findViewById(R.id.bfecha);
        bfecha=(Button)findViewById(R.id.bfecha);
        bfecha.setOnClickListener((View.OnClickListener) this);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            private String nombre;

            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                int valor = 0;
                int nota = 0;
                try {
                    valor = Integer.parseInt(valorTxt.getText().toString());
                    if(valor<1000){
                        throw new NumberFormatException();
                    }
                }catch(NumberFormatException ex){
                    errores.add("El valor debe ser mayor a 1000");
                }
                try {
                    nota = Integer.parseInt(notaTxt.getText().toString());
                    if(nota<1 || nota>7 ){
                        throw new NumberFormatException();
                    }
                }catch(NumberFormatException ex){
                    errores.add("La calificacion debe ser entre 1 y 7");
                }
               if(errores.isEmpty()){
                   Evento e = new Evento();
                   e.setValor(valor);
                   e.setNombre(nombre);
                   e.setNota(nota);
                   e.setGenero(comboGenero);
                   e.setFecha(efecha);
               }
            }
        });

        comboGenero=findViewById(R.id.idSpinnerGenero);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_genero_musica,android.R.layout.simple_spinner_item);
        comboGenero.setAdapter(adapter);




    }

    private void mostrarErrores(List<String errores>){
        String mensaje ="";
        for(String r: errores){
            mensaje+="-"+ r + "\n";
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
            alertBuilder.setTitle("Error de validacion")
                    .setMessage(mensaje)
                    .setPositiveButton("Aceptar", null)
                    .create()
                    .show();
        }
    }
    public void onClick(View v){
    if (v==bfecha){
        final Calendar c = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
               efecha.setText((dayOfMonth)+"/"+(monthOfYear)+"/"(year));
            }
        }


    }
    }
}

