package com.example.mazejia.aac.aac_demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mazejia.aac.aac_demo.R;
import com.example.mazejia.aac.aac_demo.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazejia on 2018/11/27.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Girl> mList;

    public ListAdapter() {
        mList = new ArrayList<Girl>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Girl girl = mList.get(position);
        holder.getTVGirlName().setText(girl.getWho());
        holder.getTVGirlAge().setText(girl.getPublishedAt());
        Glide.with(holder.getIVGirlAvatar().getContext())
                .load(girl.getUrl())
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.getIVGirlAvatar());
    }

    public void setGirlList(List<Girl> girlList) {
        mList.addAll(girlList);
        notifyDataSetChanged();
    }

    public void clearGirlList() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;

        private TextView mTVGirlName;

        private TextView mTVGirlAge;

        private ImageView mIVGirlAvatar;

        ViewHolder(View view) {
            super(view);
            mRoot = view.findViewById(R.id.rl_girl_item_root);
            mTVGirlName = (TextView) view.findViewById(R.id.tv_girl_name);
            mTVGirlAge = (TextView) view.findViewById(R.id.tv_girl_age);
            mIVGirlAvatar = (ImageView) view.findViewById(R.id.iv_girl_avatar);
        }

        private View getRoot() {
            return mRoot;
        }

        private TextView getTVGirlName() {
            return mTVGirlName;
        }

        private TextView getTVGirlAge() {
            return mTVGirlAge;
        }

        private ImageView getIVGirlAvatar() {
            return mIVGirlAvatar;
        }

    }
}
