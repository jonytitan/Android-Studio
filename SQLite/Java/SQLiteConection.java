package app1.jonytitan.com.buenaspracticasforever;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jonytitan on 16/09/2017.
 */

public class SQLiteConection extends SQLiteOpenHelper {

    /*PARA EVITAR INCONVENIENTES CON LOS PARAMETROS POSTERIORES, DECLARAMOS NOMBRE Y VERSION DE LA DB*/
    private static final String NOMBRE_BASE_DE_DATOS = "MyDataBase";
    private static final int VERSION_ACTUAL = 1;

    /*INTERFACE QUE ALMACENARA SOLO LOS NOMBRES DE TODAS LAS TABLAS A CREAR*/
    interface Tablas{
        String TABLA_PERSONA = "persona";
    }

    /*INTERFACE QUE ALMACENARA TODOS LOS QUERYS QUE SE EJECUTARAN UNICAMENTE EN LA PRIMERA CORRIDA*/
    interface Query{
        String CREAR_TABLA_PERSONA = "CREATE TABLE "+Tablas.TABLA_PERSONA+" " +
                "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, telefono INTEGER)";
    }

    /*METODO A INVOCAR PARA INICIALIZAR LA DB, CON UNICO PARAMETRO EL QUERY A EJECUTAR*/
    public SQLiteConection(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_ACTUAL);
    }

    /*METODO DE EJECUCION DE QUERYS EN SU PRIMERA CORRIDA*/
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(Query.CREAR_TABLA_PERSONA);
    }

    /*UNICAMENTE PARA ACTUALIZACIONES*/
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS" +Tablas.TABLA_PERSONA);
    }
}
