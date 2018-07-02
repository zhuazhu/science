package zhuazhu.science.widget;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zhuazhu.science.R;

/**
 * 主页底部菜单
 *
 * @author zhuazhu
 **/
public class MainMenuTab extends RelativeLayout implements View.OnClickListener {

    public static final int SELECT_CAT = 0x01;
    public static final int SELECT_MINE = 0x02;
    public static final int SELECT_INDEX = 0x03;


    @IntDef({SELECT_CAT, SELECT_INDEX, SELECT_MINE})
    public @interface Type {

    }

    public MainMenuTab(Context context) {
        this(context, null);
    }

    public MainMenuTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private LinearLayout mLayoutCat;
    private ImageView mImgCat;
    private TextView mTxtCat;

    private LinearLayout mLayoutMine;
    private ImageView mImgMine;
    private TextView mTxtMine;

    private ImageView mImgIndex;

    public MainMenuTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View v = LayoutInflater.from(context).inflate(R.layout.layout_main_menu_tab, this, true);
        mLayoutCat = v.findViewById(R.id.layout_cat);
        mImgCat = v.findViewById(R.id.img_cat);
        mTxtCat = v.findViewById(R.id.txt_cat);

        mLayoutMine = v.findViewById(R.id.layout_mine);
        mImgMine = v.findViewById(R.id.img_mine);
        mTxtMine = v.findViewById(R.id.txt_mine);

        mImgIndex = v.findViewById(R.id.img_index);

        mLayoutCat.setOnClickListener(this);
        mLayoutMine.setOnClickListener(this);
        mImgIndex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clearSelect();
        int itemType = SELECT_INDEX;
        switch (v.getId()) {
            case R.id.layout_cat:
                mImgCat.setImageResource(R.drawable.ic_cat_selected);
                mTxtCat.setTextColor(ContextCompat.getColor(getContext(), R.color.c_2395ff));
                itemType = SELECT_CAT;
                break;
            case R.id.layout_mine:
                mImgMine.setImageResource(R.drawable.ic_mine_selected);
                mTxtMine.setTextColor(ContextCompat.getColor(getContext(), R.color.c_2395ff));
                itemType = SELECT_MINE;
                break;
            case R.id.img_index:
                mImgIndex.setImageResource(R.drawable.ic_index_selected);
                itemType = SELECT_INDEX;
                break;
            default:
                break;
        }
        if(mOnTabClickListener!=null){
            mOnTabClickListener.onItemClick(itemType);
        }
    }

    private OnTabClickListener mOnTabClickListener;

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        mOnTabClickListener = onTabClickListener;
    }

    private void clearSelect() {
        mImgCat.setImageResource(R.drawable.ic_cat_unselect);
        mTxtCat.setTextColor(ContextCompat.getColor(getContext(), R.color.c_999));

        mImgMine.setImageResource(R.drawable.ic_mine_unselect);
        mTxtMine.setTextColor(ContextCompat.getColor(getContext(), R.color.c_999));

        mImgIndex.setImageResource(R.drawable.ic_index_unselect);
    }

    public interface OnTabClickListener {
        void onItemClick(@Type int item);
    }
}
