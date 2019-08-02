package com.cc.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cc.chat.adpter.MsgAdapter;
import com.cc.chat.entity.Msg;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.msg_recycler_view)
    RecyclerView msgRecyclerView;
    @BindView(R.id.input_text)
    EditText inputText;
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.sendleft)
    Button sendleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化数据
        final ArrayList<Msg> msgList = new ArrayList<>();
        //初始化信息
        Msg msg1 = new Msg("hello guy", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("hello guy", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("hello guy", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
        //LayoutManager用来指定RecyclerView的布局方式，layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)设置横向滚动，StaggeredLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL) 可以设置3列的瀑布排列
        msgRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final MsgAdapter adapter = new MsgAdapter(this, msgList);

        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = inputText.getText().toString().trim();
                Msg msg3 = new Msg(trim, Msg.TYPE_SENT);
                msgList.add(msg3);
                adapter.notifyDataSetChanged();
                msgRecyclerView.scrollToPosition(msgList.size()-1);
            }
        });  sendleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = inputText.getText().toString().trim();
                Msg msg3 = new Msg(trim, Msg.TYPE_RECEIVED);
                msgList.add(msg3);
                adapter.notifyDataSetChanged();
                msgRecyclerView.scrollToPosition(msgList.size()-1);
            }
        });
    }

}
