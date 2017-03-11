package a501.itis.kpfu.ru.peoplelistmvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a501.itis.kpfu.ru.peoplelistmvp.R;
import a501.itis.kpfu.ru.peoplelistmvp.interfaces.HumanClickListener;
import a501.itis.kpfu.ru.peoplelistmvp.model.Human;
import a501.itis.kpfu.ru.peoplelistmvp.viewholder.PeopleViewHolder;

/**
 * Created by Амир on 08.03.2017.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder> {

    private List<Human> list;
    private HumanClickListener listener;

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.human_item, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        Human human = list.get(position);
        if (human != null) {
            holder.bind(human, this.listener);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public HumanClickListener getListener() {
        return listener;
    }

    public void setListener(HumanClickListener listener) {
        this.listener = listener;
    }

    public List<Human> getList() {
        return list;
    }

    public void setList(List<Human> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
