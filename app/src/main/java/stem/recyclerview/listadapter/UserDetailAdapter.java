package stem.recyclerview.listadapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import stem.recyclerview.R;
import stem.recyclerview.model.UserModel;

/**
 * Created by sandeep on 9/13/16.
 */
public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.MyViewHolder> {


    private ArrayList<UserModel> list;
    private final Activity activity;
    private int layout;

    public UserDetailAdapter(Activity activity, ArrayList<UserModel> list) {
        this.activity = activity;
        this.list = list;

    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtEmail;
        private final TextView txtPassword;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            txtPassword = (TextView) itemView.findViewById(R.id.txtPassword);
        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserDetailAdapter.MyViewHolder holder, int position) {

        UserModel userModel = list.get(position);
        holder.txtEmail.setText(userModel.getEmailId());
        holder.txtPassword.setText(userModel.getPassword());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<UserModel> list) {
        this.list = list;
        notifyItemRangeInserted(0, this.list.size());
    }

}
