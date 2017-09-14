package com.example.android.festafimdeano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.festafimdeano.constants.FimDeAnoConstants;
import com.example.android.festafimdeano.util.SecurityPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.text_today)
    TextView textToday;
    @BindView(R.id.text_days_left)
    TextView textDaysLeft;
    @BindView(R.id.button_confirm)
    Button buttonConfirm;

    private SecurityPreferences securityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        securityPreferences = new SecurityPreferences(this);
        buttonConfirm.setOnClickListener(this);
        veriticaPresenca();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
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
}
