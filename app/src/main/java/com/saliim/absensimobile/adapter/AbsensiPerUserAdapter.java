package com.saliim.absensimobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saliim.absensimobile.R;
import com.saliim.absensimobile.model.absensi.DataAbsenPerUser;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AbsensiPerUserAdapter extends RecyclerView.Adapter<AbsensiPerUserAdapter.AbsensiPerUserViewHolder> {
    private List<DataAbsenPerUser> dataSet;

    public AbsensiPerUserAdapter(ArrayList<DataAbsenPerUser> tempData){
        dataSet = tempData;
    }

    @Override
    public AbsensiPerUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_absensi_peruser, parent, false);

        return new AbsensiPerUserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AbsensiPerUserViewHolder holder, int position) {
        DataAbsenPerUser dataAbsenPerUser = dataSet.get(position);
        holder.status.setText(dataAbsenPerUser.getStatusAbsen());
        holder.lokasi.setText("Lokasi: "+dataAbsenPerUser.getLokasi());
        holder.jamMasuk.setText(dataAbsenPerUser.getJamMasuk());
        holder.jamPulang.setText(dataAbsenPerUser.getJamPulang());
    }

    @Override
    public int getItemCount() {
        if (dataSet == null){
            return 0;
        } else {
            return dataSet.size();
        }
    }

    public class AbsensiPerUserViewHolder extends RecyclerView.ViewHolder {
        TextView status, lokasi, jamMasuk, jamPulang;
        public AbsensiPerUserViewHolder(View itemView) {
            super(itemView);

            status = itemView.findViewById(R.id.txt_absen_perUser_status);
            lokasi = itemView.findViewById(R.id.txt_absen_perUser_lokasi);
            jamMasuk = itemView.findViewById(R.id.txt_absen_perUser_jamMasuk);
            jamPulang = itemView.findViewById(R.id.txt_absen_perUser_jamPulang);
        }
    }
}
