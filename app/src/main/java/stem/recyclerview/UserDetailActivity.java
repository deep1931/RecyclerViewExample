package stem.recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import stem.recyclerview.db.DBAdapter;
import stem.recyclerview.listadapter.UserDetailAdapter;
import stem.recyclerview.model.UserModel;

public class UserDetailActivity extends AppCompatActivity {

    private Context context;
    private DBAdapter dbAdapter;
    private RecyclerView recyclerView;
    private UserDetailAdapter mAdapter;

    private ArrayList<UserModel> alUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        init();
    }

    private void init() {
        context = this;
        dbAdapter = new DBAdapter(context);
        dbAdapter.open();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new UserDetailAdapter(this, alUsers);

        //set the layout of list
        mAdapter.setLayout(R.layout.items_grid);

        //initialize layout manager as per need, (linear, grid etc)
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        setListValues();

    }

    private void setListValues() {
        Cursor cursor = dbAdapter.getAllUser();


        int count = cursor.getCount();
        cursor.moveToFirst();

        if (count > 0) {
            final ArrayList<UserModel> alUsers = new ArrayList<>();

            for (int i = 0; i < count; i++) {

                String email_id = cursor.getString(1);
                String password = cursor.getString(2);


                alUsers.add(new UserModel(email_id, password));
                cursor.moveToNext();
            }

            //set the latest array list
            mAdapter.setData(alUsers);

        } else {
            Toast.makeText(UserDetailActivity.this, "No Data Found !", Toast.LENGTH_SHORT).show();
        }
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_type, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {


            case R.id.action_listview:

                initListDisplay();
                break;

            case R.id.action_gridview:

                initGridDisplay();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    // Display a list
    private void initListDisplay() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setLayout(R.layout.list_items);
    }

    // Display the Grid
    private void initGridDisplay() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setLayout(R.layout.items_grid);


    }
}
