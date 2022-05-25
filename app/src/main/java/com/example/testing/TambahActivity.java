package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahActivity extends AppCompatActivity {
    EditText et1, et2, et3;
    Button btn_save;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        et1 = findViewById(R.id.et_nama);
        et2 = findViewById(R.id.et_NIM);
        et3 = findViewById(R.id.et_Jurusan);
        btn_save = findViewById(R.id.buttonSave);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getNama = et1.getText().toString();
                String getNim = et2.getText().toString();
                String getJurusan = et3.getText().toString();

                if (getNama.isEmpty()){
                    et1.setError("Silakan isi nama");
                }else if (getNim.isEmpty()){
                    et2.setError("Silakan isi NIM");
                }else if (getJurusan.isEmpty()){
                    et3.setError("Silakan isi jurusan");
                }else{
                    databaseReference.child("Mahasiswa").push().setValue(new Model(getNama, getNim, getJurusan)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TambahActivity.this, "Data berhasil di simpan",  Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TambahActivity.this, MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TambahActivity.this, "Data gagal di simpan",  Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }
}