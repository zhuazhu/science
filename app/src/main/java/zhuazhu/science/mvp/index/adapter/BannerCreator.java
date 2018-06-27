package zhuazhu.science.mvp.index.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import mejust.frame.FrameManager;
import zhuazhu.science.R;
import zhuazhu.science.mvp.index.model.BannerBean;

/**
 * @author zhuazhu
 **/
public class BannerCreator implements MZHolderCreator<BannerCreator.BannerViewHolder> {
    @Override
    public BannerViewHolder createViewHolder() {
        return new BannerViewHolder();
    }

    public static class BannerViewHolder implements MZViewHolder<BannerBean> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            View v = View.inflate(context, R.layout.item_banner,null);
            mImageView = v.findViewById(R.id.img);
            return v;
        }

        @Override
        public void onBind(Context context, int i, BannerBean bannerBean) {
            FrameManager.imageLoadManager().loadNet(mImageView,bannerBean.getImagePath());
        }
    }
}
