package com.phongbm.diadiemvuichoi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemLongClickListener {
    private static final String TAG = "MainActivity";

    private RadioGroup radioDiaDiem;
    private ListView listViewDiaDiem;
    private DiaDiemDBManager diaDiemDBManager;
    private DiaDiemAdapter diaDiemAdapter;
    private ArrayList<DiaDiem> diaDiems;
    private int positionLongItemClick = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.initializeToolbar();
        this.initializeComponent();

        this.registerForContextMenu(listViewDiaDiem);

        diaDiemDBManager = new DiaDiemDBManager(this);
        diaDiems = diaDiemDBManager.getAllData();
        diaDiemAdapter = new DiaDiemAdapter(this, diaDiems);
        listViewDiaDiem.setAdapter(diaDiemAdapter);
    }

    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
    }

    private void initializeComponent() {
        listViewDiaDiem = (ListView) findViewById(R.id.listViewDiaDiem);
        listViewDiaDiem.setOnItemLongClickListener(this);
        radioDiaDiem = (RadioGroup) findViewById(R.id.radioDiaDiem);
        radioDiaDiem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioHN:
                        Log.i(TAG, "HN");
                        diaDiems.clear();
                        diaDiems = diaDiemDBManager.getHaNoiData();
                        diaDiemAdapter.setDiaDiems(diaDiems);
                        diaDiemAdapter.notifyDataSetChanged();
                        break;
                    case R.id.radioHCM:
                        Log.i(TAG, "HCM");
                        diaDiems.clear();
                        diaDiems = diaDiemDBManager.getHCMData();
                        diaDiemAdapter.setDiaDiems(diaDiems);
                        diaDiemAdapter.notifyDataSetChanged();
                        break;
                    case R.id.radioHNHCM:
                        Log.i(TAG, "HNHCM");
                        diaDiems.clear();
                        diaDiems = diaDiemDBManager.getAllData();
                        diaDiemAdapter.setDiaDiems(diaDiems);
                        diaDiemAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        positionLongItemClick = position;
        this.openContextMenu(parent);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        if (view.getId() == R.id.listViewDiaDiem) {
            menu.setHeaderTitle(diaDiems.get(positionLongItemClick).getTenDiaDiem());
            MenuInflater menuInflater = this.getMenuInflater();
            menuInflater.inflate(R.menu.menu_more_options, menu);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_xem_chi_tiet:
                break;
            case R.id.action_xem_ban_do:
                Uri gmmIntentUri = Uri.parse(
                        "geo:0,0?q=" + diaDiems.get(positionLongItemClick).getDiaChi());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                this.startActivity(mapIntent);
                break;
            default:
                super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}