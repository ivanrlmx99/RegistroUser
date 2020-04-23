package com.example.listaheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private EditText editTextCity;
    private EditText editTextPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = (EditText) findViewById(R.id.id_editex);
        editTextName = (EditText) findViewById(R.id.name_girl);
        editTextLastName = (EditText) findViewById(R.id.lastname_editex);
        editTextAge = (EditText) findViewById(R.id.age_girl);
        editTextCity = (EditText) findViewById(R.id.address_girl);
        editTextPhone = (EditText) findViewById(R.id.phone_girl);


    }

    public void registrarDatos(View view) {
        AdminSqlHelper admi = new AdminSqlHelper(this, "administracion", null, 1);
        SQLiteDatabase dataBase = admi.getWritableDatabase();

        String strinId = editTextId.getText().toString();
        String strinName = editTextName.getText().toString();
        String strinLastName = editTextLastName.getText().toString();
        String strinAge = editTextAge.getText().toString();
        String strinCity = editTextCity.getText().toString();
        String strinPhone = editTextPhone.getText().toString();

        if (!strinId.isEmpty() && !strinName.isEmpty() && !strinLastName.isEmpty() && !strinAge.isEmpty()&&
        !strinCity.isEmpty()&&!strinPhone.isEmpty()){
            ContentValues registro=new ContentValues();

            registro.put("id",strinId);
            registro.put("name",strinName);
            registro.put("lastName",strinLastName);
            registro.put("age",strinAge);
            registro.put("city",strinCity);
            registro.put("phone",strinPhone);

            dataBase.insert("agenda",null,registro);
            dataBase.close();

            editTextName.setText("");
            editTextLastName.setText("");
            editTextAge.setText("");
            editTextCity.setText("");
            editTextPhone.setText("");



            Toast.makeText(this,"Se registro los datos",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }

        }

        //metodo para buscar un contacto

    public void buscarPer(View view){
AdminSqlHelper admin=new AdminSqlHelper(this,"administracion",null,1);
SQLiteDatabase dataBase=admin.getWritableDatabase();

String id_buscar=editTextId.getText().toString();

      if(!id_buscar.isEmpty()){
          Cursor fila=dataBase.rawQuery("SELECT name,lastname,age,city,phone FROM agenda WHERE codigo"+id_buscar,null);

          if(fila.moveToFirst()){
              editTextName.setText(fila.getString(0));
              editTextLastName.setText(fila.getString(1));
              editTextAge.setText(fila.getString(2));
              editTextCity.setText(fila.getString(3));
              editTextPhone.setText(fila.getString(4));

              dataBase.close();
          }
          else{
              Toast.makeText(this,"No existe dato de esta persona",Toast.LENGTH_SHORT).show();
              dataBase.close();
          }
      }
      else{
          Toast.makeText(this,"Debes llenar codigo a buscar",Toast.LENGTH_SHORT).show();
      }

    }
    //metodo para eliminar un usuario
    public void eliminar(View view){
        AdminSqlHelper admin=new AdminSqlHelper(this,"adminstracion",null,1);
        SQLiteDatabase baseDatos=admin.getWritableDatabase();
        String clave=editTextId.getText().toString();


        if(!clave.isEmpty()){
            int cantidad=baseDatos.delete("administracion","id="+clave,null);
            baseDatos.close();

            editTextName.setText("");
            editTextLastName.setText("");
            editTextAge.setText("");
            editTextCity.setText("");
            editTextPhone.setText("");


            if(cantidad==1){
                Toast.makeText(this,"Se elimino usuario",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this,"Usuario no existe",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"Debes introducir la clave de esta persona",Toast.LENGTH_SHORT).show();
        }
    }

    //Método para modificar un artículo o producto
    public void Modificar(View view){
        AdminSqlHelper admin = new AdminSqlHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String strinId = editTextId.getText().toString();
        String strinName = editTextName.getText().toString();
        String strinLastName = editTextLastName.getText().toString();
        String strinAge = editTextAge.getText().toString();
        String strinCity = editTextCity.getText().toString();
        String strinPhone = editTextPhone.getText().toString();


         if (!strinId.isEmpty() && !strinName.isEmpty() && !strinLastName.isEmpty() && !strinAge.isEmpty()&&
                !strinCity.isEmpty()&&!strinPhone.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("id",strinId);
            registro.put("name",strinName);
            registro.put("lastName",strinLastName);
            registro.put("age",strinAge);
            registro.put("city",strinCity);
            registro.put("phone",strinPhone);

            int cantidad = BaseDatabase.update("articulos", registro, "codigo=" + strinId, null);
            BaseDatabase.close();

            if(cantidad == 1){
                Toast.makeText(this, "Artículo modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    }
