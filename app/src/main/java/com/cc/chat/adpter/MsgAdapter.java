package com.cc.chat.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cc.chat.MainActivity;
import com.cc.chat.R;
import com.cc.chat.entity.Msg;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/8/1
 *@Time:20:34
 *@Description:${DESCRIPTION}
 * */public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Msg> msgList;

    public MsgAdapter(Context context, ArrayList<Msg> msgList) {
        this.context = context;
        this.msgList = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new ViewHolder(from.inflate(R.layout.msg_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Msg msg = msgList.get(i);//获取当前的Msg实例
        if (msg.getType() == Msg.TYPE_RECEIVED)//对消息的类型进行判断，显示对应的布局，隐藏另一个布局。
        {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
        } else if (msg.getType() == Msg.TYPE_SENT) {
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.rightMsg.setText(msg.getContent());
        }

    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder(View view)//传入view参数，获取布局中的控件
        {
            super(view);
            leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            leftMsg = (TextView) view.findViewById(R.id.left_msg);
            rightMsg = (TextView) view.findViewById(R.id.right_msg);
        }

    }
}
