package come.dfy.custumeviewpopwindown.weiget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import come.dfy.custumeviewpopwindown.R;
import come.dfy.custumeviewpopwindown.adapter.PopupAdapter;

import static android.content.ContentValues.TAG;

/**
 * 作者：dfy on 16/11/2017 17:21
 * <p>
 * 邮箱：dengfuyao@163.com
 */

public class MyPopupView extends LinearLayout implements PopupAdapter.OnclickListener {
    @BindView(R.id.edit_text)
    EditText  mEditText;
    @BindView(R.id.arrow)
    ImageView mArrow;

    private ArrayList mList = new ArrayList();
    private PopupWindow mPopupWindow;

    public MyPopupView(Context context) {
        this(context, null);
    }

    public MyPopupView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPopupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.my_popup_view, this);
        ButterKnife.bind(this,this);
        initData();
    }

    private void initData() {
        if (mList.size() == 0) {
            for (int i = 0; i < 10; i++) {
                mList.add("条目" + i);
            }
        }
    }
    @OnClick(R.id.arrow)
    public void onViewClicked() {
        showPopupWindown();
    }

      private void showPopupWindown() {
        Log.e(TAG, "showPopupWindown: 点击弹popupWINDOW");
        if (mPopupWindow == null) {
            mPopupWindow = getPopupWindow();
        }
            int width = mEditText.getWidth();
            mPopupWindow.setWidth(width);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupWindow.showAsDropDown(mEditText, 0, 0); //显示在某一个view的下面,后面的x, y 值
            // popupWindow.showAtLocation(parent,gravity,x,y);  //显示在指定位置
    }

    @NonNull
    private PopupWindow getPopupWindow() {
        PopupWindow popupWindow = new PopupWindow(this);
        //设置要显示的view;
        View popView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_layout, null);
        ListView listView = (ListView) popView.findViewById(R.id.list_view);
        PopupAdapter popupAdapter = new PopupAdapter(getContext(), mList);
        listView.setAdapter(popupAdapter);
        popupAdapter.setDeleteOnclickListener(this);
        //设置可以获取焦点
        popupWindow.setFocusable(true);
        //设置背景,可以点击收起或者弹出window,
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setContentView(popView);

        return popupWindow;
    }

    @Override
    public void deleteItem(String item) {
        Toast.makeText(getContext(), "删除了"+item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickItem(String title) {
         mEditText.setText(title);
    }
}
