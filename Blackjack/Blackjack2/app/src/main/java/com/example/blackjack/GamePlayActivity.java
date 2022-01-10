package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class GamePlayActivity extends AppCompatActivity {

    ImageView house1, house2, house3, house4, house5, housecover1, housecover2, housecover3, housecover4, housecover5,
            player1, player2, player3, player4, player5, playercover1, playercover2, playercover3, playercover4, playercover5;

    TextView coins, scoreplayer, scorehouse;
    Button hit, stay, retry, exit;

    ArrayList<Card> cardArrayList, housePlayCardsList, playerPlayCardsList;

    int playerscores = 0, housescores = 0, playerLastPosition = -1, houseLastPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        initi();
        clickListeners();

        startGame();

    }

    private void startGame() {
    //initialized arrays for house/player cards
        housePlayCardsList = new ArrayList<>();
        playerPlayCardsList = new ArrayList<>();
        
        hit.setEnabled(true);
        stay.setEnabled(true);
        
        playercover1.setVisibility(View.VISIBLE);
        playercover2.setVisibility(View.VISIBLE);
        playercover3.setVisibility(View.VISIBLE);
        playercover4.setVisibility(View.VISIBLE);
        playercover5.setVisibility(View.VISIBLE);
        housecover2.setVisibility(View.VISIBLE);
        housecover3.setVisibility(View.VISIBLE);
        housecover4.setVisibility(View.VISIBLE);
        housecover5.setVisibility(View.VISIBLE);

        getCardList();

        for (Card card : getCardList()) {
            Log.e("Cards", String.valueOf(card.value));
        }

        Collections.shuffle(cardArrayList);
        Collections.shuffle(cardArrayList);
        Collections.shuffle(cardArrayList);

        for (int i = 0; i <= 4; i++) {
            housePlayCardsList.add(cardArrayList.get(i));
        }

        for (int i = 5; i <= 9; i++) {
            playerPlayCardsList.add(cardArrayList.get(i));
        }

        for (Card card : cardArrayList) {
            Log.e("Cards Shuffled", String.valueOf(card.value));
        }

        house1.setImageDrawable(getResources().getDrawable(housePlayCardsList.get(0).getImage()));
        house2.setImageDrawable(getResources().getDrawable(housePlayCardsList.get(1).getImage()));
        house3.setImageDrawable(getResources().getDrawable(housePlayCardsList.get(2).getImage()));
        house4.setImageDrawable(getResources().getDrawable(housePlayCardsList.get(3).getImage()));
        house5.setImageDrawable(getResources().getDrawable(housePlayCardsList.get(4).getImage()));

        player1.setImageDrawable(getResources().getDrawable(playerPlayCardsList.get(0).getImage()));
        player2.setImageDrawable(getResources().getDrawable(playerPlayCardsList.get(1).getImage()));
        player3.setImageDrawable(getResources().getDrawable(playerPlayCardsList.get(2).getImage()));
        player4.setImageDrawable(getResources().getDrawable(playerPlayCardsList.get(3).getImage()));
        player5.setImageDrawable(getResources().getDrawable(playerPlayCardsList.get(4).getImage()));

        housecover1.setVisibility(View.GONE);

        housescores = housescores + housePlayCardsList.get(houseLastPosition).getValue();
        playerscores = 0;

        scorehouse.setText("House Scores: " + String.valueOf(housescores));
        scoreplayer.setText(getIntent().getStringExtra("name") + " Scores: " + String.valueOf(playerscores));
    }

    private void initi() {
        player1 = findViewById(R.id.imageview_player_1);
        player2 = findViewById(R.id.imageview_player_2);
        player3 = findViewById(R.id.imageview_player_3);
        player4 = findViewById(R.id.imageview_player_4);
        player5 = findViewById(R.id.imageview_player_5);
        playercover1 = findViewById(R.id.imageview_player_cover_1);
        playercover2 = findViewById(R.id.imageview_player_cover_2);
        playercover3 = findViewById(R.id.imageview_player_cover_3);
        playercover4 = findViewById(R.id.imageview_player_cover_4);
        playercover5 = findViewById(R.id.imageview_player_cover_5);
        house1 = findViewById(R.id.imageview_house_1);
        house2 = findViewById(R.id.imageview_house_2);
        house3 = findViewById(R.id.imageview_house_3);
        house4 = findViewById(R.id.imageview_house_4);
        house5 = findViewById(R.id.imageview_house_5);
        housecover1 = findViewById(R.id.imageview_house_cover_1);
        housecover2 = findViewById(R.id.imageview_house_cover_2);
        housecover3 = findViewById(R.id.imageview_house_cover_3);
        housecover4 = findViewById(R.id.imageview_house_cover_4);
        housecover5 = findViewById(R.id.imageview_house_cover_5);
        coins = findViewById(R.id.textview_coins);
        scoreplayer = findViewById(R.id.textview_score_player);
        scorehouse = findViewById(R.id.textview_score_house);
        hit = findViewById(R.id.button_hit);
        stay = findViewById(R.id.button_stay);
        retry = findViewById(R.id.button_retry);
        exit = findViewById(R.id.button_exit);

    }

    private void clickListeners() {

        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int housepos = houseLastPosition; housepos < 5; housepos++) {

                    if (housepos < 4) {
                        housescores = housescores + housePlayCardsList.get(housepos + 1).getValue();

                        switch (housepos + 1) {
                            case 1: {
                                housecover2.setVisibility(View.GONE);
                                break;
                            }
                            case 2: {
                                housecover3.setVisibility(View.GONE);
                                break;
                            }
                            case 3: {
                                housecover4.setVisibility(View.GONE);
                                break;
                            }
                            case 4: {
                                housecover5.setVisibility(View.GONE);
                                break;
                            }
                        }

                        scorehouse.setText("House Scores: " + String.valueOf(housescores));

//                        houseLastPosition = houseLastPosition + 1;

                        if (housescores > 21) {
                            Toast.makeText(GamePlayActivity.this, "House Bust!", Toast.LENGTH_SHORT).show();
                            hit.setEnabled(false);hit.setEnabled(false);
                            stay.setEnabled(false);
                            break;
                        }

                    }
                }
            }
        });

        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerLastPosition == -1) {
                    playerscores = playerscores + playerPlayCardsList.get(0).getValue();
                    playerscores = playerscores + playerPlayCardsList.get(1).getValue();

                    playercover1.setVisibility(View.GONE);
                    playercover2.setVisibility(View.GONE);

//                    scoreplayer.setText("Player: "+String.valueOf(playerscores));
                    scoreplayer.setText(getIntent().getStringExtra("name") + " Scores: " + String.valueOf(playerscores));

                    playerLastPosition = 1;

                } else {
                    if (playerLastPosition < 4) {
                        playerscores = playerscores + playerPlayCardsList.get(playerLastPosition + 1).getValue();

                        switch (playerLastPosition + 1) {
                            case 2: {
                                playercover3.setVisibility(View.GONE);
                                break;
                            }
                            case 3: {
                                playercover4.setVisibility(View.GONE);
                                break;
                            }
                            case 4: {
                                playercover5.setVisibility(View.GONE);
                                break;
                            }
                        }

                        scoreplayer.setText(getIntent().getStringExtra("name") + ": " + String.valueOf(playerscores));

                        playerLastPosition = playerLastPosition + 1;

                        if (playerscores > 21) {
                            Toast.makeText(GamePlayActivity.this, getIntent().getStringExtra("name") + " Bust!", Toast.LENGTH_SHORT).show();
                            hit.setEnabled(false);
                            stay.setEnabled(false);
                        }

                    }

                }
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playerscores = 0;
                housescores = 0;
                playerLastPosition = -1;
                houseLastPosition = 0;



                startGame();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private ArrayList<Card> getCardList() {

        cardArrayList = new ArrayList<>();

        cardArrayList.add(new Card("Ace of Clubs", 11, R.drawable.ace_of_clubs));
        cardArrayList.add(new Card("Ace of Diamonds", 11, R.drawable.ace_of_diamonds));
        cardArrayList.add(new Card("Ace of Hearts", 11, R.drawable.ace_of_hearts));
        cardArrayList.add(new Card("Ace of Spades", 11, R.drawable.ace_of_spades));
        cardArrayList.add(new Card("King of Clubs", 10, R.drawable.king_of_clubs));
        cardArrayList.add(new Card("King of Diamonds", 10, R.drawable.king_of_diamonds));
        cardArrayList.add(new Card("King of Hearts", 10, R.drawable.king_of_hearts));
        cardArrayList.add(new Card("King of Spades", 10, R.drawable.king_of_spades));
        cardArrayList.add(new Card("Queen of Clubs", 10, R.drawable.queen_of_clubs));
        cardArrayList.add(new Card("Queen of Diamonds", 10, R.drawable.queen_of_diamonds));
        cardArrayList.add(new Card("Queen of Hearts", 10, R.drawable.queen_of_hearts));
        cardArrayList.add(new Card("Queen of Spades", 10, R.drawable.queen_of_spades));
        cardArrayList.add(new Card("Jack of Clubs", 10, R.drawable.jack_of_clubs));
        cardArrayList.add(new Card("Jack of Diamonds", 10, R.drawable.jack_of_diamonds));
        cardArrayList.add(new Card("Jack of Hearts", 10, R.drawable.jack_of_hearts));
        cardArrayList.add(new Card("Jack of Spades", 10, R.drawable.jack_of_spades));
        cardArrayList.add(new Card("Ten of Clubs", 10, R.drawable.ten_of_clubs));
        cardArrayList.add(new Card("Ten of Diamonds", 10, R.drawable.ten_of_diamonds));
        cardArrayList.add(new Card("Ten of Hearts", 10, R.drawable.ten_of_hearts));
        cardArrayList.add(new Card("Ten of Spades", 10, R.drawable.ten_of_spades));
        cardArrayList.add(new Card("Nine of Clubs", 9, R.drawable.nine_of_clubs));
        cardArrayList.add(new Card("Nine of Diamonds", 9, R.drawable.nine_of_diamonds));
        cardArrayList.add(new Card("Nine of Hearts", 9, R.drawable.nine_of_hearts));
        cardArrayList.add(new Card("Nine of Spades", 9, R.drawable.nine_of_spades));
        cardArrayList.add(new Card("Eight of Clubs", 8, R.drawable.eight_of_clubs));
        cardArrayList.add(new Card("Eight of Diamonds", 8, R.drawable.eight_of_diamonds));
        cardArrayList.add(new Card("Eight of Hearts", 8, R.drawable.eight_of_hearts));
        cardArrayList.add(new Card("Eight of Spades", 8, R.drawable.eight_of_spades));
        cardArrayList.add(new Card("Seven of Clubs", 7, R.drawable.seven_of_clubs));
        cardArrayList.add(new Card("Seven of Diamonds", 7, R.drawable.seven_of_diamonds));
        cardArrayList.add(new Card("Seven of Hearts", 7, R.drawable.seven_of_hearts));
        cardArrayList.add(new Card("Seven of Spades", 7, R.drawable.seven_of_spades));
        cardArrayList.add(new Card("Six of Clubs", 6, R.drawable.six_of_clubs));
        cardArrayList.add(new Card("Six of Diamonds", 6, R.drawable.six_of_diamonds));
        cardArrayList.add(new Card("Six of Hearts", 6, R.drawable.six_of_hearts));
        cardArrayList.add(new Card("Six of Spades", 6, R.drawable.six_of_spades));
        cardArrayList.add(new Card("Five of Clubs", 5, R.drawable.five_of_clubs));
        cardArrayList.add(new Card("Five of Diamonds", 5, R.drawable.five_of_diamonds));
        cardArrayList.add(new Card("Five of Hearts", 5, R.drawable.five_of_hearts));
        cardArrayList.add(new Card("Five of Spades", 5, R.drawable.five_of_spades));
        cardArrayList.add(new Card("Four of Clubs", 4, R.drawable.four_of_clubs));
        cardArrayList.add(new Card("Four of Diamonds", 4, R.drawable.four_of_diamonds));
        cardArrayList.add(new Card("Four of Hearts", 4, R.drawable.four_of_hearts));
        cardArrayList.add(new Card("Four of Spades", 4, R.drawable.four_of_spades));
        cardArrayList.add(new Card("Three of Clubs", 3, R.drawable.three_of_clubs));
        cardArrayList.add(new Card("Three of Diamonds", 3, R.drawable.three_of_diamonds));
        cardArrayList.add(new Card("Three of Hearts", 3, R.drawable.three_of_hearts));
        cardArrayList.add(new Card("Three of Spades", 3, R.drawable.three_of_spades));
        cardArrayList.add(new Card("Two of Clubs", 2, R.drawable.two_of_clubs));
        cardArrayList.add(new Card("Two of Diamonds", 2, R.drawable.two_of_diamonds));
        cardArrayList.add(new Card("Two of Hearts", 2, R.drawable.two_of_hearts));
        cardArrayList.add(new Card("Two of Spades", 2, R.drawable.two_of_spades));

        return cardArrayList;

    }
}