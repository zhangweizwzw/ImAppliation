package imdemo.zw.com.imappliation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import imdemo.zw.com.imappliation.R;
import imdemo.zw.com.imappliation.model.FriendMsgBean;

/**
 * @Class: ListAdapter
 * @Description: 数据适配器
 * @author: lling(www.liuling123.com)
 * @Date: 2015/10/29
 */
public class DemolistAdapter extends RecyclerView.Adapter<DemolistAdapter.ItemViewHolder> {

    private List<FriendMsgBean> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public DemolistAdapter(Context context, List<FriendMsgBean> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        System.out.println("111111111=="+mDatas.size());
        System.out.println("222222222=="+mDatas.get(i).getUserName());
        itemViewHolder.friname.setText(mDatas.get(i).getUserName());
        itemViewHolder.fridate.setText(mDatas.get(i).getUserDate());
        if(mOnItemClickListener != null) {
            if(!itemViewHolder.itemView.hasOnClickListeners()) {
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemClick(v, pos);
                    }
                });
                itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemLongClick(v, pos);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.e("ListAdapter", "onCreateViewHolder");
        ItemViewHolder holder = new ItemViewHolder(mInflater.inflate(
                R.layout.frimsg_list, viewGroup, false));
        return holder;
    }

    /**
     * 向指定位置添加元素
     * @param position
     * @param value
     */
//    public void add(int position, String value) {
//        if(position > mDatas.size()) {
//            position = mDatas.size();
//        }
//        if(position < 0) {
//            position = 0;
//        }
//        mDatas.add(position, value);
//        /**
//         * 使用notifyItemInserted/notifyItemRemoved会有动画效果
//         * 而使用notifyDataSetChanged()则没有
//         */
//        notifyItemInserted(position);
//    }

    /**
     * 移除指定位置元素
     * @return
     */
//    public String remove(int position) {
//        if(position > mDatas.size()-1) {
//            return null;
//        }
//        String value = mDatas.remove(position);
//        notifyItemRemoved(position);
//        return value;
//    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 处理item的点击事件和长按事件
     */
    interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {


        private TextView friname,fridate;
        private RelativeLayout rela;

        public ItemViewHolder(View itemView) {
            super(itemView);
            friname = (TextView) itemView.findViewById(R.id.friname);
            fridate = (TextView) itemView.findViewById(R.id.fridate);
            rela = (RelativeLayout) itemView.findViewById(R.id.rela);
        }
    }

}


//package imdemo.zw.com.imappliation.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import java.util.List;
//
//import imdemo.zw.com.imappliation.R;
//import imdemo.zw.com.imappliation.model.FriendMsgBean;
//
///**
// * Created by WuXiaolong on 2015/7/2.
// */
//public class DemolistAdapter extends RecyclerView.Adapter<DemolistAdapter.ViewHolder> {
//
//    private Context mContext;
//    private List<FriendMsgBean> dataList;
//
//    public List<FriendMsgBean> getDataList() {
//        return dataList;
//    }
//
//    public void removeAllDataList() {
//        this.dataList.removeAll(dataList);
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(View view, int position);
//
//        void onItemLongClick(View view, int position);
//    }
//
//    private OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    public DemolistAdapter(Context context, List<FriendMsgBean> dataList) {
//        this.dataList = dataList;
//        System.out.println("kkkkkkkkkkkkkkkkk=="+dataList.size());
//        mContext = context;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private ImageView friimg;
//        private TextView friname,fridate;
//        private RelativeLayout rela;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            friname = (TextView) itemView.findViewById(R.id.friname);
//            fridate = (TextView) itemView.findViewById(R.id.fridate);
//            rela = (RelativeLayout) itemView.findViewById(R.id.rela);
//        }
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frimsg_list, parent, false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.friname.setText(dataList.get(position).getUserName());
//        System.out.println("lllllllllllllll=="+dataList.get(position).getUserName());
//        if (onItemClickListener != null) {
//            holder.rela.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    onItemClickListener.onItemClick(holder.itemView, position);
//                }
//            });
//
//            holder.rela.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    onItemClickListener.onItemLongClick(holder.itemView, position);
//                    return false;
//                }
//            });
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//}