package com.saliim.absensimobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saliim.absensimobile.R;
import com.saliim.absensimobile.model.absensi.DataAbsen;

import java.util.ArrayList;
import java.util.List;

public class AbsensiAdapter extends RecyclerView.Adapter<AbsensiAdapter.AbsensiViewHolder> {
    private List<DataAbsen> dataSet;

    public AbsensiAdapter(ArrayList<DataAbsen> tempData){
        dataSet = tempData;
    }

    @NonNull
    @Override
    public AbsensiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_absensi, parent, false);

        return new AbsensiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsensiViewHolder holder, int position) {
        DataAbsen dataAbsen = dataSet.get(position);
        holder.nama.setText(dataAbsen.getNama());
        holder.lokasi.setText("Lokasi: "+dataAbsen.getLokasi());
        holder.status.setText(dataAbsen.getStatusAbsen());
        holder.tgl.setText(dataAbsen.getCreated());
    }

    @Override
    public int getItemCount() {
        if (dataSet == null){
            return 0;
        } else {
            return dataSet.size();
        }
    }

    public class AbsensiViewHolder extends RecyclerView.ViewHolder {
        public TextView nama, lokasi, status, tgl;
        public AbsensiViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txt_absen_nama);
            lokasi = itemView.findViewById(R.id.txt_absen_lokasi);
            status = itemView.findViewById(R.id.txt_absen_status);
            tgl = itemView.findViewById(R.id.txt_absen_tgl);
        }
    }
}
