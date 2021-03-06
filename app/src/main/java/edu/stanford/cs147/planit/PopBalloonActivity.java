package edu.stanford.cs147.planit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class PopBalloonActivity extends AppCompatActivity {
    public static ArrayList<Balloon> balloons = new ArrayList<Balloon>();
    private BalloonPoppingView popper;
    private static boolean stateSaved = false;
    private int numLongPress = 0;
    private AlertDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_balloon);
        popper = (BalloonPoppingView) findViewById(R.id.balloonPoppingView);

        if(stateSaved == false) {
            BitmapDrawable p39balloonDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.p39balloon);
            Balloon p39balloon = new Balloon("Pier 39", p39balloonDrawable, 198f, 45f,"p39details");

            BitmapDrawable aballoonDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.aballoon);
            Balloon aballoon = new Balloon("Alcatraz", aballoonDrawable, 703f, 119f, "adetails");

            BitmapDrawable leballoonDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.leballoon);
            Balloon leballoon = new Balloon("Land's End", leballoonDrawable, 530f, 0f, "ledetails");

            BitmapDrawable caballoonDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.caballoon);
            Balloon caballoon = new Balloon("Clarion Alley", caballoonDrawable, 193f, 455f, "cadetails");

            BitmapDrawable lsballoonDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.lsballoon);
            Balloon lsballoon = new Balloon("Lombard Street", lsballoonDrawable, 179f, 763f, "lsdetails");

            BitmapDrawable attballoonDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.attballoon);
            Balloon attballoon = new Balloon("AT&T Park", attballoonDrawable, 702f, 460f, "attdetails");

            BitmapDrawable bgballoonDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.bgballoon);
            Balloon bgballoon = new Balloon("Boba Guys", bgballoonDrawable, 698f, 685f, "bgdetails");

            BitmapDrawable fwballoonDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.fwballoon);
            Balloon fwballoon = new Balloon("Fisherman's Wharf", fwballoonDrawable, 483f, 275f, "fwdetails");

            BitmapDrawable ggbballoonDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ggbballoon);

            Balloon ggbballoon = new Balloon("Golden Gate", ggbballoonDrawable, 500f, 658f, "ggbdetails");

            balloons.add(p39balloon);
            balloons.add(aballoon);
            balloons.add(leballoon);
            balloons.add(caballoon);
            balloons.add(lsballoon);
            balloons.add(attballoon);
            balloons.add(bgballoon);
            balloons.add(fwballoon);
            balloons.add(ggbballoon);

        } else {
            PopBalloonActivity.balloons.clear();
            for(int i = 0; i < BalloonPoppingView.savedBalloons.size(); i++) {
                PopBalloonActivity.balloons.add(BalloonPoppingView.savedBalloons.get(i));
            }
        }

        popper.setMap();
        popper.setBalloons(balloons);
        popper.invalidate();

        final ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item,new ArrayList<String>());
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setVisibility(View.INVISIBLE);

        ImageButton undoButton = (ImageButton) findViewById(R.id.undo);

        undoButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (!popper.poppedBalloons.isEmpty()) {
                    undo(popper.poppedBalloons.get(popper.poppedBalloons.size() - 1));
                }
                listView.setVisibility(View.INVISIBLE);
            }
        });

        undoButton.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v) {
                numLongPress++;
                if (!popper.poppedBalloons.isEmpty()) {
                    if (numLongPress % 2 == 1) {
                        listView.setVisibility(View.VISIBLE);
                        ((ArrayAdapter) adapter).clear();
                        for (int i = 0; i < popper.poppedBalloons.size(); i++) {
                            Balloon balloon = popper.poppedBalloons.get(i);
                            ((ArrayAdapter) adapter).add((Object) balloon.getIdea());
                        }

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Object listItem = listView.getItemAtPosition(position);
                                Balloon poppedBalloon = getBalloonByIdea(listItem.toString());
                                undo(poppedBalloon);
                                listView.setVisibility(View.INVISIBLE);
                                numLongPress++;
                            }
                        });
                        listView.setAdapter(adapter);
                    } else {
                        listView.setVisibility(View.INVISIBLE);
                    }
                }
                return true;
            }
        });
    }

    public void undo(Balloon currBalloon) {
        if (!popper.poppedBalloons.isEmpty()) {
            popper.poppedBalloons.remove(currBalloon);
            popper.retrievedBalloons.add(currBalloon);
            currBalloon.retrieveBalloon();
            popper.invalidate();
        }
    }

    public void redo(View view) {
        if (!popper.retrievedBalloons.isEmpty()) {
            popper.currBalloon = popper.retrievedBalloons.get(popper.retrievedBalloons.size() - 1);
            popper.retrievedBalloons.remove(popper.currBalloon);
            popper.popBalloon();
        }
    }

    public void done(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = ((Activity) this).getLayoutInflater();
        View layout = inflater.inflate(R.layout.confirmation, null);
        builder.setView(layout);
        dialog = builder.show();
        dialog.getWindow().setLayout(1200, 962);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void cancel(View view) {
        dialog.dismiss();
    }

    public void yes(View view) {
        BalloonPoppingView.savedBalloons.clear();

        for(int i = 0; i < popper.balloons.size(); i++){
            boolean saveIdea = true;
            for(int j = 0; j < popper.poppedBalloons.size(); j++){
                if(popper.balloons.get(i).getIdea()
                        .equals(popper.poppedBalloons.get(j).getIdea())){
                    saveIdea = false;
                }
            }
            if(saveIdea == true) {
                popper.savedBalloons.add(balloons.get(i));
            }
        }

        stateSaved = true;
        Intent intent = new Intent(this, FinalPlan.class);
        startActivity(intent);
    }

    public void goHome(View view) {
        if (Boolean.toString(UpdatedHomePage.updatedHomeStarted).equals("false")) {
            startActivity(new Intent(PopBalloonActivity.this, Home.class));
        } else {
            startActivity(new Intent(PopBalloonActivity.this, UpdatedHomePage.class));
        }
    }

    public Balloon getBalloonByIdea(String idea) {
        for (Balloon balloon : popper.balloons) {
            if (balloon.getIdea().equals(idea)) {
                return balloon;
            }
        }
        return null;
    }
}
