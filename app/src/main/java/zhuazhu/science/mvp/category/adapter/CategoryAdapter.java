package zhuazhu.science.mvp.category.adapter;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mejust.frame.data.annotation.Adapter;
import mejust.frame.widget.adapter.BaseViewHolder;
import mejust.frame.widget.adapter.RecyclerAdapter;
import zhuazhu.science.R;
import zhuazhu.science.data.db.model.Category;

/**
 * @author zhuazhu
 **/
@Adapter(layout = R.layout.item_category,holder = CategoryAdapter.ViewHolder.class)
public class CategoryAdapter extends RecyclerAdapter<Category,CategoryAdapter.ViewHolder> {

    @Override
    public void bindViewHolder(ViewHolder holder, Category parentCategory, int position) {
        holder.mName.setText(parentCategory.getName());
    }

    public static class ViewHolder extends BaseViewHolder{
        @BindView(R.id.name)
        TextView mName;

        public ViewHolder(View view) {
            super(view,false);
            ButterKnife.bind(this, view);
        }
    }
}
