package stem.recyclerview.listadapter;

/**
 * Created by sandeep on 8/27/16.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import stem.recyclerview.R;
import stem.recyclerview.model.UserModel;


// here's our beautiful adapter
public class ListItemAdapter extends ArrayAdapter<UserModel> {

    Context mContext;
    int layoutResourceId;
    ArrayList<UserModel> data = null;

    public ListItemAdapter(Context mContext, int layoutResourceId, ArrayList<UserModel> data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        UserModel objectItem = data.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView txtEmail = (TextView) convertView.findViewById(R.id.txtEmail);
        txtEmail.setText(objectItem.getEmailId());

        TextView txtPassword = (TextView) convertView.findViewById(R.id.txtPassword);
        txtPassword.setText(objectItem.getPassword());

        return convertView;

    }


}