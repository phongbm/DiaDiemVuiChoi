package com.phongbm.diadiemvuichoi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DiaDiemAdapter extends BaseAdapter {
    private static final String TAG = "DiaDiemAdapter";

    private LayoutInflater layoutInflater;
    private ArrayList<DiaDiem> diaDiems;

    public DiaDiemAdapter(Context context, ArrayList<DiaDiem> diaDiems) {
        this.diaDiems = diaDiems;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return diaDiems.size();
    }

    @Override
    public DiaDiem getItem(int position) {
        return diaDiems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_diadiem, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtTenDiaDiem = (TextView) convertView.findViewById(R.id.txtTenDiaDiem);
            viewHolder.txtDiaChi = (TextView) convertView.findViewById(R.id.txtDiaChi);
            viewHolder.imgLinkHinh = (ImageView) convertView.findViewById(R.id.imgLinkHinh);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtTenDiaDiem.setText(diaDiems.get(position).getTenDiaDiem());
        viewHolder.txtDiaChi.setText("Địa chỉ: " + diaDiems.get(position).getDiaChi());
        Picasso.with(parent.getContext())
                .load(diaDiems.get(position).getLinkHinh())
                .into(viewHolder.imgLinkHinh);
        return convertView;
    }

    class ViewHolder {
        TextView txtTenDiaDiem, txtDiaChi;
        ImageView imgLinkHinh;
    }

    public void setDiaDiems(ArrayList<DiaDiem> diaDiems) {
        this.diaDiems = diaDiems;
    }

}