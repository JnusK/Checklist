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


    @Override
    public void onCreate() {
        super.onCreate();
        userDAO = new UserDAO();
        userDAO.DBHandler(getApplicationContext());

        checklistDAO = new ChecklistDAO();
        checklistDAO.DBHandler(getApplicationContext());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
