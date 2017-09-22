package app1.jonytitan.com.buenaspracticasforever;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConectionActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtNombre, txtApellido, txtTelefono;
    Button btnRegistrar;
    private Cursor celda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conection);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(this);
    }
    /*FUNCION QUE DETECTARA TODAS LAS OPERACIONES QUE REALICEN TODOS LOS BOTONES DE ESTE ACTIVITY*/
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnRegistrar:
                    String nombre = txtNombre.getText().toString();
                    String apellido = txtApellido.getText().toString();
                    String telefono = txtTelefono.getText().toString();
                    ValidarPersona(nombre,apellido,telefono);
            }
        }


    /*FUNCION QUE VALIDA SI EL NOMBRE INGRESADO YA EXISTEN, PIDE UNICAMENTE COMO PARAMETRO LA
    CANTIDAD DE VARIABLES QUE NECESITES*/
    public void ValidarPersona(String Nombre, String Apellido, String Telefono){
        SQLiteConection conexion = new SQLiteConection(this);
        SQLiteDatabase database = conexion.getWritableDatabase();
        String sql_verificar = "SELECT nombre from persona WHERE nombre = '"+Nombre+"'";
        celda = database.rawQuery(sql_verificar,null);
        if(celda.moveToFirst()){
            String usuario_respuesta = celda.getString(0);
            if(usuario_respuesta.equals(Nombre)){
                Toast.makeText(getApplicationContext(),"Este nombre ya existe, prueba con otro",Toast.LENGTH_SHORT).show();
                txtNombre.setText(""); txtApellido.setText(""); txtTelefono.setText("");
            }else{
                   RegistrarPersona(Nombre,Apellido,Telefono);
            }
        }
        database.close();
    }
    /*FUNCION QUE REGISTRA LOS VALORES INGRESADOS EN LOS EDITTEXT*/
    private void RegistrarPersona(String nombre, String apellido, String telefono) {
        SQLiteConection conexion = new SQLiteConection(this);
        SQLiteDatabase database = conexion.getWritableDatabase();
        String sql_insertar = "INSERT INTO persona (nombre, apellido, telefono) VALUES ('"+nombre+"','"+apellido+"','"+telefono+"')";
        Toast.makeText(getApplicationContext(),"Persona "+nombre+" Registrado con Exito",Toast.LENGTH_SHORT).show();
        database.execSQL(sql_insertar);
        database.close();
    }
}
