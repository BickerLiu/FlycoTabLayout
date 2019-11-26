package com.flyco.tablayoutsamples.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.tablayoutsamples.entity.ListEntity;
import com.flyco.tablayoutsamples.utils.ViewFindUtils;

import java.util.List;


/**
 * @Author: lbq
 * @Description:
 * @CreateDate: 2019/11/25 12:32
 */
public class CommonTab2Adapter extends RecyclerView.Adapter<CommonTab2Adapter.ViewHolder> {

    private List<ListEntity> mListEntities;

    private Context mContext;

    public CommonTab2Adapter(List<ListEntity> listEntities) {
        mListEntities = listEntities;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View item = LayoutInflater.
                from(mContext).
                inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text1.setText(mListEntities.get(position).getEntityText());
        holder.text1.setTextColor(Color.BLACK);
    }

    @Override
    public int getItemCount() {
        if (mListEntities == null) {
            return 0;
        } else {
            return mListEntities.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = ViewFindUtils.find(itemView, android.R.id.text1);
        }
    }
}
