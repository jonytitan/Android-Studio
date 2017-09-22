package app1.jonytitan.com.buenaspracticasforever;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /*FUNCION DENTRO DE BOTON QUE INICIALIZA LA NUEVA ACTIVITY*/
    public void clickOnBtnConexion(View view) {
        Intent abrir_activity = new Intent(this, ConectionActivity.class);
        startActivity(abrir_activity);
    }
}
