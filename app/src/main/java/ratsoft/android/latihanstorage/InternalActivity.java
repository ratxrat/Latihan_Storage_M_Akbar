package ratsoft.android.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
//import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {

    public  static  final  String FILENAME = "namafile.txt";
    Button buat,edit,hapus,baca;
    TextView textBaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        getSupportActionBar().setTitle(getString(R.string.internal_storage));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buat = findViewById(R.id.btnBuatFile);
        edit = findViewById(R.id.btnUbahFile);
        hapus = findViewById(R.id.btnHapusFile);
        baca = findViewById(R.id.btnBacaFile);
        textBaca = findViewById(R.id.textBaca);

        buat.setOnClickListener(this);
        edit.setOnClickListener(this);
        hapus.setOnClickListener(this);
        baca.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        jalankanPerintah(v.getId());
    }

    private void jalankanPerintah(int id) {
        switch (id){
            case R.id.btnBuatFile :
                buatFile();
                break;
            case R.id.btnHapusFile:
                hapusFile();
                break;
            case R.id.btnUbahFile :
                ubahFile();
                break;
            case R.id.btnBacaFile:
                bacaFile();
                break;
         }
    }
    void  ubahFile(){
        String isiFile = "\n File Telah Di ubah";
        File file = new File(getFilesDir(),FILENAME);

        FileOutputStream outputStream ;

        try {

            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     void buatFile(){
        String isiFile = "Coba Isi Fata File Text";
        File file = new File(getFilesDir(),FILENAME);

        FileOutputStream outputStream ;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     void hapusFile(){
        File file = new File(getFilesDir(),FILENAME);
        if(file.exists()){
            file.delete();
            Toast.makeText(getApplicationContext(),"File Sudah DI hapus",Toast.LENGTH_SHORT).show();
        }else {
            textBaca.setText(getString(R.string.tidak_ditemukan));
        }

    }
    void bacaFile() {
        FileInputStream fi ;
        try {
            fi = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fi);
            BufferedReader buffer = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String data;
            while ((data = buffer.readLine()) != null) {
                sb.append(data);
            }
            textBaca.setText(sb.toString());
            fi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}