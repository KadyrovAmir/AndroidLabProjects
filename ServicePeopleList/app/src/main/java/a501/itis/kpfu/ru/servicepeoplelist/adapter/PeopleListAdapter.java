package a501.itis.kpfu.ru.servicepeoplelist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import a501.itis.kpfu.ru.servicepeoplelist.R;
import a501.itis.kpfu.ru.servicepeoplelist.api.People;
import a501.itis.kpfu.ru.servicepeoplelist.interfaces.PeopleClickListener;

/**
 * Created by Амир on 13.03.2017.
 */

public class PeopleListAdapter extends RecyclerView.Adapter<PeopleListAdapter.PeopleListViewHolder> {
    List<People> peopleList;
    PeopleClickListener listener;
    Context mContext;

    public PeopleListAdapter(List<People> peopleList, Context mContext) {
        this.peopleList = peopleList;
        this.mContext = mContext;
    }

    @Override
    public PeopleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_item, parent, false);
        return new PeopleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleListViewHolder holder, final int position) {
        People human = peopleList.get(position);
        holder.bind(human, listener);
        Picasso.with(mContext)
                .load(human.getPicture().getThumbnail())
                .into(holder.photo);
        holder.fullName.setText(human.getName().getFirst() + " " + human.getName().getLast());
    }

    public void setListener(PeopleClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class PeopleListViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView fullName;

        public PeopleListViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.people_image);
            fullName = (TextView) itemView.findViewById(R.id.people_fullname);
        }

        public void bind(final People human, final PeopleClickListener peopleClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    peopleClickListener.onHumanClick(human);
                }
            });
        }
    }
}
