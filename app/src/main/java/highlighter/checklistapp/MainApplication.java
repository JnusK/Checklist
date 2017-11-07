package highlighter.checklistapp;

import android.app.Application;
import android.util.Log;

/**
 * Created by Khorly on 2/11/17.
 */

public class MainApplication extends Application {
    public static final String TAG = MainApplication.class.getSimpleName();
    public static UserDAO userDAO;
    public static ChecklistDAO checklistDAO;
    public static int num = 0;


    @Override
    public void onCreate() {

        super.onCreate();
        if (num == 0) {
            userDAO = new UserDAO();
            userDAO.DBHandler(getApplicationContext());

            checklistDAO = new ChecklistDAO();
            checklistDAO.DBHandler(getApplicationContext());
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
