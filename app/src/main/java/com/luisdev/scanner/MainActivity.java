package com.luisdev.scanner;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.luisdev.scanner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private TextView tv_mostrarDatos;

    // Configurar el lanzador de resultados
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher =
            registerForActivityResult(new ScanContract(), result -> {
                if (result.getContents() != null) {
                    // Mostrar el contenido escaneado
                    tv_mostrarDatos.setText(result.getContents());
                } else {
                    tv_mostrarDatos.setText("No se detectó ningún código.");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazar el TextView
        this.tv_mostrarDatos = findViewById(R.id.text);
    }

    // Metodo onclick para iniciar el escaneo
    public void onclick(View view) {
        if (view.getId() == R.id.AbrirScanner) {
            // Configurar opciones de escaneo
            ScanOptions options = new ScanOptions();
            options.setPrompt("Coloca el código de barras dentro del marco");
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            barcodeLauncher.launch(options); // Iniciar el escaneo
        }
    }
}
