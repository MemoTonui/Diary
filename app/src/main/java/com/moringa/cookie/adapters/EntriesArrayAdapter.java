package com.moringa.cookie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.cookie.R;
import com.moringa.cookie.models.Entries;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntriesArrayAdapter extends RecyclerView.Adapter<EntriesArrayAdapter.EntriesViewHolder> {

    Context mContext;
    private List<Entries> mEntries;

    public EntriesArrayAdapter(Context mContext, List<Entries> mEntries) {
        this.mContext = mContext;
        this.mEntries = mEntries;
    }



    @NonNull
    @Override
    public EntriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entries_list, parent, false);
        EntriesViewHolder viewHolder = new EntriesViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(EntriesArrayAdapter.EntriesViewHolder holder, int position) {
        holder.bindEntries(mEntries.get(position));
    }

    @Override
    public int getItemCount() {
        return mEntries.size();
    }

    public class EntriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.moodDisplay)
        TextView mMoodDisplay;
        @BindView(R.id.descriptionDisplay)
        TextView mDescriptionDisplay;
        private Context context;

        public EntriesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            context = view.getContext();

        }
        public void bindEntries(Entries entries) {
            mMoodDisplay.setText(entries.getMood().toString());
            mDescriptionDisplay.setText(entries.getDescription().toString());
        }
    }
}


