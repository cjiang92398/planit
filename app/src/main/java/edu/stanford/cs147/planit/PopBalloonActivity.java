package edu.stanford.cs147.planit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PopBalloonActivity extends AppCompatActivity {
    private BalloonPoppingView popper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_balloon);
        popper = (BalloonPoppingView) findViewById(R.id.balloonPoppingView);
        Balloon balloon1 = new Balloon("San Francisco", 100f, 100f, 300f, 300f);
        Balloon balloon2 = new Balloon("New York", 400f, 400f, 600f, 600f);
        List<Balloon> balloons = new ArrayList<Balloon>();
        balloons.add(balloon1);
        balloons.add(balloon2);
        popper.setBalloons(balloons);
        popper.invalidate();

        ImageButton undoButton = (ImageButton) findViewById(R.id.undo);
        undoButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                undo(popper.poppedBalloons.get(popper.poppedBalloons.size() - 1));
            }
        });

        undoButton.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v) {
                // implement list view
                List<String> poppedBalloonIdeas = new ArrayList<String>();
                for (int i = popper.poppedBalloons.size() - 1; i >= 0; i--) {
                    Balloon balloon = popper.poppedBalloons.get(i);
                    poppedBalloonIdeas.add(balloon.getIdea());
                }
                String[] poppedBalloonIdeasArray = poppedBalloonIdeas.toArray(new String[poppedBalloonIdeas.size()]);
                final ListView listView = (ListView) findViewById(R.id.list_view);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object listItem = listView.getItemAtPosition(position);
                        Balloon poppedBalloon = getBalloonByIdea(listItem.toString());
                        undo(poppedBalloon);
                    }
                });
                listView.setStackFromBottom(true);
                // STILL TRYING TO FIGURE OUT HOW TO FORMAT LIST VIEW
                ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item,poppedBalloonIdeasArray);
                listView.setAdapter(adapter);
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
        Intent intent = new Intent(this, FinalPlan.class);
        startActivity(intent);
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
