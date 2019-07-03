package com.saliim.absensimobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saliim.absensimobile.R;
import com.saliim.absensimobile.model.absensi.DataAbsenPerIdAdmin;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AbsensiPerAdminIdAdapter extends RecyclerView.Adapter<AbsensiPerAdminIdAdapter.AbsensiPerAdminIdViewHolder>{
    private List<DataAbsenPerIdAdmin> dataSet;

    public AbsensiPerAdminIdAdapter(ArrayList<DataAbsenPerIdAdmin> tempData){
        dataSet = tempData;
    }

    @Override
    public AbsensiPerAdminIdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_absensi_per_admin_id, parent, false);

        return new AbsensiPerAdminIdAdapter.AbsensiPerAdminIdViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AbsensiPerAdminIdViewHolder holder, int position) {
        DataAbsenPerIdAdmin dataAbsen = dataSet.get(position);
        holder.nama.setText(dataAbsen.getNama());
        holder.lokasi.setText("Lokasi: "+dataAbsen.getLokasi());
        holder.status.setText(dataAbsen.getStatusAbsen());
        holder.jamMasuk.setText(dataAbsen.getJamMasuk());
    }

    @Override
    public int getItemCount() {
        if (dataSet == null){
            return 0;
        } else {
            return dataSet.size();
        }
    }

    public class AbsensiPerAdminIdViewHolder extends RecyclerView.ViewHolder {
        TextView nama, lokasi, status, jamMasuk;

        public AbsensiPerAdminIdViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txt_absen_perAdmin_id_nama);
            lokasi = itemView.findViewById(R.id.txt_absen_perAdmin_id_lokasi);
            status = itemView.findViewById(R.id.txt_absen_perAdmin_id_status);
            jamMasuk = itemView.findViewById(R.id.txt_absen_perAdmin_id_jamMasuk);
        }
    }
}
