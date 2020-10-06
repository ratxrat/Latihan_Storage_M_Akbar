package ratsoft.android.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;



public class ExternalStorage extends AppCompatActivity implements View.OnClickListener {
    public  static  final  String FILENAME = "externalfile.txt";
    Button buat,edit,hapus,baca;
    TextView textBaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        getSupportActionBar().setTitle(getString(R.string.external_storage));
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
        File file = new File(getExternalFilesDir(null),FILENAME);

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
        String isiFile = "File Di buat di External Storage ! ";
        String state = Environment.getExternalStorageState();
        if(!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }

        File file = new File(getExternalFilesDir(null),FILENAME);

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
        File file = new File(getExternalFilesDir(null),FILENAME);
        if(file.exists()){

            file.delete();
            Toast.makeText(getApplicationContext(),"File Sudah DI hapus",Toast.LENGTH_SHORT).show();
        }else {
            textBaca.setText(getString(R.string.tidak_ditemukan));
        }

    }
    void bacaFile() {
        File file = new File(getExternalFilesDir(null),FILENAME);

        if(file.exists()){
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                textBaca.setText(text.toString());
                br.close();
            }catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}