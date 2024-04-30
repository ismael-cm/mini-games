package com.example.minijuego;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> cardArray = new ArrayList<>();
    private int flippedCount = 0;
    private int firstCardIndex = -1;
    private int secondCardIndex = -1;
    private int matchedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shuffleImages(); // Mezcla aleatoria de las imágenes

        GridView gridView = findViewById(R.id.gridView);
        final MemoryCardAdapter adapter = new MemoryCardAdapter(this, cardArray);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flippedCount == 0) {
                    firstCardIndex = position;
                    adapter.flipCard(position);
                    flippedCount++;
                } else if (flippedCount == 1) {
                    secondCardIndex = position;
                    adapter.flipCard(position);
                    flippedCount++;

                    if (cardArray.get(firstCardIndex).equals(cardArray.get(secondCardIndex))) {
                        matchedCount += 2;
                        if (matchedCount == cardArray.size()) {
                            // Todos los pares coinciden, el juego ha terminado
                            Toast.makeText(MainActivity.this, "¡Felicidades has avanzado de nivel!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // No coinciden, dar la vuelta después de un breve retraso
                        gridView.setEnabled(false);
                        gridView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                adapter.flipCard(firstCardIndex);
                                adapter.flipCard(secondCardIndex);
                                gridView.setEnabled(true);
                            }
                        }, 1000);
                    }
                    flippedCount = 0;
                }
            }
        });
    }

    // Método para mezclar aleatoriamente las imágenes
    private void shuffleImages() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.card1);
        images.add(R.drawable.card2);
        images.add(R.drawable.card3);
        images.add(R.drawable.card4);

        // Agrega más imágenes aquí

        cardArray.clear();
        for (Integer image : images) {
            cardArray.add(image);
            cardArray.add(image); // Cada imagen aparece dos veces (un par)
        }
        Collections.shuffle(cardArray); // Mezcla aleatoriamente el array
    }
}

