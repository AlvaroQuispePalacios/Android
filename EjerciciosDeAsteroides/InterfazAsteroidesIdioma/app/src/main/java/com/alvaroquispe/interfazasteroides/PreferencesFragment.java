package com.alvaroquispe.interfazasteroides;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

public class PreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        final EditTextPreference fragments = (EditTextPreference)findPreference("fragments");
        fragments.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int valor;
                try {
                    valor = Integer.parseInt((String)newValue);
                } catch(Exception e) {
                    Toast.makeText(getActivity(), "Ha de ser un número", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if (valor>=0 && valor<=9) {
                    fragments.setSummary("En quants trossos es divideix un asteroide ("+valor+")");
                    return true;
                } else {
                    Toast.makeText(getActivity(), "Màxim de fragments 9", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
    }
}