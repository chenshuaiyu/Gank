package com.example.chen.gank.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/15 21:32
 */
public class LatestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int GANK_TYPE = 0;
    public static final int TITLE_TYPE = 1;

    private Context mContext;
    private List<Gank> mGanks;

    public LatestAdapter(Context context, List<Gank> ganks) {
        mContext = context;
        mGanks = ganks;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == GANK_TYPE) {
            viewHolder = new GankHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, parent, false));
        } else if (viewType == TITLE_TYPE) {
            viewHolder = new TitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Gank gank = mGanks.get(position);
        if (holder.getItemViewType() == GANK_TYPE) {
            ((GankHolder) holder).bind(gank);
        } else if (holder.getItemViewType() == TITLE_TYPE) {
            ((TitleHolder) holder).bind(gank);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Gank gank = mGanks.get(position);
        if ("-1".equals(gank.get_id())) {
            return TITLE_TYPE;
        } else {
            return GANK_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return mGanks.size();
    }

    private class GankHolder extends RecyclerView.ViewHolder {
        TextView articleTitle;
        TextView author;
        TextView date;
        ImageView image;

        public GankHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.article_title);
            author = itemView.findViewById(R.id.author);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.image);
        }

        public void bind(Gank gank) {
            articleTitle.setText(gank.getDesc());
            author.setText(gank.getWho());
            date.setText(gank.getPublishedAt().substring(0, gank.getPublishedAt().indexOf("T")));
            if (gank.getImages() != null && gank.getImages().size() != 0) {
                image.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(gank.getImages().get(0)).into(image);
            }
            itemView.setOnClickListener(v -> mOnClickListener.onClick(gank));
        }
    }

    private class TitleHolder extends RecyclerView.ViewHolder {
        TextView title;

        public TitleHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }

        public void bind(Gank gank) {
            title.setText(gank.getDesc());
        }
    }

    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener{
        /**
         * 点击事件回调
         *
         * @param gank
         */
        void onClick(Gank gank);
    }
}
