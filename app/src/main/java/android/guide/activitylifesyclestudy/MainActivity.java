package android.guide.activitylifesyclestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
int teamAS, teamBS;
Button addScorA, addScorB, showNot;
TextView TAS, TBS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamAS=0;
        teamBS =0;

        addScorA = findViewById(R.id.TABV);
        addScorB = findViewById(R.id.TBBV);
        TAS = findViewById(R.id.TATV);
        TBS = findViewById(R.id.TBTV);
        showNot = findViewById(R.id.SHOWNOTBTN);



//        if(teamAS>0){
//            TAS.setText(teamAS);
//        }
//        if (teamBS>0){
//            TBS.setText(teamBS);
//        }


        addScorA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAS.setText(Integer.toString(teamAS+=1));
            }
        });
        addScorB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TBS.setText(Integer.toString(teamBS+=1));
            }
        });


        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("my notification", "my notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        if(teamAS==10){

            Context context = getApplicationContext();
            Intent intent = new Intent(this, WinnerService.class); // Build the intent for the service
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(this, 0, intent, 0);

            Notification notification =
                    new Notification.Builder(this, "my notification")
                            .setContentTitle(getText(R.string.winnerName))
                            .setContentText(getText(R.string.winnerName))
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentIntent(pendingIntent)
                            .setTicker(getText(R.string.teamA))
                            .build();

// Notification ID cannot be 0.

            startActivity(intent);






        }


        showNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"my notification");
                builder.setContentTitle(getText(R.string.winnerName));
                builder.setContentText(getText(R.string.winnerName));
                builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1,builder.build());
            }
        });







    }
}