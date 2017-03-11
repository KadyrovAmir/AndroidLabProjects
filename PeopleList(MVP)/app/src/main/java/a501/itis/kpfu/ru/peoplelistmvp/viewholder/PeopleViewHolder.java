package a501.itis.kpfu.ru.peoplelistmvp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import a501.itis.kpfu.ru.peoplelistmvp.R;
import a501.itis.kpfu.ru.peoplelistmvp.interfaces.HumanClickListener;
import a501.itis.kpfu.ru.peoplelistmvp.model.Human;

/**
 * Created by Амир on 11.03.2017.
 */

public class PeopleViewHolder extends RecyclerView.ViewHolder {


    private TextView tvName;
    private TextView tvEmail;

    public PeopleViewHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.human_item_fullname);
        tvEmail = (TextView) itemView.findViewById(R.id.human_item_email);
    }

    public void bind(final Human human, final HumanClickListener listener) {
        if (human != null) {
            tvName.setText(human.getName() + " " + human.getSurname());
            tvEmail.setText(human.getEmail());
        }

        if (listener != null) {
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onHumanClick(human);
                }
            });
        }
    }
}
