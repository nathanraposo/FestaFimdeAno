package com.example.android.festafimdeano;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.example.android.festafimdeano.constants.FimDeAnoConstants;
import com.example.android.festafimdeano.util.SecurityPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.check_participate)
    CheckBox checkParticipate;

    private SecurityPreferences securityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        ButterKnife.bind(this);

        securityPreferences = new SecurityPreferences(this);

        checkParticipate.setOnClickListener(this);
        carregaDados();
    }

    private void carregaDados() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String presences = extras.getString(FimDeAnoConstants.PRESENCE);
            if(presences.equals(FimDeAnoConstants.CONFIRMED)){
                checkParticipate.setChecked(true);
            }else{
                checkParticipate.setChecked(false);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.check_participate){
            if(checkParticipate.isChecked()){
              securityPreferences.storeString(FimDeAnoConstants.PRESENCE,FimDeAnoConstants.CONFIRMED);
            }else{
                securityPreferences.storeString(FimDeAnoConstants.PRESENCE,FimDeAnoConstants.NAO_CONFIRMED);
            }
        }
    }
}
