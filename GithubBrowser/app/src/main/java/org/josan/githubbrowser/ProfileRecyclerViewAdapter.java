package org.josan.githubbrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewHolder> {
    private static final String LOG_TAG = ProfileRecyclerViewAdapter.class.getSimpleName();

    private List<Profile> mProfileList;
    private Context mContext;

    public ProfileRecyclerViewAdapter(Context context, List<Profile> profileList) {
        mProfileList = profileList;
        mContext = context;
    }

    @Override
    public ProfileRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.browse, null, false);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(lp);

        ProfileRecyclerViewHolder profileRecyclerViewHolder =
                new ChampionRecyclerViewHolder(view);

        return championRecyclerViewHolder;
    }

    @Override
    public int getItemCount() {
        return (mProfileList != null ? mProfileList.size() : 0);
    }

    @Override
    public void onBindViewHolder(ProfileRecyclerViewHolder holder, int position) {
        Profile profileItem = mProfileList.get(position);

        Log.d(LOG_TAG, "Processing: " + profileItem.getmLogin() + " -> " + Integer.toString(position));

        Picasso.with(mContext).load(profileItem.getmAvatar_url())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.getThumbnail());

        holder.mLogin.setText(profileItem.getmLogin());
        holder.mId.setText(profileItem.getmId());
    }

    public void loadNewData(List<Profile> profiles) {
        mProfileList = profiles;

        notifyDataSetChanged();
    }

    public Profile getProfile(int position) {
        return (mProfileList != null ? mProfileList.get(position) : null);
    }
}