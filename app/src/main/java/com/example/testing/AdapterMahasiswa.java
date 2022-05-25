package com.example.testing;

import android.app.Activity;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterMahasiswa extends RecyclerView.Adapter<AdapterMahasiswa.MyViewHolder> {
    private List<Model> modelList;
    private Activity activity;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public AdapterMahasiswa(List<Model>modelList, Activity activity){
        this.modelList = modelList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = modelList.get(position);
        holder.tv_nama.setText("Nama : " + model.getNama());
        holder.tv_NIM.setText("NIM : " + model.getNim());
        holder.tv_Jurusan.setText("Jurusan : " + model.getJurusan());
        holder.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.delete:
                                deleteItem(model, view, model.getNama());
                                break;
                            case R.id.edit:
                                editItem(model, view);
                                break;
                        }
                        return false;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama, tv_NIM, tv_Jurusan;
        CardView card_hasil;
        ImageView imgMenu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_NIM = itemView.findViewById(R.id.tv_NIM);
            tv_Jurusan = itemView.findViewById(R.id.tv_Jurusan);
            card_hasil = itemView.findViewById(R.id.card_hasil);
            imgMenu = itemView.findViewById(R.id.imgMenu);
        }
    }

    private void deleteItem(Model model, View v, String nama){
        databaseReference.child("Mahasiswa").child(model.getKey()).removeValue();
        notifyDataSetChanged();
        Toast.makeText(v.getContext(), "Menghapus " + model.getNama(), Toast.LENGTH_SHORT).show();

    }
    private void editItem(Model model, View view){
        Intent i = new Intent(view.getContext(), EditActivity.class);
        i.putExtra("Nama", model.getNama());
        i.putExtra("NIM", model.getNim());
        i.putExtra("Jurusan", model.getJurusan());
        i.putExtra("Key", model.getKey());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(i);

    }
}
