package com.example.android.festafimdeano;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.festafimdeano.constants.FimDeAnoConstants;
import com.example.android.festafimdeano.util.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.text_today)
    TextView textToday;
    @BindView(R.id.text_days_left)
    TextView textDaysLeft;
    @BindView(R.id.button_confirm)
    Button buttonConfirm;

    private SecurityPreferences securityPreferences;
    //criando o formato da dat
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        securityPreferences = new SecurityPreferences(this);
        buttonConfirm.setOnClickListener(this);
        //setando a data de hoje no textToday
        textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String diasFaltando = String.format("%s %s", String.valueOf(getCalculaDiasFinalAno()),
                getString(R.string.dias));
        textDaysLeft.setText(diasFaltando);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
        veriticaPresenca();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void veriticaPresenca() {
        String presence = securityPreferences.getStoreString(FimDeAnoConstants.PRESENCE);
        if (presence.isEmpty()) {
            buttonConfirm.setText(R.string.nao_confirmado);
        } else if (presence.equals(FimDeAnoConstants.CONFIRMED)) {
            buttonConfirm.setText(R.string.sim);
        } else if (presence.equals(FimDeAnoConstants.NAO_CONFIRMED)) {
            buttonConfirm.setText(R.string.nao);
        }
    }

    @Override
    public void onClick(View view) {
        String presence = securityPreferences.getStoreString(FimDeAnoConstants.PRESENCE);
        if(view.getId() == R.id.button_confirm){
            Intent intent = new Intent(this, DetalhesActivity.class);

            intent.putExtra(FimDeAnoConstants.PRESENCE,presence);

            startActivity(intent);
        }
    }

    public int getCalculaDiasFinalAno(){
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);


        Calendar calendarUltimoDia= Calendar.getInstance();
        int ultimoDia = calendarUltimoDia.getActualMaximum(Calendar.DAY_OF_YEAR);
        return ultimoDia - today;
    }
}
