package zhuazhu.science.mvp.index.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mejust.frame.data.annotation.Adapter;
import mejust.frame.widget.adapter.BaseViewHolder;
import mejust.frame.widget.adapter.RecyclerAdapter;
import mejust.frame.widget.refresh.IPageControl;
import zhuazhu.science.R;
import zhuazhu.science.mvp.index.model.ArticleBean;

/**
 * @author zhuazhu
 **/
@Adapter(layout = R.layout.item_article, holder = ArtitcleAdapter.ViewHolder.class)
public class ArtitcleAdapter extends RecyclerAdapter<ArticleBean, ArtitcleAdapter.ViewHolder> {

    public ArtitcleAdapter(IPageControl pageControl) {
        super(pageControl);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, ArticleBean articleBean, int position) {
        holder.mTvAuthor.setText(articleBean.getAuthor());
        holder.mTvTime.setText(articleBean.getNiceDate());
        holder.mTvTitle.setText(articleBean.getTitle());
        holder.mTvType.setText(articleBean.getChapterName());
    }


    public static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_head)
        ImageView mIvHead;
        @BindView(R.id.tv_author)
        TextView mTvAuthor;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_type)
        TextView mTvType;
        public ViewHolder(View v) {
            super(v, false);
            ButterKnife.bind(this,v);
        }
    }
}
