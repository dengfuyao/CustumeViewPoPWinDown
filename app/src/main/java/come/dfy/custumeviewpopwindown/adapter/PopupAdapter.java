package come.dfy.custumeviewpopwindown.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import come.dfy.custumeviewpopwindown.R;

/**
 * 作者：dfy on 16/11/2017 13:31
 * <p>
 * 邮箱：dengfuyao@163.com
 */

public class PopupAdapter extends BaseAdapter {
    Context      mContext;
    List<String> mList;
    private OnclickListener onclickListener;

    public PopupAdapter(Context context, List data) {
        mContext = context;
        mList = data;

    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList != null) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTetle.setText(mList.get(position));
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通知删除的是哪一个条目;


                if (onclickListener!=null) {
                    onclickListener.deleteItem(mList.get(position));
                    mList.remove(position);
                }
                notifyDataSetChanged();
            }
        });

        holder.mTetle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclickListener!=null){
                    onclickListener.onClickItem(mList.get(position));
                }
            }
        });
        return convertView;
    }



    static class ViewHolder {
        @BindView(R.id.user)
        ImageView mUser;
        @BindView(R.id.tetle)
        TextView  mTetle;
        @BindView(R.id.delete)
        ImageView mDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
//定义接口,显示删除的数据;
   public interface OnclickListener {
         void deleteItem( String item);
    void onClickItem(String title);
    }

   //创建一个方法,给外部设置这个接口;
    public void setDeleteOnclickListener(OnclickListener onclickListener){
        this.onclickListener = onclickListener;
    }
}
