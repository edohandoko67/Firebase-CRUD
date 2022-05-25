package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {
    EditText et_namaUpdate, et_JurusanUpdate, et_NIMUpdate;
    Button btnUpdate;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    String nama, nim, jurusan, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        et_namaUpdate = findViewById(R.id.et_namaUpdate);
        et_JurusanUpdate = findViewById(R.id.et_JurusanUpdate);
        et_NIMUpdate = findViewById(R.id.et_NIMUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan();
            }
        });

        updateDokter();
    }

    private void updateDokter() {
        Bundle bundle = getIntent().getExtras();
        if (getIntent().getExtras() != null) {
            nama = bundle.getString("Nama");
            nim = bundle.getString("NIM");
            jurusan = bundle.getString("Jurusan");
            key = bundle.getString("Key");

            et_namaUpdate.setText(nama);
            et_JurusanUpdate.setText(jurusan);
            et_NIMUpdate.setText(nim);
        }
    }

    private void simpan(){
        nama = et_namaUpdate.getText().toString().trim();
        nim = et_NIMUpdate.getText().toString().trim();
        jurusan = et_JurusanUpdate.getText().toString().trim();

        if (nama.isEmpty()){
            et_namaUpdate.setError("Harap isi nama");
        }else if(nim.isEmpty()){
            et_NIMUpdate.setError("Harap isi NIM");
        }else if (jurusan.isEmpty()){
            et_JurusanUpdate.setError("Harap isi Jurusan");
        }else
        {
            Model model = new Model(nama, nim, jurusan, key);
            if (model != null){
                database.child("Mahasiswa").child(model.getKey()).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(EditActivity.this, "Data Berhasil Di Update", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }
        }
    }
}