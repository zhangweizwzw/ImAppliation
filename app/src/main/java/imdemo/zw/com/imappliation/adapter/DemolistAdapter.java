package imdemo.zw.com.imappliation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import imdemo.zw.com.imappliation.R;
import imdemo.zw.com.imappliation.model.FriendMsgBean;

/**
 * Created by WuXiaolong on 2015/7/2.
 */
public class DemolistAdapter extends RecyclerView.Adapter<DemolistAdapter.ViewHolder> {

    private Context mContext;
    private List<FriendMsgBean> dataList;

    public List<FriendMsgBean> getDataList() {
        return dataList;
    }

    public void removeAllDataList() {
        this.dataList.removeAll(dataList);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public DemolistAdapter(Context context, List<FriendMsgBean> dataList) {
        this.dataList = dataList;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView friimg;
        private TextView friname,fridate,frireason;
        private RelativeLayout rela;

        public ViewHolder(View itemView) {
            super(itemView);
            friname = (TextView) itemView.findViewById(R.id.friname);
            fridate = (TextView) itemView.findViewById(R.id.fridate);
            frireason = (TextView) itemView.findViewById(R.id.frireason);
            rela = (RelativeLayout) itemView.findViewById(R.id.rela);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frimsg_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.friname.setText(dataList.get(position).getUserName());
        holder.fridate.setText(dataList.get(position).getUserDate());
        int type=dataList.get(position).getType();
        String strtype="";
        if(type==1){
            strtype="好友请求被同意";
        }else if(type==2){
            strtype="好友请求被拒绝";
        }else if(type==3){
            strtype="收到好友邀请";
        }else if(type==4){
            strtype="被删除";
        }else if(type==5){
            strtype="增加了联系人";
        }

        holder.frireason.setText(strtype);

        if (onItemClickListener != null) {
            holder.rela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });

            holder.rela.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}