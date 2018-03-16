package org.josan.githubbrowser;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileRecyclerViewHolder extends RecyclerView.ViewHolder {
    private ImageView mThumbnail;
    public TextView mLogin;
    public TextView mId;

    public ProfileRecyclerViewHolder(View itemView) {
        super(itemView);
        mThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
        mLogin = itemView.findViewById(R.id.textViewLogin);
        mId = itemView.findViewById(R.id.textViewId);
    }

    public ImageView getThumbnail() {
        return mThumbnail;
    }
}