package com.example.corina.hobby;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //chestii obligatorii la crearea unei activitati
        super.onCreate(savedInstanceState);//este un obiect de tip Bundle care contine starea salvata precedent a activitatii
        setContentView(R.layout.activity_main);//declararea interfetei cu utilizatorul prin pasarea id-ului resursei fisierului
            //(R.layout.activity_main, unde R este o clasa care extinde clasa Object care este
            //radacina ierarhiei de clase


    }

    //cod de realizare a legaturii dintre activitatea curenta si alta activitate prin intermediul unui buton
    public void startOnClick(View v){
        Intent intent = new Intent(v.getContext(), Subjects.class); //descriere abstracta a operatiei de pornire a
        // activitatii care urmeaza a fi executata
        //parametrii: contextul(informatii legate de mediul
        //aplicatiei) si un obiect de tip clas al activitatii
        //cu care face legatura
        startActivityForResult(intent, 0);//apeleaza efectiv operatia de lansare a activitatii print intermediul intent-ului
        //0-codul operatiei de lansare
    }


}
